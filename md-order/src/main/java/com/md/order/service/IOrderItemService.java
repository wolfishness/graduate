package com.md.order.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;
import com.md.order.model.OrderItem;

public interface IOrderItemService extends IService<OrderItem>{

	void addAll(Long orderId, Set<OrderItem> orderItems);

	Set<OrderItem> findByOrderId(Long orderId);

	List<Map<String, Object>> getListByOrderId(Long orderId, Long orderItemId);

	List<Map<String, Object>> getListByOrderId(Long orderId);


}
