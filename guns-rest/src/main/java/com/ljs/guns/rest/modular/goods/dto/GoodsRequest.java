package com.ljs.guns.rest.modular.goods.dto;

public class GoodsRequest {
	private Long cateId;

	private Integer index;

	private String name;

	private Long goodsId;

	private Long tagId;

	private Long priceTagId;
	
	private Long memberId;

	public Long getCateId() {
		return cateId;
	}

	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Long getPriceTagId() {
		return priceTagId;
	}

	public void setPriceTagId(Long priceTagId) {
		this.priceTagId = priceTagId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "GoodsRequest [cateId=" + cateId + ", index=" + index + ", name=" + name
				+ ", goodsId=" + goodsId + ", tagId=" + tagId + ", priceTagId=" + priceTagId + "]";
	}
	
	
}
