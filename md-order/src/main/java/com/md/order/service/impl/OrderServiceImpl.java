package com.md.order.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.md.order.constant.OrderStatus;
import com.md.order.dao.OrderMapper;
import com.md.order.model.Order;
import com.md.order.service.IOrderService;
import com.ljs.guns.core.page.Page;
import com.ljs.guns.core.util.ToolUtil;

@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService{
	@Resource
	OrderMapper orderMapper;
	
	@Override
	public List<Order> findListByPage(Long memberId, Integer status, Integer index) {
		Wrapper<Order> wrapper = new EntityWrapper<>();
		if (ToolUtil.isNotEmpty(memberId)) {
			wrapper.eq("memberId", memberId);
		}
		if (ToolUtil.isNotEmpty(status)) {
			if (status != 10) {
				wrapper.eq("status", status);
			}
			
		}else {
			wrapper.ne("status", OrderStatus.DELETE.getCode());
		}
		wrapper.orderBy("createTime",false);
		Integer begin = (index - 1) * Page.PAGESIZE.getCode();
		RowBounds rowBounds = new RowBounds(begin,Page.PAGESIZE.getCode());
		return orderMapper.selectPage(rowBounds, wrapper);
	}
	
	@Override
	public List<Order> findShopListByPage(Integer status, Integer index) {
		Wrapper<Order> wrapper = new EntityWrapper<>();
		if (ToolUtil.isNotEmpty(status)) {
			if (status != 10) {
				wrapper.eq("status", status);
			}
			
		}else {
			wrapper.ne("status", OrderStatus.DELETE.getCode());
		}
		wrapper.orderBy("createTime",false);
		Integer begin = (index - 1) * Page.PAGESIZE.getCode();
		RowBounds rowBounds = new RowBounds(begin,Page.PAGESIZE.getCode());
		return orderMapper.selectPage(rowBounds, wrapper);
	}
	
	@Override
	public Integer selectOrderCount(Long memberId, Integer status) {
		// TODO 自动生成的方法存根
		Wrapper<Order> wrapper = new EntityWrapper<>();
		wrapper.eq("memberId", memberId);
		wrapper.eq("status", status);
		return orderMapper.selectCount(wrapper);
	}
	
	@Override
	public void add(Order order) {
		orderMapper.insert(order);
	}
	
	@Override
	public String findSumForMonth(Long goodsId){
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String sum = orderMapper.selectOneMonth(dateFormat.format(date), goodsId);
		if (ToolUtil.isNotEmpty(sum)) {
			return sum;
		}
		return "0";
	}
}
