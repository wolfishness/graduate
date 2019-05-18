package com.md.member.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("shop_permissions")
public class Permissions {

	// id
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	// 允许权限
	private int perArea;
	// 不允许权限
	private int disPerArea;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPerArea() {
		return perArea;
	}

	public void setPerArea(int perArea) {
		this.perArea = perArea;
	}

	public int getDisPerArea() {
		return disPerArea;
	}

	public void setDisPerArea(int disPerArea) {
		this.disPerArea = disPerArea;
	}

}
