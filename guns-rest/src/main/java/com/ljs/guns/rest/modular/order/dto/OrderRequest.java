package com.ljs.guns.rest.modular.order.dto;

import java.util.List;

import com.md.order.model.Order;

public class OrderRequest {

	// 订单id
	private Long orderId;
	// 订单项id
	private Long orderItemId;
	// 订单状态
	private Integer status;
	// 退款原因
	private String applyWhy;
	// 用户id
	private Long memberId;
	// 页码
	private Integer index;
	// 商品id
	private Long goodsId;
	// 评价详情
	private String detail;
	// 评价等级
	private Integer level;
	// 订单列表
	private List<Order> orderList;
	// 评价列表
	private List<EvaluationRequest> evaluationList;
	// 是否购物车
	private boolean isCart;
	// 退款申请id
	private Long refundApplyId;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getApplyWhy() {
		return applyWhy;
	}

	public void setApplyWhy(String applyWhy) {
		this.applyWhy = applyWhy;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<EvaluationRequest> getEvaluationList() {
		return evaluationList;
	}

	public void setEvaluationList(List<EvaluationRequest> evaluationList) {
		this.evaluationList = evaluationList;
	}

	public boolean isCart() {
		return isCart;
	}

	public void setCart(boolean isCart) {
		this.isCart = isCart;
	}

	public Long getRefundApplyId() {
		return refundApplyId;
	}

	public void setRefundApplyId(Long refundApplyId) {
		this.refundApplyId = refundApplyId;
	}

}
