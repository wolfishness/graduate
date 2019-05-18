package com.md.goods.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.md.goods.model.Goods;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsMapper extends BaseMapper<Goods>{

	/**
     * 获取最大sn码
     * @param currentTime
     * @return
     */
    String  getMaxSn(@Param("currentTime")String currentTime);

    /**
     * 促销关系绑定获取商品列表
     * @param categoryId
     * @param brandId
     * @param goodsName
     */
    List<Map<String,Object>> goodsList(@Param("categoryId")Long categoryId, @Param("brandId") Long brandId, @Param("goodsName")String goodsName);

    /**
     * 获取绑定的价格签
     * @param ids
     * @return
     */
    List<Map<String, Object>> findBindPriceTag(@Param("ids")List<Long> ids);
    
    
    List<Map<String, Object>> findGoodsByGoodsCode(@Param("goodsCode")String goodsCode);
    
    /**
     * 根据cate获取列表信息
     * 去掉数量为0的商品
     * @param categoryId
     * @param shopId
     * @param begin
     * @return
     */
    List<Map<String, Object>> selectByCateOnStatus(@Param("categoryId") Long categoryId,@Param("begin") int begin);

    /**
     * 根据tag获取列表信息
     * 去掉数量为0的商品
     * @param tagId
     * @param shopId
     * @param begin
     * @return
     */
    List<Map<String, Object>> selectByTag(@Param("tagId") Long tagId ,@Param("shopId") Long shopId ,@Param("begin") int begin);
    
    /**
     * 根据name获取列表信息
     * 去掉数量为0的商品
     * @param name
     * @param shopId
     * @param begin
     * @return
     */
    List<Map<String, Object>> selectByName(@Param("name") String name ,@Param("begin") int begin);
    
}
