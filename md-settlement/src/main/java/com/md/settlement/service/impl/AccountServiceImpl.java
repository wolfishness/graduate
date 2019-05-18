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


	//@Override
	public Order amount(List<CartItem> cartItems, Address address) {
		// 获取购买清单的价格签
		Map<Long, PriceTag> priceTags = this.findPriceTag(cartItems);
		Set<OrderItem> orderItems = new HashSet<>();
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
			
			orderItems.add(orderItem);
			
		}
		
		Order order = new Order();
		order.set
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


	@Override
	public DeliveryCost getDeliveryCost(DeliveryMode deliveryMode, Address address, Long shopId) {
		DeliveryCost shopCountyCost = deliveryCostService.getCost(deliveryMode.getId(), address.getCounty(), shopId,
				null);
		if (ToolUtil.isNotEmpty(shopCountyCost)) {
			return shopCountyCost;
		}
		DeliveryCost shopCityCost = deliveryCostService.getCost(deliveryMode.getId(), address.getCity(), shopId, null);
		if (ToolUtil.isNotEmpty(shopCityCost)) {
			return shopCityCost;
		}
		DeliveryCost shopProvinceCost = deliveryCostService.getCost(deliveryMode.getId(), address.getProvince(), shopId,
				null);
		if (ToolUtil.isNotEmpty(shopProvinceCost)) {
			return shopProvinceCost;
		}
		DeliveryCost adminCountyCost = deliveryCostService.getCost(deliveryMode.getId(), address.getCounty(),
				Long.valueOf("0"), null);
		if (ToolUtil.isNotEmpty(adminCountyCost)) {
			return adminCountyCost;
		}
		DeliveryCost adminCityCost = deliveryCostService.getCost(deliveryMode.getId(), address.getCity(),
				Long.valueOf("0"), null);
		if (ToolUtil.isNotEmpty(adminCityCost)) {
			return adminCityCost;
		}
		DeliveryCost adminProvinceCost = deliveryCostService.getCost(deliveryMode.getId(), address.getProvince(),
				Long.valueOf("0"), null);
		if (ToolUtil.isNotEmpty(adminProvinceCost)) {
			return adminProvinceCost;
		}
		return null;
	}

	public Order deliverySattlement(Order order, DeliveryMode deliveryMode, Address address, Long shopId) {
		// 获取配送费对象
		DeliveryCost deliveryCost = this.getDeliveryCost(deliveryMode, address, shopId);
		// 计算出订单的总重量
		for (OrderItem orderItem : order.getOrderItems()) {
			Product product = productService.findById(orderItem.getProductId());
			order.setWeight(order.getWeight().add(product.getWeight().multiply(new BigDecimal(orderItem.getQuantity())))
					.setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		if (ToolUtil.isNotEmpty(deliveryCost)) {
			// 计算运费
			if (order.getWeight().compareTo(deliveryCost.getYkg()) <= 0 || ToolUtil.isNotEmpty(deliveryCost.getYkg())
					|| deliveryCost.getYkg().equals(0) || deliveryCost.getYkg().compareTo(new BigDecimal(0)) == 0) {
				order.setDiliveryPay(deliveryCost.getStartPrice());
			} else {
				BigDecimal overstepWeight = order.getWeight().subtract(deliveryCost.getYkg());
				if (deliveryCost.getAddedWeight().compareTo(new BigDecimal(0)) > 0) {
					BigDecimal overStepPrice = new BigDecimal(
							Math.ceil(overstepWeight.divide(deliveryCost.getAddedWeight()).doubleValue()))
									.multiply(deliveryCost.getAddedPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
					order.setDiliveryPay(deliveryCost.getStartPrice().add(overStepPrice));
				} else {
					order.setDiliveryPay(deliveryCost.getStartPrice());
				}
			}
			order.setActualPay(order.getDue().add(order.getDiliveryPay()));
		} else {
			DeliveryMode mode = deliveryModeService.getById(deliveryMode.getId());
			order.setDiliveryPay(mode.getPrice());
			order.setActualPay(order.getDue().add(order.getDiliveryPay()));
		}
		order.setConsigneeName(address.getConsigeeName());
		order.setAddress(address.getAddressName() + address.getDetail());
		order.setPhoneNum(address.getPhone());
		order.setDiliveryId(deliveryMode.getId());
		return order;
	}


}
