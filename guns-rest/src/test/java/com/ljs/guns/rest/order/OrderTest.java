package com.ljs.guns.rest.order;

import static org.mockito.Matchers.doubleThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ljs.guns.rest.GunsRestApplication;
import com.md.order.service.IOrderService;
import com.md.order.service.impl.OrderServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GunsRestApplication.class)
public class OrderTest {

	@Resource
	IOrderService orderService;
	
	
	@Test
	public void orderT() throws Exception{
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		System.out.println(dateFormat.format(date));
		System.out.println(date);
		System.out.println(orderService.findSumForMonth((long)4));
	}
}
