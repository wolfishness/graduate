package com.ljs.guns.rest.cart;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ljs.guns.rest.GunsRestApplication;
import com.ljs.guns.rest.modular.cart.ApiCartController;
import com.ljs.guns.rest.modular.cart.dto.CartRequest;
import com.md.cart.model.Cart;
import com.md.cart.service.ICartService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GunsRestApplication.class)
public class CartTest {

	@Resource
	ICartService cartService;
	
	@Resource
	ApiCartController apiCartController;
	
	//@Test
	public void cartTest() throws Exception{
		/*Cart cart = new Cart();
		cart = cartService.addSave((long) 1);
		System.out.println(cart.getId());*/
		CartRequest cartRequest = new CartRequest();
		cartRequest.setMemberId((long)1);
		cartRequest.setProductId((long)1);
		cartRequest.setQuantity(1);
		System.out.println(apiCartController.addCart(cartRequest));
	}
	
	@Test
	public void cart() throws Exception{
		CartRequest cartRequest = new CartRequest();
		cartRequest.setCartId((long)1);
		System.out.println(apiCartController.getCartList(cartRequest));
	}
}
