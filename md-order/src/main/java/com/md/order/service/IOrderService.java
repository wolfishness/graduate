package com.md.order.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.md.order.model.Order;

public interface IOrderService extends IService<Order>{

	List<Order> findListByPage(Long memberId, Integer status, Integer index);

	Integer selectOrderCount(Long memberId, Integer status);

	void add(Order order);

}
