package com.ljs.guns.rest.modular.goods;

import java.awt.JobAttributes;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ljs.guns.core.util.ToolUtil;
import com.ljs.guns.rest.modular.goods.dto.GoodsRequest;
import com.md.goods.model.Category;
import com.md.goods.model.Goods;
import com.md.goods.model.LowerGoods;
import com.md.goods.model.PriceTag;
import com.md.goods.model.Product;
import com.md.goods.oe.GoodsObject;
import com.md.goods.oe.ProductObject;
import com.md.goods.service.IBrandService;
import com.md.goods.service.ICategoryService;
import com.md.goods.service.IGoodsService;
import com.md.goods.service.ILowerGoodsService;
import com.md.goods.service.IPriceTagService;
import com.md.goods.service.IProductService;
import com.md.goods.service.ISeriesService;
import com.md.order.service.IOrderItemService;
import com.md.order.service.IOrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/goods")
public class ApiGoodsController {

	@Resource
	ICategoryService categoryService;

	@Resource
	ISeriesService seriesService;

	@Resource
	IGoodsService goodsService;

	@Resource
	IProductService productService;

	@Resource
	IPriceTagService priceTagService;

	@Resource
	IBrandService brandService;

	@Resource
	ILowerGoodsService lowerGoodsService;

	@Resource
	IOrderItemService orderItemService;

	@Resource
	IOrderService orderService;

	@ApiOperation(value = "通过分类获取商品列表", notes = "通过分类获取商品列表")
	@RequestMapping(value = "/getListByCategory", method = RequestMethod.POST)
	public ResponseEntity<?> getListByCategory(@RequestBody GoodsRequest goodsRequest) {
		Category category = categoryService.selectById(goodsRequest.getCateId());
		List<Map<String, Object>> goodsList = goodsService.getListByCate(category, goodsRequest.getIndex());
		JSONObject jObject = null;
		if (ToolUtil.isNotEmpty(goodsList)) {
			jObject = judgeInventory(goodsList);
			for (Map<String, Object> map : goodsList) {
				map.replace("images", map.get("images").toString().split(",")[0]);
				map.remove("unit");
				map.remove("createTime");
				map.remove("brandId");
				map.remove("goodsCode");
				map.remove("detail");
				map.remove("inventory");
				map.remove("isDel");
			}
			return ResponseEntity.ok(jObject);

		}
		return null;
	}

	@ApiOperation(value = "通过商品名称获取商品列表", notes = "通过商品名称获取商品列表")
	@RequestMapping(value = "/getListByName", method = RequestMethod.POST)
	public ResponseEntity<?> getListByName(@RequestBody GoodsRequest goodsRequest) {
		List<Map<String, Object>> result = goodsService.getListByName(goodsRequest.getName(), goodsRequest.getIndex());
		JSONObject jObject = null;
		if (ToolUtil.isNotEmpty(result)) {
			jObject = judgeInventory(result);
			return ResponseEntity.ok(jObject);
		}

		return null;
	}

	@ApiOperation(value = "获取商品详情", notes = "获取商品详情")
	@RequestMapping(value = "/getGoodDetail", method = RequestMethod.POST)
	public ResponseEntity<?> getGoodDetail(@RequestBody GoodsRequest goodsRequest) {
		GoodsObject goodsObject = new GoodsObject();
		Goods goods = goodsService.selectById(goodsRequest.getGoodsId());

		String imageUrL = goods.getImages().replace(",", "\",\"").toString();

		goodsObject.setImages("[\"" + imageUrL + "\"]");
		goodsObject.setGoodName(goods.getName());
		goodsObject.setBrandName(brandService.selectById(goods.getBrandId()).getName());
		goodsObject.setId(goods.getId());
		goodsObject.setMarketPrice(goods.getMarketPrice());
		goodsObject.setPrice(goods.getPrice());
		goodsObject.setStock((long) priceTagService.findByGoodsCode(goods.getGoodsCode()).getInventory());
		goodsObject.setDetail(goods.getDetail());
		goodsObject.setGoodsCode(goods.getGoodsCode());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("partData", JSONObject.parse(goodsObject.toString()));

		return ResponseEntity.ok(jsonObject);
	}

