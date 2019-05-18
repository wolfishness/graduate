package com.ljs.guns.rest.modular.settle;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ljs.guns.rest.modular.settle.dto.SettleRequest;
import com.md.cart.model.CartItem;
import com.md.goods.service.IProductService;
import com.md.settlement.service.IAccountService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/settlement")
public class ApiSettlementController {

	@Resource
	IAccountService accountService;

	@Resource
	IProductService productService;


	@ApiOperation(value = "结算", notes = "结算")
	@RequestMapping(value = "/account", method = { RequestMethod.POST })
	public ResponseEntity<?> orderItemSubmit(@RequestBody SettleRequest settleRequest) {

		String []arr =settleRequest.getCartItemList().split(",");
		for(int i = 0 ; i < arr.length ; i ++){
			
		}
		
		
		return ResponseEntity.ok("支付成功");
		
		
	}
}
