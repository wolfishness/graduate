package com.md.order.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.md.member.model.Member;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@TableName("shop_order")
public class Order {
	/**
	 * 主键id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	// 流水号
	private String serial;
	// 日期
	private Timestamp createTime;
	// 确认收货日期
	private Timestamp gainsTime;
	// 支付日期
	private Timestamp payTime;
	// 应付款
	private BigDecimal due;
	// 实付款
	private BigDecimal actualPay;
	// 总件数
	private Integer packages;
	// 支付方式
	private String payment;
	// 顾客(顾客对象id)
	private Long memberId;
	// 配送方式(配送对象)
	private Long diliveryId;
	// 总重量
	private BigDecimal weight = BigDecimal.ZERO;
	// 总运费
	private BigDecimal diliveryPay;
	// 收货人名称
	private String consigneeName;
	// 收货人地址
	private String address;
	// 收货人联系方式
	private String phoneNum;
	// 备注
	private String remark;
	// 状态（待付款、待审核、待发货、待收货、完成、关闭）
	private Integer status;
	// 所属用户
	@TableField(exist = false)
	private Member member;
	// 明细(订单明细对象)
	@TableField(exist = false)
	private Set<OrderItem> orderItems;
	// 自定义明细(订单明细对象)
	@TableField(exist = false)
	private Object itemObject;
	@TableField(exist = false)
	private RefundApply refundApply;
	// 退货申请状态标识
	private int refundStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getGainsTime() {
		return gainsTime;
	}

	public void setGainsTime(Timestamp gainsTime) {
		this.gainsTime = gainsTime;
	}

	public Timestamp getPayTime() {
		return payTime;
	}

	public void setPayTime(Timestamp payTime) {
		this.payTime = payTime;
	}

	public BigDecimal getDue() {
		return due;
	}

	public void setDue(BigDecimal due) {
		this.due = due;
	}

	public BigDecimal getActualPay() {
		return actualPay;
	}

	public void setActualPay(BigDecimal actualPay) {
		this.actualPay = actualPay;
	}

	public Integer getPackages() {
		return packages;
	}

	public void setPackages(Integer packages) {
		this.packages = packages;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getDiliveryId() {
		return diliveryId;
	}

	public void setDiliveryId(Long diliveryId) {
		this.diliveryId = diliveryId;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getDiliveryPay() {
		return diliveryPay;
	}

	public void setDiliveryPay(BigDecimal diliveryPay) {
		this.diliveryPay = diliveryPay;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Object getItemObject() {
		return itemObject;
	}

	public void setItemObject(Object itemObject) {
		this.itemObject = itemObject;
	}

	public RefundApply getRefundApply() {
		return refundApply;
	}

	public void setRefundApply(RefundApply refundApply) {
		this.refundApply = refundApply;
	}

	public int getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(int refundStatus) {
		this.refundStatus = refundStatus;
	}

}
