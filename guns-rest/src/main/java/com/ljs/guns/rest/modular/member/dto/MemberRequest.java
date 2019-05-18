package com.ljs.guns.rest.modular.member.dto;

public class MemberRequest {

	// 客户id
	private Long memberId;
	// 是否默认地址
	private boolean isdefault;
	// 收货地址id
	private Long addressId;
	// 客户手机号
	private String phone;
	// 密码
	private String password;
	// 验证码
	private String VerificationCode;
	// 旧密码
	private String oldPassword;
	// 收藏商品数组，样例1,2,3,
	private String itemIds;
	// 昵称
	private String name;
	// 性别
	private Integer sex;
	// 用户openid
	private String openId;

	private String configKey;
	// 用户unionId
	private String unionId;
	// 用户登录类别
	private String loginType;
	// 用户头像
	private String headImgUrl;
	
	private String code;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public boolean isIsdefault() {
		return isdefault;
	}

	public void setIsdefault(boolean isdefault) {
		this.isdefault = isdefault;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerificationCode() {
		return VerificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		VerificationCode = verificationCode;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "MemberRequest [memberId=" + memberId + ", isdefault=" + isdefault + ", addressId=" + addressId
				+ ", phone=" + phone + ", password=" + password + ", VerificationCode=" + VerificationCode
				+ ", oldPassword=" + oldPassword + ", itemIds=" + itemIds + ", name=" + name + ", sex=" + sex
				+ ", openId=" + openId + ", configKey=" + configKey + ", unionId=" + unionId + ", loginType="
				+ loginType + ", headImgUrl=" + headImgUrl + "]";
	}
	
	

}
