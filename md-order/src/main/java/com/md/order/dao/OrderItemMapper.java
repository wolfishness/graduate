package com.md.order.dao;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.md.order.model.OrderItem;

public interface OrderItemMapper extends BaseMapper<OrderItem>{

	String selectRefundStatus(@Param("orderId") Long orderId);
}
