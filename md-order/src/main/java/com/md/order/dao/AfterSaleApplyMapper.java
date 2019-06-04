package com.md.order.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.md.order.model.AfterSaleApply;

public interface AfterSaleApplyMapper extends BaseMapper<AfterSaleApply>{

	int insertAfterSaleApply(AfterSaleApply afterSaleApply);
}
