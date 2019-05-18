package com.ljs.guns.rest.category;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ljs.guns.rest.GunsRestApplication;
import com.ljs.guns.rest.modular.category.ApiCategoryController;
import com.md.goods.service.ICategoryService;
import com.md.goods.service.ISeriesService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GunsRestApplication.class)
public class CategoryTest {
	@Resource
	ICategoryService categoryService;

	@Resource
	ISeriesService seriesService;

	@Resource
	ApiCategoryController apiCategoryController;

	@Test
	public void categoryTest() throws Exception {
		System.out.println(seriesService.findAll());
	}

}
