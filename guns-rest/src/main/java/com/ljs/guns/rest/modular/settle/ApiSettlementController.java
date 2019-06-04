package com.ljs.guns.rest.modular.settle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ljs.guns.core.exception.GunsException;
import com.ljs.guns.rest.common.exception.BizExceptionEnum;
import com.ljs.guns.rest.modular.settle.dto.SettleRequest;
import com.md.cart.model.CartItem;
import com.md.cart.service.ICartItemService;
import com.md.goods.service.IProductService;
import com.md.order.model.Order;
import com.md.order.model.OrderItem;
import com.md.order.service.IOrderItemService;
import com.md.order.service.IOrderService;
import com.md.settlement.service.IAccountService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/settlement")
public class ApiSettlementController {

	@Resource
	IAccountService accountService;

	@Resource
	IProductService productService;
	
	@Resource
	ICartItemService cartItemService;
	
	@Resource
	IOrderService orderService;
	
	@Resource
	IOrderItemService orderItemService;


	@ApiOperation(value = "结算", notes = "结算")
	@RequestMapping(value = "/account", method = { RequestMethod.POST })
	public ResponseEntity<?> orderItemSubmit(@RequestBody SettleRequest settleRequest) {

		System.out.println(settleRequest);
		
		String []arr =settleRequest.getCartItemList().split(",");
		List<CartItem> cartItems = new ArrayList<>();
		
		for(int i = 0 ; i < arr.length ; i ++){
			CartItem cartItem = cartItemService.selectById(Long.parseLong(arr[0]));
			cartItems.add(cartItem);
		}
		//判断库存是否充足
		if (!accountService.inventoryEnough(cartItems, accountService.findPriceTag(cartItems))) {
			throw new GunsException(BizExceptionEnum.INVENTORY_NOENOUGH);
		}
		
		Order order = accountService.amount(cartItems, settleRequest.getAddress(), settleRequest.getMemberId());
		
		orderService.add(order);
		orderItemService.addAll(order.getId(), order.getOrderItems());
		
		System.out.println(order);
		System.out.println(order.getOrderItems());
		return ResponseEntity.ok("添加成功");
		
	}
}
