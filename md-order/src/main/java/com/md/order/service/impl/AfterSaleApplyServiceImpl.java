package com.md.order.service.impl;

import java.io.Console;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljs.guns.core.util.ToolUtil;
import com.md.order.dao.AfterSaleApplyMapper;
import com.md.order.model.AfterSaleApply;
import com.md.order.service.IAfterSaleApplyService;

/**
 * 对退货申请表进行操作
 * by ljs	20190219
 *
 */
@Service
@Transactional
public class AfterSaleApplyServiceImpl extends ServiceImpl<AfterSaleApplyMapper, AfterSaleApply> implements IAfterSaleApplyService{

	@Resource 
	AfterSaleApplyMapper afterSaleApplyMapper;
	
	@Override
	public int add(AfterSaleApply afterSaleApply) {
		return afterSaleApplyMapper.insertAfterSaleApply(afterSaleApply);
	}
	
	@Override 
	public AfterSaleApply findOne(long id) {
		return afterSaleApplyMapper.selectById(id);
	}
	
	@Override 
	public void edit(AfterSaleApply afterSaleApply) {
		afterSaleApplyMapper.updateById(afterSaleApply);
	}
	
	@Override
	public AfterSaleApply findInAfterSale(Long orderId,Long orderItemId){
		Wrapper<AfterSaleApply> wrapper = new EntityWrapper<>();
		wrapper.eq("orderId", orderId);
		wrapper.eq("orderItemId", orderItemId);
		wrapper.eq("status", 3);
		
		List<AfterSaleApply> lAfterSaleApplies  = afterSaleApplyMapper.selectList(wrapper);
		System.out.println(lAfterSaleApplies);
		if (ToolUtil.isNotEmpty(lAfterSaleApplies)) {
			return lAfterSaleApplies.get(0);
		}
		return null;
	}
}
