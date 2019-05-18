package com.md.goods.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.md.goods.model.PriceTag;

public interface IPriceTagService extends IService<PriceTag>{

	PriceTag findByProductId(Long productId);

	PriceTag findByGoodsCode(String goodsCode);

	List<PriceTag> getPriceTagListByGoodsId(Long goodsId);

	void edit(PriceTag priceTag);

	PriceTag reduceInventory(Long productId, Integer amount);

	void addInventory(Long productId, Integer amount);

	void add(PriceTag priceTag);

}
