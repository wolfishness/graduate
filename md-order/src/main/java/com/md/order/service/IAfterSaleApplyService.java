package com.md.order.service;

import com.baomidou.mybatisplus.service.IService;
import com.md.order.model.AfterSaleApply;

/**
 * by ljs	20190219
 *
 */
public interface IAfterSaleApplyService extends IService<AfterSaleApply>{

	int add(AfterSaleApply afterSaleApply);

	AfterSaleApply findOne(long id);

	void edit(AfterSaleApply afterSaleApply);

}
