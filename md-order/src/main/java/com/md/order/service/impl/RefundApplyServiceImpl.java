package com.md.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.md.order.dao.RefundApplyMapper;
import com.md.order.model.RefundApply;
import com.md.order.service.IRefundApplyService;
import com.ljs.guns.core.util.ToolUtil;

@Service
public class RefundApplyServiceImpl extends ServiceImpl<RefundApplyMapper, RefundApply> implements IRefundApplyService {
	@Resource
	RefundApplyMapper refundApplyMapper;

	@Override
	public List<RefundApply> findMemberRefundApplyToo(Long memberId) {
		// TODO 自动生成的方法存根
		Wrapper<RefundApply> wrapper = new EntityWrapper<>();
		if (ToolUtil.isNotEmpty(memberId)) {
			wrapper.eq("memberId", memberId);
		}
		wrapper.orderBy("createTime");
		return refundApplyMapper.selectList(wrapper);
	}

	@Override
	public Integer selectOrderCount(Long memberId, String statusArray) {
		// TODO 自动生成的方法存根
		Wrapper<RefundApply> wrapper = new EntityWrapper<>();
		wrapper.eq("memberId", memberId);
		wrapper.in("status", statusArray);
		return refundApplyMapper.selectCount(wrapper);
	}
}
