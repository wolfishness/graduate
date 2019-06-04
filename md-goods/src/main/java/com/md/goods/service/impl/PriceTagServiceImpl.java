package com.md.goods.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljs.guns.core.util.ToolUtil;
import com.md.goods.dao.PriceTagMapper;
import com.md.goods.dao.ProductMapper;
import com.md.goods.model.Goods;
import com.md.goods.model.PriceTag;
import com.md.goods.model.Product;
import com.md.goods.service.IGoodsService;
import com.md.goods.service.IPriceTagService;

@Service
public class PriceTagServiceImpl extends ServiceImpl<PriceTagMapper, PriceTag> implements IPriceTagService{

	@Resource
	PriceTagMapper priceTagMapper;
	
	@Resource
	ProductMapper productMapper;
	
	@Resource
	IGoodsService goodsService;
	
	@Resource
	IPriceTagService priceTagService;
	
	public void createPriceTagRecord(Long goodsId) {
		Wrapper<PriceTag> wrapper = new EntityWrapper<>();
		wrapper.eq("goodsId", goodsId);
		List<PriceTag> pList = null;
		try {
			pList = priceTagMapper.selectList(wrapper);
			if (ToolUtil.isEmpty(pList)) {
				System.out.println("查到数据为空！");
				Wrapper<Product> pWrapper = new EntityWrapper<>();
				pWrapper.eq("goodsId", goodsId);
				
				List<Product> products = productMapper.selectList(pWrapper);
				List<PriceTag> priceTagInsert = new ArrayList<>();
				
				
				for (Product product : products) {
					PriceTag priceTag = new PriceTag();
					priceTag.setThreshold(0);
					priceTag.setStatus(0);;
					priceTag.setInventory(100);
					priceTag.setGoodsId(goodsId);
					priceTag.setProductId(product.getId());
					priceTag.setMarketPrice(product.getMarketPrice());
					priceTag.setPrice(product.getPrice());
					priceTag.setGoodsCode(product.getGoodsCode());
					priceTag.setGoodsSize(product.getGoodsSize());
					priceTag.setStatus(1);
					priceTag.setThreshold(0);
					priceTag.setMarketTable(new BigDecimal(1));
					priceTagInsert.add(priceTag);
				}
				
				boolean res = false;
				// 执行插入语句
				res = priceTagService.insertBatch(priceTagInsert, priceTagInsert.size());
				if (res) {
					System.out.println("成功初始化数据！");
				} else {
					System.out.println("初始化数据失败！");
				}
			}
		} catch (Exception e) {
			System.out.println("查找数据出错！");
		}

	}
	
	@Override
	public void add(PriceTag priceTag) {
		priceTagMapper.insert(priceTag);
	}
	
	@Override
	public void addInventory(Long productId, Integer amount) {
		PriceTag priceTag = new PriceTag();
		priceTag.setProductId(productId);
		PriceTag tag = priceTagMapper.selectOne(priceTag);
		tag.setInventory(tag.getInventory() + amount);
		edit(tag);
	}
	
	@Override
	public PriceTag reduceInventory(Long productId,Integer amount) {
		PriceTag priceTag = new PriceTag();
		priceTag.setProductId(productId);
		PriceTag tag = priceTagMapper.selectOne(priceTag);
		tag.setInventory(tag.getInventory() - amount);

		edit(tag);
		return tag;
	}
	
	@Override
	public void edit(PriceTag priceTag) {
		priceTagMapper.updateById(priceTag);
	}
	
	@Override
	public PriceTag findByProductId(Long productId){
		Wrapper<PriceTag> wrapper = new EntityWrapper<>();
		wrapper.eq("productId", productId);
		
		List<PriceTag> selectList = priceTagMapper.selectList(wrapper);
		if (selectList.size() >0) {
			return selectList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public PriceTag findByGoodsCode(String goodsCode){
		PriceTag priceTag = new PriceTag();
		priceTag.setGoodsCode(goodsCode);
		Wrapper<Goods> wrapper = new EntityWrapper<>();
		wrapper.eq("goodsCode", goodsCode);
		Goods goods = goodsService.selectOne(wrapper);
		createPriceTagRecord(goods.getId());//初始化数据
		//查找priceTag数据
		Wrapper<PriceTag> priceTagWrapper = new EntityWrapper<>();
		priceTagWrapper.eq("goodsId", goods.getId());
		List<PriceTag> pList = priceTagService.selectList(priceTagWrapper);
		if (ToolUtil.isNotEmpty(pList)) {
			return pList.get(0);
		}else {
			return null;
		}
		
	}
	
	@Override
	public List<PriceTag> getPriceTagListByGoodsId(Long goodsId){
		createPriceTagRecord(goodsId);
		Wrapper<PriceTag> wrapper = new EntityWrapper<>();
		
		wrapper.eq("goodsId", goodsId);
		wrapper.eq("marketTable", 1);
		wrapper.orderBy("goodsSize");
		List<PriceTag> pList = priceTagMapper.selectList(wrapper);
		
		return pList;
	}
	
	/*@Override
	public List<PriceTag> getPriceTagListByGoodsIdAndProductId(){
		
	}*/
}
