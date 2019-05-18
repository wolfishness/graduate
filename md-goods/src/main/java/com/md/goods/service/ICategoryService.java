package com.md.goods.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.md.goods.model.Category;

public interface ICategoryService extends IService<Category>{

	List<Category> findCategoryList(Long id);

}
