package com.ljs.guns.rest.modular.cart;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.domain.OrderItem;
import com.ljs.guns.core.util.ToolUtil;
import com.ljs.guns.rest.modular.cart.dto.CartRequest;
import com.md.cart.model.Cart;
import com.md.cart.model.CartItem;
import com.md.cart.oe.CartObject;
import com.md.cart.oe.GoodObject;
import com.md.cart.service.ICartItemService;
import com.md.cart.service.ICartService;
import com.md.goods.model.Goods;
import com.md.goods.model.PriceTag;
import com.md.goods.model.Product;
import com.md.goods.service.IGoodsService;
import com.md.goods.service.IPriceTagService;
import com.md.goods.service.IProductService;
import com.md.order.model.Order;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/cart")
public class ApiCartController{

	@Resource
	ICartService cartService;

	@Resource
	ICartItemService cartItemService;

	@Resource
	IPriceTagService priceTagService;

	@Resource
	IGoodsService goodsService;

	@Resource
	IProductService productService;


	@ApiOperation(value = "加入购物车", notes = "加入购物车")
	@RequestMapping(value = "/addCart", method = RequestMethod.POST)
	public ResponseEntity<?> addCart(@RequestBody CartRequest cartRequest) {
		System.out.println(cartRequest);
		Cart cart = cartService.addSave(cartRequest.getMemberId());// 初始化购物车或者查找到对应的购物车
		PriceTag priceTag = priceTagService.findByProductId(cartRequest.getProductId());
		if (ToolUtil.isNotEmpty(priceTag)) {
			CartItem cartItem = cartItemService.findByPriceTagId(priceTag.getId(), cart.getId());
			if (ToolUtil.isNotEmpty(cartItem)) {
				cartItem.setQuantity(cartItem.getQuantity() + cartRequest.getQuantity());
				cartItemService.updateById(cartItem);// 新增购物车项
			} else {
				CartItem cartItem2 = new CartItem();
				cartItem2.setCartId(cart.getId());
				cartItem2.setPriceTagId(priceTag.getId());
				cartItem2.setProductId(cartRequest.getProductId());
				cartItem2.setQuantity(cartRequest.getQuantity());
				cartItem2.setStatus(1);
				cartItemService.insert(cartItem2);
				if (ToolUtil.isEmpty(cart.getQuantity())) {
					cart.setQuantity(1);
				} else {
					cart.setQuantity(cart.getQuantity() + 1);
				}
				cartService.updateById(cart);
			}
		} else {
			return ResponseEntity.ok("不存在当前商品");
		}
		return ResponseEntity.ok("加入购物车成功！");

	}

	@ApiOperation(value = "批量加入购物车", notes = "批量加入购物车")
	@RequestMapping(value = "/addCartMore", method = RequestMethod.POST)
	public ResponseEntity<?> addCartMore(@RequestBody CartRequest cartRequest) {
		Cart cart = cartService.addSave(cartRequest.getMemberId()); // 初始化购物车
		String[] productArray = cartRequest.getProducts().split(",");
		String[] quantityArray = cartRequest.getQuantitys().split(",");
		if (productArray.length > 0) {
			for (Integer i = 0; i < productArray.length; i++) {
				PriceTag tag = priceTagService.findByProductId(Long.valueOf(productArray[i]));
				if (ToolUtil.isNotEmpty(tag)) {
					CartItem cartItem = cartItemService.findByPriceTagId(tag.getId(), cart.getId());
					if (cartItem != null) {
						cartItem.setQuantity(cartItem.getQuantity() + Integer.valueOf(quantityArray[i]));
						cartItemService.updateById(cartItem); // 新增购物车项
					} else {
						CartItem cartItem2 = new CartItem();
						cartItem2.setCartId(cart.getId());
						cartItem2.setProductId(Long.valueOf(productArray[i]));
						cartItem2.setPriceTagId(tag.getId());
						cartItem2.setQuantity(Integer.valueOf(quantityArray[i]));
						cartItem2.setStatus(1);
						cartItemService.insert(cartItem2); // 新增购物车项
						if (ToolUtil.isEmpty(cart.getQuantity())) {
							cart.setQuantity(1);
						} else {
							cart.setQuantity(cart.getQuantity() + 1);
						}
						cartService.updateById(cart); // 加入购物车
					}
				} else {
					return ResponseEntity.ok("不存在当前商品");
				}
			}
		} else {
			return ResponseEntity.ok("加入购物车成功");
		}
		return ResponseEntity.ok("加入购物车成功");
	}

