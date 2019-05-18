package com.md.goods.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.md.goods.dao.BrandMapper;
import com.md.goods.model.Brand;
import com.md.goods.service.IBrandService;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService{

}
