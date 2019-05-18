package com.md.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.md.order.dao.EvaluationMapper;
import com.md.order.model.Evaluation;
import com.md.order.service.IEvaluationService;
import com.ljs.guns.core.page.Page;
import com.ljs.guns.core.util.ToolUtil;

@Service
@Transactional
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements IEvaluationService {

	@Resource
	EvaluationMapper evaluationMapper;
	
	@Override
	public List<Map<String, Object>> findList(Evaluation evaluation) {
		Wrapper<Evaluation> wrapper = new EntityWrapper<>();
		if (ToolUtil.isNotEmpty(evaluation.getGoodsId())) {
			wrapper.eq("goodsId", evaluation.getGoodsId());
		}
		return  evaluationMapper.selectMaps(wrapper);
	}

	@Override
	public List<Evaluation> findListByPage(Long goodsId, Integer index) {

		Wrapper<Evaluation> wrapper = new EntityWrapper<>();
		if (ToolUtil.isNotEmpty(goodsId)) {
			wrapper.eq("goodsId", goodsId);
		}
		wrapper.orderBy("createTime",false);
		Integer begin = (index - 1) * Page.PAGESIZE.getCode();
		RowBounds rowBounds = new RowBounds(begin,Page.PAGESIZE.getCode());
		return evaluationMapper.selectPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> findListByPage2(Long goodsId,Integer index) {
		Wrapper<Evaluation> wrapper = new EntityWrapper<>();
		if (ToolUtil.isNotEmpty(goodsId)) {
			wrapper.eq("goodsId", goodsId);
		}
		wrapper.orderBy("createTime",false);
		Integer begin = (index - 1) * Page.PAGESIZE.getCode();
		RowBounds rowBounds = new RowBounds(begin,Page.PAGESIZE.getCode());
		return evaluationMapper.selectMapsPage(rowBounds, wrapper);
	}
}
