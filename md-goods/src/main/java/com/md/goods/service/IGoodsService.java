package com.md.goods.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.md.goods.model.Category;
import com.md.goods.model.Goods;
import com.md.goods.model.LowerGoods;

public interface IGoodsService extends IService<Goods>{

	/**
	 * 判断商品名称是否存在
	 * 
	 * @param goodsName
	 * @return
	 */
	boolean existGoods(String goodsName, Long goodsId);

	/**
	 * 添加商品
	 * 
	 * @param
	 * @return
	 */
	Long add(Goods goods);

	/**
	 * 修改商品
	 * 
	 * @param
	 * @return
	 */
	void edit(Goods goods);

	/**
	 * 判断规格规格项是被使用
	 * 
	 * @param
	 * @return
	 */
	Boolean specIsUse(Long id);

	/**
	 * 获取规格商品
	 * 
	 * @param categoryId
	 * @param brandId
	 * @param goodsName
	 */
	List<Map<String, Object>> goodsList(Long categoryId, Long brandId, String goodsName);

	/**
	 * 获取绑定的价格签
	 * 
	 * @param ids
	 * @return
	 */
	List<Map<String, Object>> findBindPriceTag(List<Long> ids);
	
	/**
	 *  通过类目id获取商品分页列表
	 * @param category 类目
	 * @param index 当前页
	 * @return
	 */
	List<Map<String, Object>> getListByCate(Category category,Integer index);
	
	/**
	 *  通过商品标签id获取商品分页列表
	 * @param tag 商品标签
	 * @return
	 */
	List<Map<String, Object>> getListByTag(Long tagId,Long shopId,Integer index);
	
	/**
	 * 通过商品名称获取商品列表
	 * @param name
	 * @param index
	 * @return
	 */
	List<Map<String, Object>> getListByName(String name,Integer index);

	/**
	 * 通过条件查询商品列表
	 * @param name 商品名
	 * @param goodsId 商品id
	 * @param sn  商品编号
	 * @param index  当前页
	 * @return
	 */
	List<Map<String, Object>> getListByConditon(String name, String goodsId,String sn, Integer index, Integer pageSize);
	
	List<Goods> findBySn(String sn);

	 //JSONObject judgeInventory(long shopId, List<Map<String, Object>> goods);

}
