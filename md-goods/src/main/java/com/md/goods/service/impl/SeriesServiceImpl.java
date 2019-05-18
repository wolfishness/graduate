package com.md.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.md.goods.dao.SeriesMapper;
import com.md.goods.model.Series;
import com.md.goods.service.ISeriesService;

@Service
public class SeriesServiceImpl extends ServiceImpl<SeriesMapper, Series> implements ISeriesService{

	@Resource
	SeriesMapper seriesMapper;
	
	@Override
	public List<Series> findAll(){
		Wrapper<Series> wrapper = new EntityWrapper<>();
		wrapper.eq("status", 1);
		wrapper.orderBy("id");
		return seriesMapper.selectList(wrapper);
	}
}
