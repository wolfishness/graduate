package com.ljs.guns.rest.modular.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljs.guns.core.util.DateUtil;
import com.ljs.guns.core.util.ToolUtil;
import com.ljs.guns.rest.modular.order.dto.EvaluationRequest;
import com.ljs.guns.rest.modular.order.dto.OrderRequest;
import com.md.cart.model.Cart;
import com.md.cart.model.CartItem;
import com.md.cart.service.ICartItemService;
import com.md.cart.service.ICartService;
import com.md.goods.model.PriceTag;
import com.md.goods.model.Product;
import com.md.goods.service.IGoodsService;
import com.md.goods.service.IPriceTagService;
import com.md.goods.service.IProductService;
import com.md.member.model.Member;
import com.md.member.service.IMemberService;
import com.md.order.constant.OrderStatus;
import com.md.order.model.AfterSaleApply;
import com.md.order.model.Evaluation;
import com.md.order.model.Order;
import com.md.order.model.OrderItem;
import com.md.order.model.RefundApply;
import com.md.order.service.IAfterSaleApplyService;
import com.md.order.service.IEvaluationService;
import com.md.order.service.IOrderItemService;
import com.md.order.service.IOrderService;
import com.md.order.service.IRefundApplyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/order")
public class ApiOrderController {

	@Resource
	IEvaluationService evaluationService;

	@Resource
	IOrderItemService orderItemService;

	@Resource
	IOrderService orderService;

	@Resource
	IProductService productService;

	@Resource
	IGoodsService goodsService;

	@Resource
	IPriceTagService priceTagService;

	@Resource
	IRefundApplyService refundApplyService;

	@Resource
	ICartItemService cartItemService;

	@Resource
	ICartService cartService;

	@Resource
	IMemberService memberService;
	
	@Resource
	IAfterSaleApplyService afterSaleApplyService;

