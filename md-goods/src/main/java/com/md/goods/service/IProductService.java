package com.md.goods.service;

import com.baomidou.mybatisplus.service.IService;
import com.md.goods.model.Product;

public interface IProductService extends IService<Product>{

	int findColorsNum(Long goodsId);

}
