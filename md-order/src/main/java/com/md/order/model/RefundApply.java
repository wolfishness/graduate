package com.md.order.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("shop_refund_apply")
public class RefundApply {
	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 用户id
	 */
	private Long memberId;
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 订单详情id
	 */
	private Long orderItemId;
	/**
	 * 申请原因
	 */
	private String applyWhy;
	/**
	 * 退款金额
	 */
	private Double money;
	/**
	 * 物流信息
	 */
	private String logisticsMsg;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 退款类型(0:仅退款 1：退货退款)
	 */
	private Integer refundType;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 退款单编号
	 */
	private String afterSaleApplyId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

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

	public String getApplyWhy() {
		return applyWhy;
	}

	public void setApplyWhy(String applyWhy) {
		this.applyWhy = applyWhy;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getLogisticsMsg() {
		return logisticsMsg;
	}

	public void setLogisticsMsg(String logisticsMsg) {
		this.logisticsMsg = logisticsMsg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRefundType() {
		return refundType;
	}

	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAfterSaleApplyId() {
		return afterSaleApplyId;
	}

	public void setAfterSaleApplyId(String afterSaleApplyId) {
		this.afterSaleApplyId = afterSaleApplyId;
	}

}