	@ApiOperation(value = "添加评价", notes = "添加评价")
	@RequestMapping(value = "/addEvaluation", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addEvaluation(@RequestBody OrderRequest orderRequest) {
		for (EvaluationRequest eva : orderRequest.getEvaluationList()) {
			OrderItem orderItem = orderItemService.selectById(eva.getOrderItemId());
			Order order = orderService.selectById(orderItem.getOrderId());
			Product product = productService.selectById(orderItem.getProductId());
			Evaluation evaluation = new Evaluation();
			evaluation.setCreateTime(DateUtil.format(new Date()));
			evaluation.setDetail(eva.getDetail());
			evaluation.setGoodsId(orderItem.getGoodsId());
			evaluation.setMemberId(orderRequest.getMemberId());
			evaluation.setOrderItemId(eva.getOrderItemId());
			evaluationService.insert(evaluation);
			order.setStatus(OrderStatus.TRADE_SUCCESS.getCode());
			orderService.updateById(order);
		}

		return ResponseEntity.ok("success");
	}

	@ApiOperation(value = "获取评价列表", notes = "获取评价列表")
	@RequestMapping(value = "/getEvaluationList", method = RequestMethod.POST)
	public ResponseEntity<?> getEvaluationList(@RequestBody OrderRequest orderRequest) {
		List<Map<String, Object>> list = evaluationService.findListByPage2(orderRequest.getGoodsId(),
				orderRequest.getIndex());
		return ResponseEntity.ok(list);
	}

	@ApiOperation(value = "获取订单列表", notes = "获取订单列表")
	@RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getOrderList(@RequestBody OrderRequest orderRequest) {
		JSONObject jb = new JSONObject();
		if (ToolUtil.isEmpty(orderRequest.getMemberId())) {
			jb.put("data", "");
			return ResponseEntity.ok(jb);
		}
		List<Order> orderResult = orderService.findListByPage(orderRequest.getMemberId(), orderRequest.getStatus(),
				orderRequest.getIndex());
		if (orderResult.size() > 0) {
			for (Order order : orderResult) {
				List<Map<String, Object>> itemResult = orderItemService.getListByOrderId(order.getId());
				for (Map<String, Object> map : itemResult) {
					Product product = productService.selectById(Long.parseLong(map.get("productId").toString()));
					map.put("image", product.getImage());
					map.put("skuName", product.getName());
					map.put("goodsSize", product.getGoodsSize());
					
				}
				order.setItemObject(itemResult);
			}
		}
		jb.put("data", orderResult);
		return ResponseEntity.ok(jb);
	}
	
	@ApiOperation(value = "获取商家订单列表", notes = "获取订单列表")
	@RequestMapping(value = "/getShopOrderList", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getShopOrderList(@RequestBody OrderRequest orderRequest) {
		JSONObject jb = new JSONObject();

		List<Order> orderResult = orderService.findShopListByPage( orderRequest.getStatus(),orderRequest.getIndex());
		if (orderResult.size() > 0) {
			for (Order order : orderResult) {
				List<Map<String, Object>> itemResult = orderItemService.getListByOrderId(order.getId());
				for (Map<String, Object> map : itemResult) {
					Product product = productService.selectById(Long.parseLong(map.get("productId").toString()));
					map.put("image", product.getImage());
					map.put("skuName", product.getName());
					map.put("goodsSize", product.getGoodsSize());

					if(orderRequest.getStatus() == 9){

						AfterSaleApply afterSaleApply = afterSaleApplyService.findInAfterSale(order.getId(), Long.parseLong(map.get("id").toString()));

						
						if (ToolUtil.isNotEmpty(afterSaleApply)) {
							map.put("afterSaleId", afterSaleApply.getId());
						}
					}
				}
				order.setItemObject(itemResult);
			}
		}
		jb.put("data", orderResult);
		return ResponseEntity.ok(jb);
	}

	@ApiOperation(value = "获取退款申请列表", notes = "获取退款申请列表")
	@RequestMapping(value = "/getRefundApplyList", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getRefundApplyList(@RequestBody OrderRequest orderRequest) {
		JSONObject jb = new JSONObject();
		if (ToolUtil.isEmpty(orderRequest.getMemberId())) {
			jb.put("data", "");
			return ResponseEntity.ok(jb);
		}
		List<RefundApply> refundApplys = refundApplyService.findMemberRefundApplyToo(orderRequest.getMemberId());
		List<Order> orders = new ArrayList<>();
		for (RefundApply refundApply : refundApplys) {
			Order order = orderService.selectById(refundApply.getOrderId());
			order.setRefundApply(refundApply);
			List<Map<String, Object>> itemResult = orderItemService.getListByOrderId(order.getId());
			order.setItemObject(itemResult);
			orders.add(order);
		}
		jb.put("data", orders);
		return ResponseEntity.ok(jb);
	}

	@ApiOperation(value = "修改订单状态", notes = "修改订单状态")
	@RequestMapping(value = "/changeOrderStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> changeOrderStatus(@RequestBody OrderRequest orderRequest) {
		JSONObject jb = new JSONObject();
		Order order = orderService.selectById(orderRequest.getOrderId());
		// 订单状态判断 需要新增
		order.setStatus(orderRequest.getStatus());
		orderService.updateById(order);
		
		if (orderRequest.getStatus() == OrderStatus.WAIT_EVALUATE.getCode()) {
			order.setGainsTime(DateUtil.format(new Date()));
			orderService.updateById(order);
		}
		
		if (orderRequest.getStatus() == OrderStatus.TRADE_CLOSE.getCode()) {
			for (OrderItem item : orderItemService.findByOrderId(orderRequest.getOrderId())) {
				priceTagService.addInventory(item.getProductId(), item.getQuantity());
			}
		}
		
		if (order.getStatus() == OrderStatus.WAIT_SEND.getCode()) {
			order.setPayTime(DateUtil.format(new Date()));
		}

		jb.put("data", "success");
		return ResponseEntity.ok(jb);
	}

	@ApiOperation(value = "获取订单详情", notes = "获取订单详情")
	@RequestMapping(value = "/getOrderDetail", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getOrderDetail(@RequestBody OrderRequest orderRequest) {
		JSONObject jb = new JSONObject();
		Order order = orderService.selectById(orderRequest.getOrderId());
		if (ToolUtil.isNotEmpty(order)) {
			List<Map<String, Object>> itemResult = orderItemService.getListByOrderId(order.getId());
			for (Map<String, Object> map : itemResult) {
				Product product = productService.selectById(Long.parseLong(map.get("productId").toString()));
				map.put("image", product.getImage());
				map.put("skuName", product.getName());
				map.put("goodsSize", product.getGoodsSize());
			}
			
			order.setItemObject(itemResult);
		}
		jb.put("data", order);
		return ResponseEntity.ok(jb);
	}

	@ApiOperation(value = "获取退款详情", notes = "获取退款详情")
	@RequestMapping(value = "/getRefundDetail", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getRefundDetail(@RequestBody OrderRequest orderRequest) {
		JSONObject jb = new JSONObject();
		RefundApply refundApply = refundApplyService.selectById(orderRequest.getRefundApplyId());
		Order order = orderService.selectById(refundApply.getOrderId());
		order.setRefundApply(refundApply);
		List<Map<String, Object>> itemResult = orderItemService.getListByOrderId(order.getId(),
				refundApply.getOrderItemId());
		order.setItemObject(itemResult);
		jb.put("data", order);
		return ResponseEntity.ok(jb);
	}

	@ApiOperation(value = "获取各状态订单数量", notes = "获取各状态订单数量")
	@RequestMapping(value = "/getOrderQuantity", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> getOrderQuantity(@RequestBody OrderRequest orderRequest) {
		List<Integer> quantity = new ArrayList<>();
		quantity.add(orderService.selectOrderCount(orderRequest.getMemberId(), 0));
		quantity.add(orderService.selectOrderCount(orderRequest.getMemberId(), 2));
		quantity.add(orderService.selectOrderCount(orderRequest.getMemberId(), 3));
		quantity.add(orderService.selectOrderCount(orderRequest.getMemberId(), 6));
		quantity.add(refundApplyService.selectOrderCount(orderRequest.getMemberId(), "7,9,10"));
		return ResponseEntity.ok(quantity);
	}

	@ApiOperation(value = "批量提交订单", notes = "批量提交订单")
	@RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
	public ResponseEntity<?> submitOrder(@RequestBody OrderRequest orderRequest) throws Exception {
		List<Long> idList = new ArrayList<>();
		System.out.println("批量提交订单:" + JSON.toJSONString(orderRequest));
		String serial =new Date().getTime()+ String.valueOf((int)((Math.random()*
		 9 + 1) * 100000));
		for (Order order : orderRequest.getOrderList()) {
			// 先判断库存是否足够
			for (OrderItem item : order.getOrderItems()) {
				PriceTag pricetay = priceTagService.findByProductId(item.getProductId());
				if (pricetay.getInventory() < item.getQuantity()) {
					JSONObject jobj = new JSONObject();
					jobj.put("errCode", 1);
					jobj.put("errMsg", "库存不足,不能请重新选择");
					return ResponseEntity.ok(jobj);
				}
			}

			Cart cart = cartService.findByMemberId(order.getMemberId());
			if (orderRequest.isCart()) {
				cart.setQuantity(cart.getQuantity() - order.getPackages());
				cartService.updateById(cart);
				orderRequest.setCart(false);
			}
			order.setSerial(serial);
			// order.setSn(sn);
			order.setCreateTime(DateUtil.format(new Date()));
			orderService.add(order);
			idList.add(order.getId());
			for (OrderItem item : order.getOrderItems()) {
				PriceTag pricetay = priceTagService.findByProductId(item.getProductId());
				if (pricetay.getInventory() < item.getQuantity()) {

				}
				item.setGoodsId(pricetay.getGoodsId());
				PriceTag priceTag = priceTagService.reduceInventory(item.getProductId(), item.getQuantity());
				if (priceTag.getInventory() <= priceTag.getThreshold()) {
					Product product = productService.selectById(priceTag.getProductId());
					
				}
				item.setOrderId(order.getId());
				// by ljs 20190219 将UnitPrice价格修改为促销政策的价格
				item.setActualPay(item.getActualPrice().multiply(new BigDecimal(item.getQuantity())));
				item.setOriginalPay(item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())));
				orderItemService.insert(item);
				CartItem cartItem = cartItemService.findByPriceTagId(priceTag.getId(), cart.getId());
				if (ToolUtil.isNotEmpty(cartItem)) {
					cartItemService.deleteById(cartItem.getId());
				}
			}
		}

		return ResponseEntity.ok(idList);
	}
	
	/**
	 * by ljs	20190219
	 * @param orderId
	 * @param orderItemId
	 * @return
	 */
	@ApiOperation(value = "新增售后处理单", notes = "新增售后处理单")
	@RequestMapping(value = "/insertAfterSaleApply", method = RequestMethod.POST)
	public ResponseEntity<?> insertAfterSaleApply(@RequestBody OrderRequest orderRequest){
		
		System.out.println(orderRequest.getOrderId());
		System.out.println(orderRequest.getOrderItemId());
		JSONObject jb = new JSONObject();
		
		//by ljs 20190219 	更新明细表售后状态
		OrderItem orderItem = orderItemService.selectById(orderRequest.getOrderItemId());
		if (orderItem.getRefundStatus() == 3) {
			jb.put("data", "error");
			jb.put("errcode", -1);
			jb.put("errmsg", "存在未处理的售后申请");
			return ResponseEntity.ok(jb);
		}
		orderItem.setRefundQuantity(orderItem.getQuantity());
		orderItem.setRefundAmount(orderItem.getOriginalPay());
		orderItem.setRefundStatus(3);
		try {
			orderItemService.updateById(orderItem);
		} catch (Exception e) {
			System.out.println("更新明细表售后状态失败！");
		}
		//by ljs 20190219 	更新主表售后状态
		Order order = orderService.selectById(orderRequest.getOrderId());
		order.setStatus(9);
		order.setRefundStatus(3);
		try {
			System.out.println(order.getStatus());
			orderService.updateById(order);
		} catch (Exception e) {
			System.out.println("更新主表售后状态失败！");
		}
		//by ljs 20190219 	查询对应的条形码
		long productId = orderItemService.selectById(orderRequest.getOrderItemId()).getProductId();
		String goodsCode = productService.selectById(productId).getGoodsCode();
		//by ljs 20190219 	插入数据到售后申请单
		AfterSaleApply afterSaleApply = new AfterSaleApply();
		afterSaleApply.setOrderId(orderRequest.getOrderId());
		afterSaleApply.setOrderItemId(orderRequest.getOrderItemId());
		afterSaleApply.setGoodsCode(goodsCode);
		//by ljs 20190219 	增加申请原因
		afterSaleApply.setRefundQuantity(orderItem.getQuantity());
		afterSaleApply.setRefundAmount(orderItem.getOriginalPay());
		afterSaleApply.setStatus(3);
		afterSaleApply.setCreateTime(DateUtil.format(new Date()));
		System.out.println(afterSaleApply);
		afterSaleApplyService.insert(afterSaleApply);
		try {
			afterSaleApplyService.insert(afterSaleApply);

			jb.put("data", afterSaleApply);
			jb.put("errcode", 0);
			jb.put("errmsg", "0");
		} catch (Exception e) {
			System.out.println("插入数据到售后申请单失败！");
		}
		return ResponseEntity.ok(jb);
	}
	
	
	
	@ApiOperation(value = "售后处理单处理", notes = "售后处理单处理")
	@RequestMapping(value = "/updateAfterSaleApply", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> updateAfterSaleApply(@RequestBody OrderRequest orderRequest) {
		System.out.println(orderRequest.toString());
		AfterSaleApply afterSaleApply = null;
		JSONObject jb = new JSONObject();
		Date date = new Date();       
		Timestamp manageTime = new Timestamp(date.getTime());
		
		try {
			
			afterSaleApply = afterSaleApplyService.findOne(orderRequest.getRefundApplyId());
			
			OrderItem orderItem = orderItemService.selectById(afterSaleApply.getOrderItemId());
			//更新明细表状态标识
			orderItem.setRefundStatus(1);
			
			//更新售后申请单确认状态
			afterSaleApply.setConfirm(orderRequest.getDealDetail());
			if (orderRequest.getDealDetail() == 1) {
				// 更新明细表状态标识
				orderItem.setRefundAmount(orderItem.getRefundAmount().add(afterSaleApply.getRefundAmount()));
				orderItem.setRefundQuantity(orderItem.getRefundQuantity() + afterSaleApply.getRefundQuantity());
				
			}
			
			orderItemService.updateById(orderItem);
			
			//更新售后申请单标识
			afterSaleApply.setStatus(1);

			afterSaleApply.setManagerId(orderRequest.getMemberId());
			afterSaleApply.setManageTime(manageTime);
			
			afterSaleApplyService.updateById(afterSaleApply);
			//更新主表退货状态标识
			Order order = orderService.selectById(afterSaleApply.getOrderId());
			
			String status = orderItemService.selectRefundStatus(afterSaleApply.getOrderId());
			if ("1".equals(status)) {
				order.setRefundStatus(3);
			}else {
				order.setRefundStatus(1);
			}
			if (order.getStatus() == 9 && orderRequest.getDealDetail() == 1) {
				order.setStatus(10);
			}else{
				
			}
			orderService.updateById(order);
			
			jb.put("data", afterSaleApply);
			jb.put("errcode", 0);
			jb.put("errmsg", "0");
			return ResponseEntity.ok(jb);
		} catch (Exception e) {
			e.printStackTrace();
			jb.put("data", "error");
			jb.put("errcode", -1);
			jb.put("errmsg", "查无此售后申请单");
			return ResponseEntity.ok(jb);
		}

	}


}
