package com.ljs.guns.rest.modular.category;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.md.goods.model.Category;
import com.md.goods.model.Series;
import com.md.goods.service.ICategoryRelationService;
import com.md.goods.service.ICategoryService;
import com.md.goods.service.ISeriesService;

import io.swagger.annotations.ApiOperation;

import com.alibaba.fastjson.JSONObject;
import com.ljs.guns.core.util.ToolUtil;
import com.ljs.guns.rest.modular.category.dto.CategoryRequest;

/**
 * 分类接口
 *
 */
@RestController
@RequestMapping("/category")
public class ApiCategoryController{

	@Resource
	ICategoryService categoryService;
	
	@Resource
	ICategoryRelationService categoryRelationService;
	
	@Resource
	ISeriesService seriesService;
	
	@RequestMapping(value = "/category/getCategoryList",method = RequestMethod.POST)
	public ResponseEntity<?> getCategoryList(@RequestBody CategoryRequest categoryRequest) {
		List<Category> result = categoryService.findCategoryList(categoryRequest.getSeriesId());
		if(categoryRequest.getSeriesId() != 0 ) {
			for(Category category : result ) {
				List<Category> list = categoryService.findCategoryList(category.getId());
				category.setList(list);
			}
		}
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/category/getAllCategoryList",method = RequestMethod.POST)
	public ResponseEntity<?> getAllCategoryList(@RequestBody CategoryRequest categoryRequest) {
		List<Category> result = categoryService.findCategoryList(categoryRequest.getSeriesId());
		for(Category category : result ) {
			List<Category> list = categoryService.findCategoryList(category.getId());
			if(list.size() > 0) {
				for(Category categorys : list ) {
					List<Category> lists = categoryService.findCategoryList(categorys.getId());
					categorys.setList(lists);
				}
			}
			category.setList(list);
		}
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation(value = "获取二级分类目录", notes = "获取二级分类目录")
	@RequestMapping(value = "/getCategorySecond", method = { RequestMethod.POST })
	public ResponseEntity<?> getCategorySecond(@RequestBody CategoryRequest categoryRequest){
		List<Category> result = categoryService.findCategoryList(categoryRequest.getSeriesId());
		return ResponseEntity.ok(result);
	}
	
	@ApiOperation(value = "获取一级分类目录", notes = "获取一级分类目录")
	@RequestMapping(value = "/getSeries", method = { RequestMethod.POST })
	public ResponseEntity<?> getSeries(){
		List<Series> sList = seriesService.findAll();
		return ResponseEntity.ok(sList);
	}
	
	@ApiOperation(value = "获取分类目录", notes = "获取分类目录")
	@RequestMapping(value = "/getAllCategory", method = { RequestMethod.POST })
	public ResponseEntity<?> getAllCategory(){
		JSONObject jObject = new JSONObject();
		List<Series> sList = seriesService.findAll();
		jObject.put("navLeftItems", sList);
		List<Category> categories = new ArrayList<>();
		
		if (ToolUtil.isNotEmpty(sList)) {
			for (Series series : sList) {
				List<Category> cList = categoryService.findCategoryList(series.getId());
				Category category = new Category();
				category.setName(series.getName());
				category.setStatus(series.getStatus());
				category.setList(cList);
				categories.add(category);
			}
		}
		
		jObject.put("navRightItems", categories);
		return ResponseEntity.ok(jObject);
	}
}

