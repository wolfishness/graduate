package com.md.settlement.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.md.cart.model.CartItem;
import com.md.goods.model.PriceTag;
import com.md.goods.model.Product;
import com.md.goods.service.IPriceTagService;
import com.md.goods.service.IProductService;

import com.md.member.model.Address;
import com.md.order.model.Order;
import com.md.order.model.OrderItem;
import com.md.settlement.exception.SettlementExceptionEnum;
import com.md.settlement.service.IAccountService;
import com.ljs.guns.core.exception.GunsException;
import com.ljs.guns.core.util.ToolUtil;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

	@Resource
	IPriceTagService priceTagService;

	@Resource
	IProductService productService;

	@Override
	public Order amount(List<CartItem> cartItems, Address address, Long memberId) {
		// 获取购买清单的价格签
		Map<Long, PriceTag> priceTags = this.findPriceTag(cartItems);
		Set<OrderItem> orderItems = new HashSet<>();
		BigDecimal dueTotal = new BigDecimal(0);
		BigDecimal actualPrice = new BigDecimal(0);

		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			Product product = productService.selectById(cartItem.getProductId());
			PriceTag priceTag = priceTagService.selectById(cartItem.getPriceTagId());

			orderItem.setGoodsId(product.getGoodsId());
			orderItem.setProductId(product.getId());
			orderItem.setActualPrice(priceTag.getMarketPrice());
			orderItem.setGoodsName(product.getName());
			orderItem.setUnitPrice(priceTag.getPrice());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setActualPay(priceTag.getMarketPrice().multiply(new BigDecimal(cartItem.getQuantity())));
			orderItem.setOriginalPay(priceTag.getPrice().multiply(new BigDecimal(cartItem.getQuantity())));

			dueTotal = dueTotal.add(orderItem.getActualPay());
			actualPrice = actualPrice.add(orderItem.getOriginalPay());

			orderItems.add(orderItem);

		}
		Timestamp createTime = new Timestamp(System.currentTimeMillis());

		Order order = new Order();
		order.setSerial(null);
		order.setCreateTime(createTime);
		order.setDue(dueTotal);
		order.setActualPay(actualPrice);
		order.setPackages(orderItems.size());
		order.setMemberId(memberId);
		order.setConsigneeName(address.getConsigeeName());
		order.setAddress(address.getAddress());
		order.setPhoneNum(address.getPhone());
		order.setOrderItems(orderItems);

		return order;
	}

	@Override
	public Boolean inventoryEnough(List<CartItem> cartItems, Map<Long, PriceTag> priceTags) {
		Boolean flag = true;
		for (CartItem cartItem : cartItems) {
			// 判断库存是否大于购买数量
			if (cartItem.getQuantity() > priceTags.get(cartItem.getProductId()).getInventory()) {
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public Map<Long, PriceTag> findPriceTag(List<CartItem> cartItems) {
		Map<Long, PriceTag> priceTags = new HashMap<>();
		for (CartItem cartItem : cartItems) {
			PriceTag priceTag = priceTagService.selectById(cartItem.getPriceTagId());

			priceTags.put(cartItem.getProductId(), priceTag);
		}
		return priceTags;
	}

}
