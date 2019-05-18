package com.md.goods.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.md.goods.dao.LowerGoodsMapper;
import com.md.goods.model.LowerGoods;
import com.md.goods.service.ILowerGoodsService;

@Service
public class LowerGoodsServiceImpl extends ServiceImpl<LowerGoodsMapper, LowerGoods> implements ILowerGoodsService{

}
