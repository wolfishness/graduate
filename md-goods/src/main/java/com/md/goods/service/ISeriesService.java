package com.md.goods.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.md.goods.model.Series;

public interface ISeriesService extends IService<Series>{

	List<Series> findAll();

}
