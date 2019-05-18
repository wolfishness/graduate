package com.md.goods.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.md.goods.model.Product;

public interface ProductMapper extends BaseMapper<Product>{

	int findColorsNum(@Param("goodsId") Long goodsId);
}
