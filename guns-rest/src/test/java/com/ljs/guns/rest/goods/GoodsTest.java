package com.ljs.guns.rest.goods;

import static org.assertj.core.api.Assertions.linesOf;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ljs.guns.core.util.ToolUtil;
import com.ljs.guns.rest.GunsRestApplication;
import com.ljs.guns.rest.modular.goods.ApiGoodsController;
import com.ljs.guns.rest.modular.goods.dto.GoodsRequest;
import com.md.goods.dao.GoodsMapper;
import com.md.goods.model.Category;
import com.md.goods.model.Goods;
import com.md.goods.model.Product;
import com.md.goods.service.IGoodsService;
import com.md.goods.service.IProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GunsRestApplication.class)
public class GoodsTest {

	@Resource
	ApiGoodsController apiGoodsController;
	
	@Resource
	IGoodsService goodsService;
	
	@Resource
	IProductService productService;
	
	@Resource
	GoodsMapper goodsMapper;
	
	//@Test
	public void Goods() throws Exception{
		GoodsRequest goodsRequest = new GoodsRequest();
		goodsRequest.setGoodsId((long)1);
		System.out.println(apiGoodsController.getProductList(goodsRequest).toString());
		System.out.println(apiGoodsController.getGoodDetail(goodsRequest).toString());
		
		goodsRequest.setCateId((long)3);
		goodsRequest.setIndex(1);
		System.out.println(apiGoodsController.getListByCategory(goodsRequest).toString());
	}
	
	//@Test
	public void goods() throws Exception{
		System.out.println(goodsMapper.findGoodsByGoodsCode("jeans1"));
	}
	
	//@Test
	public void goodsProduct() throws Exception{
		Category category= new Category();
		category.setId((long)1);
		category.setStatus(1);
		List<Map<String, Object>> goodsList = goodsService.getListByCate(category, 2);
		for (Map<String, Object> map : goodsList) {
			Wrapper<Product> wrapper = new EntityWrapper<>();
			wrapper.eq("goodsId", map.get("id"));
			Product product = productService.selectOne(wrapper);
			if (ToolUtil.isEmpty(product)) {
				product = new Product();
				product.setGoodsCode(map.get("goodsCode").toString());
				product.setGoodsId(Long.parseLong(map.get("id").toString()));
				product.setMarketPrice(new BigDecimal(map.get("marketPrice").toString()));
				product.setName("黑色");
				product.setPrice(new BigDecimal(map.get("price").toString()));
				product.setWeight(new BigDecimal(2));
				product.setGoodsSize("L");
				productService.insert(product);
			}
		}
	}
	
	//@Test
	public void productList() throws Exception{
		GoodsRequest goodsRequest = new GoodsRequest();
		goodsRequest.setGoodsId((long)4);
		System.out.println(apiGoodsController.getProductList(goodsRequest));
		//System.out.println();
	}
	
	@Test
	public void jsonTest() throws Exception{
		/*JSONObject jsonObject = new JSONObject();
		JSONObject jObject = new JSONObject();
		
		jsonObject.put("test1", "test1");
		jsonObject.put("test2", "test2");
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonObject);
		jsonArray.add("dsds2");
		System.out.println(jsonArray);
		
		
		
		
		
		jObject.put("test", jsonObject);
		
		System.out.println(jObject);*/
		
		System.out.println(productService.findColorsNum((long)4));
	}
	
}
