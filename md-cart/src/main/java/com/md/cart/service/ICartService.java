package com.md.cart.service;

import com.baomidou.mybatisplus.service.IService;
import com.md.cart.model.Cart;

public interface ICartService extends IService<Cart>{

	Cart addSave(Long memberId);

	Cart findByMemberId(Long memberId);

}
