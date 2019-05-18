package com.md.member.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("shop_member")
public class Member {
	@TableId(value = "id" , type = IdType.AUTO)
	private Long id;
	private String name;
	private String realName;
	private String password;
	private String phoneNum;
	private int sex;
	private int age;
	private int status;
	private int permissionsId;
	private String openId;
	private String unionId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPermissionsId() {
		return permissionsId;
	}
	public void setPermissionsId(int permissionsId) {
		this.permissionsId = permissionsId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", realName=" + realName + ", password=" + password
				+ ", phoneNum=" + phoneNum + ", sex=" + sex + ", age=" + age + ", status=" + status + ", permissionsId="
				+ permissionsId + ", openId=" + openId + ", unionId=" + unionId + "]";
	}
	
	

}
