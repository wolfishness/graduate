package com.md.order.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
	
}
