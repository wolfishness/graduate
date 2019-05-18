package com.md.goods.service;

import java.util.Set;

import com.baomidou.mybatisplus.service.IService;
import com.md.goods.model.CategoryRelation;

/**
 * 商品和分类关联的service（中间表）
 */
public interface ICategoryRelationService extends IService<CategoryRelation> {

	/**
	 * 添加商品和分类的关联
	 * 
	 * @param
	 * @return
	 */
	void add(Set<CategoryRelation> categoryRelationSet);

	/**
	 * 修改商品和分类的关联
	 * 
	 * @param
	 * @return
	 */
	void edit(Long goodId, Set<CategoryRelation> categoryRelationSet);
}