	@ApiOperation(value = "修改购物车规格", notes = "修改购物车规格")
	@RequestMapping(value = "/modifyCart", method = RequestMethod.POST)
	public ResponseEntity<?> modifyCart(
			@ApiParam("购物车项id") @RequestParam(value = "cartItemId", required = true) @RequestBody long cartItemId,
			@ApiParam("产品id") @RequestParam(value = "productId", required = true) @RequestBody long productId,
			@ApiParam("数量") @RequestParam(value = "quantity", required = true) @RequestBody Integer quantity) {
		CartItem item = cartItemService.selectById(cartItemId);
		PriceTag tag = priceTagService.findByProductId(productId);
		item.setPriceTagId(tag.getId());
		item.setProductId(productId);
		item.setQuantity(quantity);
		cartItemService.updateById(item);
		return ResponseEntity.ok("购物车修改成功");
	}

	@ApiOperation(value = "修改购物车数量", notes = "修改购物车数量")
	@RequestMapping(value = "/modifyCartQuan", method = RequestMethod.POST)
	public ResponseEntity<?> modifyCartQuan(@RequestBody CartRequest cartRequest) {
		CartItem item = cartItemService.selectById(cartRequest.getCartItemId());
		item.setQuantity(cartRequest.getQuantity());
		cartItemService.updateById(item);
		return ResponseEntity.ok("购物车修改成功");
	}

	@ApiOperation(value = "购物车列表", notes = "购物车列表")
	@RequestMapping(value = "/getCartList", method = RequestMethod.POST)
	public ResponseEntity<?> getCartList(@RequestBody CartRequest cartRequest) {
	
		CartObject cartObject = new CartObject();
		Cart cart = cartService.findByMemberId(cartRequest.getMemberId());
		if (ToolUtil.isNotEmpty(cart)) {
			List<CartItem> cartItems = cartItemService.findByCartId(cart.getId());
			List<GoodObject> goodList = new ArrayList<>();//购物车列表
			if (cartItems.size() > 0) {

				for (CartItem cartItem : cartItems) {
					GoodObject good = new GoodObject();
					PriceTag tag = priceTagService.findByProductId(cartItem.getProductId());
					if (ToolUtil.isNotEmpty(tag)) {
						// 如果产品已经下架则删除购物车项
						if (tag.getStatus() == 0) {
							cart.setQuantity(cart.getQuantity() - cartItem.getQuantity());
							cartService.updateById(cart);
							cartItemService.deleteById(cartItem.getId());
							continue;
						}

						if (ToolUtil.isNotEmpty(tag.getPrice())) {
							good.setPrice(tag.getPrice());
						}
						good.setGoodsId(tag.getGoodsId());

						Goods goods = goodsService.selectById(tag.getGoodsId());
						// 如果商品已删除则删除购物车项:代码开始
						if (ToolUtil.isEmpty(goods) || goods.isDel()) {
							cart.setQuantity(cart.getQuantity() - cartItem.getQuantity());
							cartService.updateById(cart);
							cartItemService.deleteById(cartItem.getId());
							continue;
						}

						// 获取商品信息
						if (ToolUtil.isNotEmpty(goods)) {
							good.setGoodsCode(goods.getGoodsCode());
							good.setGoodsName(goods.getName());
						}
						good.setCartItemId(cartItem.getId());
						Product product = productService.selectById(cartItem.getProductId());

						good.setImageUrl(product.getImage());
						good.setGoodsSize(product.getGoodsSize());
						good.setGoodsCode(product.getGoodsCode());
						good.setProductId(cartItem.getProductId());
						good.setQuantity(cartItem.getQuantity());
						good.setSkuName(product.getName());
						good.setStatus(cartItem.getStatus());
						goodList.add(good);
					}
				}
				cartObject.setCartId(cart.getId());
				cartObject.setMemberId(cart.getMemberId());
				cartObject.setQuantity(cart.getQuantity());
				cartObject.setGoodObjectList(goodList);
				//System.out.println(cartObject);
				return ResponseEntity.ok(cartObject);
			}
		}
		
		return ResponseEntity.ok(cartObject);
	}
	
	@ApiOperation(value = "删除购物车", notes = "删除购物车")
	@RequestMapping(value = "/deleteCart", method = RequestMethod.POST)
	public ResponseEntity<?> deleteCart( @RequestBody CartRequest cartRequest) {
		
		CartItem cartItem = cartItemService.selectById(cartRequest.getCartItemId());
		
		cartItemService.deleteById(cartRequest.getCartItemId());
		
		Cart cart = cartService.selectById(cartItem.getCartId());
		cart.setQuantity(cart.getQuantity() - 1);
		cartService.updateById(cart);
		/*String[] arr = cartRequest.getItemIds().split(",");
		for(int i = 0; i < arr.length; i++) {
			cartItemService.deleteById(Long.valueOf(arr[i]));
		}
		Cart cart = cartService.selectById(cartRequest.getCartId());
		cart.setQuantity(cart.getQuantity() - arr.length);
		cartService.updateById(cart);*/
		
		return ResponseEntity.ok("删除成功");
	}
	
}
