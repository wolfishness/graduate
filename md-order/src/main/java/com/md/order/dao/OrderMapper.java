package com.md.order.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.md.order.model.Order;

public interface OrderMapper extends BaseMapper<Order>{

	/**
	 * 获取月销量
	 * @param payTime
	 * @param goodsId
	 * @return
	 */
	String selectOneMonth (@Param("payTime")String payTime,@Param("goodsId")Long goodsId);
}
