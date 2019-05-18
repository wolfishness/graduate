package com.md.order.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.md.order.model.RefundApply;

public interface IRefundApplyService extends IService<RefundApply>{

	List<RefundApply> findMemberRefundApplyToo(Long memberId);

	Integer selectOrderCount(Long memberId, String statusArray);

}
