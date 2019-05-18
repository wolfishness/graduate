package com.md.goods.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ljs.guns.core.constant.CategoryStatus;
import com.ljs.guns.core.page.Page;
import com.ljs.guns.core.util.ToolUtil;
import com.md.goods.dao.GoodsMapper;
import com.md.goods.dao.ProductMapper;
import com.md.goods.model.Category;
import com.md.goods.model.Goods;
import com.md.goods.service.IGoodsService;
import com.md.goods.service.ILowerGoodsService;

@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
	@Resource
	GoodsMapper goodsMapper;

	@Resource
	ProductMapper productMapper;

	@Resource
	ILowerGoodsService lowerGoodsService;

	@Override
	public boolean existGoods(String name, Long goodsId) {
		Wrapper<Goods> wrapper = new EntityWrapper<>();
		wrapper.eq("name", name);
		List<Goods> goodsList = goodsMapper.selectList(wrapper);
		if (goodsList.size() > 0) {
			if (!goodsList.get(0).getId().equals(goodsId)) {
				return true;
			}

		}
		return false;
	}

	@Override
	public Long add(Goods goods) {
		goodsMapper.insert(goods);
		return goods.getId();
	}

	@Override
	public void edit(Goods goods) {
		goodsMapper.updateById(goods);
	}

	@Override
	public Boolean specIsUse(Long id) {
		Wrapper<Goods> wrapper = new EntityWrapper<>();
		wrapper.like("specItems", "{\"id\":\"" + id + "\",\"name\"");
		if (goodsMapper.selectCount(wrapper) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Map<String, Object>> goodsList(Long categoryId, Long brandId, String goodsName) {
		List<Map<String, Object>> goodsList = goodsMapper.goodsList(categoryId, brandId, goodsName);
		return goodsList;
	}

	@Override
	public List<Map<String, Object>> findBindPriceTag(List<Long> ids) {
		if (ToolUtil.isNotEmpty(ids)) {
			List<Map<String, Object>> priceTagList = goodsMapper.findBindPriceTag(ids);
			return priceTagList;
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getListByCate(Category category, Integer index) {
		Wrapper<Goods> wrapper = new EntityWrapper<>();
		Integer begin = (index - 1) * Page.PAGESIZE.getCode();
		if (category.getStatus() != CategoryStatus.DISABLE.getCode()) {
			// if (category.getLevel() == 3) {
			return goodsMapper.selectByCateOnStatus(category.getId(), begin);

			// }

		}
		wrapper.orderBy("id");
		RowBounds rowBounds = new RowBounds(begin, Page.PAGESIZE.getCode());
		return goodsMapper.selectMapsPage(rowBounds, wrapper);
	}

	@Override
	public List<Map<String, Object>> getListByName(String name, Integer index) {
		Wrapper<Goods> wrapper = new EntityWrapper<>();
		Integer begin = (index - 1) * Page.PAGESIZE.getCode();
		return goodsMapper.selectByName(name, begin);
	}

	@Override
	public List<Map<String, Object>> getListByTag(Long tagId, Long shopId, Integer index) {
		Wrapper<Goods> wrapper = new EntityWrapper<>();
		Integer begin = (index - 1) * Page.PAGESIZE.getCode();
		return goodsMapper.selectByTag(tagId, shopId, begin);
	}

	@Override
	public List<Map<String, Object>> getListByConditon(String name, String goodsId, String sn, Integer index,
			Integer pageSize) {
		Wrapper<Goods> wrapper = new EntityWrapper<>();

		if (ToolUtil.isNotEmpty(goodsId)) {
			wrapper.eq("id", goodsId);
		} else {
			if (ToolUtil.isNotEmpty(name)) {
				wrapper.like("name", name);
			}
			if (ToolUtil.isNotEmpty(sn)) {
				wrapper.like("sn", sn);
			}
		}
		wrapper.eq("isDel", 0);
		wrapper.orderBy("id");
		Integer size = Page.PAGESIZE.getCode();
		if (ToolUtil.isNotEmpty(pageSize)) {
			size = pageSize;
		}
		Integer begin = (index - 1) * size;
		RowBounds rowBounds = new RowBounds(begin, size);
		return goodsMapper.selectMapsPage(rowBounds, wrapper);
	}

	@Override
	public List<Goods> findBySn(String sn) {

		List<Map<String, Object>> goodsListMap = goodsMapper.findGoodsByGoodsCode(sn);

		List<Goods> goodsList = new ArrayList<Goods>();
		for (Map goodsMap : goodsListMap) {
			Goods goods = new Goods();
			goods.setBrandId(Long.parseLong(goodsMap.get("brandId").toString()));
			goods.setId(Long.parseLong(goodsMap.get("id").toString()));
			goods.setName(goodsMap.get("name").toString());
			goodsList.add(goods);
		}
		return goodsList;
	}

}
