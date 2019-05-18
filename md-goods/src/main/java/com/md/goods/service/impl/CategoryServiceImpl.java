package com.md.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljs.guns.core.constant.CategoryStatus;
import com.md.goods.dao.CategoryMapper;
import com.md.goods.model.Category;
import com.md.goods.service.ICategoryService;

@Service
@Transactional
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService{

	@Resource
	CategoryMapper categoryMapper;
	
	@Override
	public List<Category> findCategoryList(Long id) {
		Wrapper<Category> wrapper = new EntityWrapper<Category>();
		wrapper.eq("seriesId", id);
		wrapper.eq("status", CategoryStatus.ENABLE.getCode());
		List<Category> selectList = categoryMapper.selectList(wrapper);
		return selectList;
	}
	
	//@Override
	public List<Category> findCategorySecondList(Long id){
		Wrapper<Category> wrapper = new EntityWrapper<>();
		wrapper.eq("status", CategoryStatus.ENABLE.getCode());
		wrapper.eq("id", id);
		return null;
	}
}