	@ApiOperation(value = "获取商品规格列表", notes = "获取商品规格列表")
	@RequestMapping(value = "/getProductList", method = RequestMethod.POST)
	public ResponseEntity<?> getProductList(@RequestBody GoodsRequest goodsRequest) {
		JSONObject jb = new JSONObject();

		int count = productService.findColorsNum(goodsRequest.getGoodsId());
		
		List<ProductObject> productObList = new ArrayList<>();
		List<PriceTag> priceTagList = priceTagService.getPriceTagListByGoodsId(goodsRequest.getGoodsId());
		Set<String> colors = new HashSet<>();

		JSONArray jArray = new JSONArray();//颜色
		
		for(int i = 0 ; i< count ; i++){
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();//尺码信息
			
			if (priceTagList.size() > 0) {
				for (PriceTag priceTag : priceTagList) {
					ProductObject productOb = new ProductObject();
					Product product = productService.selectById(priceTag.getProductId());
					productOb.setId(priceTag.getProductId());
					productOb.setMarketPrice(priceTag.getMarketPrice());
					productOb.setPrice(priceTag.getPrice());
					productOb.setStock(priceTag.getInventory());
					productOb.setSkuName(product.getName());
					productOb.setImage(product.getImageUrl());
					productOb.setGoodsSize(priceTag.getGoodsSize());
					String color = product.getName();// 获取颜色
					if (jsonObject.isEmpty() & !colors.contains(color) ) {

						jsonObject.put("name", color);
						colors.add(color);
					}
					if (jsonObject.containsValue(color)) {
						jsonArray.add(productOb);
					}
					productObList.add(productOb);
				}
			}
			jsonObject.put("colorSize", jsonArray);
			jArray.add(jsonObject);
		}
		jb.put("productList", jArray);

		return ResponseEntity.ok(jb);
	}

	@ApiOperation(value = "获取产品详情", notes = "获取产品详情")
	@RequestMapping(value = "/getProductDetail", method = RequestMethod.POST)
	public ResponseEntity<?> getProductDetail(@RequestBody GoodsRequest goodsRequest) {
		PriceTag tag = priceTagService.selectById(goodsRequest.getPriceTagId());
		ProductObject productObject = new ProductObject();
		if (tag != null) {
			Product product = productService.selectById(tag.getProductId());
			Goods goods = goodsService.selectById(tag.getGoodsId());
			productObject.setId(tag.getProductId());
			productObject.setMarketPrice(tag.getMarketPrice());
			productObject.setPrice(tag.getPrice());
			productObject.setSkuName(product.getName());
			productObject.setGoodsName(goods.getName());
			productObject.setWeight(product.getWeight());
		}
		return ResponseEntity.ok(productObject);
	}

	@ApiOperation(value = "通过货号查找商品信息", notes = "商品信息")
	@RequestMapping(value = "/getGoodsBySn", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getGoodDetailBySn(
			@ApiParam("商品货号") @RequestParam(value = "goodsCode", required = true) @RequestBody String goodsCode) {
		List<Goods> goodsList = goodsService.findBySn(goodsCode);
		Long goodsId = goodsList.get(0).getId();

		JSONObject jb = new JSONObject();
		GoodsObject goodsOb = new GoodsObject();
		Goods goods = goodsService.selectById(goodsId);
		goodsOb.setGoodName(goods.getName());
		goodsOb.setBrandName(brandService.selectById(goods.getBrandId()).getName());
		goodsOb.setId(goods.getId());
		goodsOb.setMarketPrice(goods.getMarketPrice());
		goodsOb.setPrice(goods.getPrice());
		goodsOb.setStock((long) priceTagService.findByGoodsCode(goods.getGoodsCode()).getInventory());
		goodsOb.setGoodsCode(goods.getGoodsCode());

		jb.put("goods", goodsOb);
		return jb;
	}
	
	

	public JSONObject judgeInventory(List<Map<String, Object>> goods) {
		//System.out.println(goods);
		JSONObject jb = new JSONObject();
		// 获取系统时间
		Timestamp d = new Timestamp(System.currentTimeMillis());
		if (ToolUtil.isEmpty(goods)) {
			jb.put("errcode", 0);
			jb.put("errmsg", "没有传入数据！");
			return jb;
		} else {
			List<LowerGoods> lowerGoodsList = new ArrayList<>();

			for (Map<String, Object> map : goods) {
				PriceTag priceTag = priceTagService.findByGoodsCode(map.get("goodsCode").toString());
				Product product = productService.selectById(priceTag.getProductId());
				map.replace("images", product.getImage());
				map.put("inventory", priceTag.getInventory());
				map.put("marketPrice", priceTag.getMarketPrice());
				String sum = orderService.findSumForMonth(Long.parseLong(map.get("id").toString()));
				map.put("sum", sum);
				//System.out.println(map);
				if (ToolUtil.isNotEmpty(priceTag) && priceTag.getInventory() == 0) {
					LowerGoods lGoods = new LowerGoods();
					lGoods.setGoodsCode(priceTag.getGoodsCode());
					lGoods.setGoodsId(priceTag.getGoodsId());
					lGoods.setPriority(0);// 处理等级 0系统 1 人工设置,设置为1时，只能通过人工修改
					lGoods.setCreateTime(d);
					lGoods.setCreator("sys");
					lowerGoodsList.add(lGoods);
				}
			}

			jb.put("data", goods);
			try {
				if (ToolUtil.isNotEmpty(lowerGoodsList)) {
					lowerGoodsService.insertBatch(lowerGoodsList, lowerGoodsList.size());
					// System.out.println("插入数据成功！");
					jb.put("errmsg", "插入数据成功！");
				} else {
					jb.put("errmsg", "数据已存在！");
				}
				jb.put("errcode", 0);
				return jb;
			} catch (Exception e) {
				jb.put("errmsg", "插入数据失败！" + e.getMessage());
				jb.put("errcode", -1);
				return jb;
			}
		}
	}
	
	

}
