package com.ljs.guns.rest;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class GunsRestServletInitializer extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder builder){
		return builder.sources(GunsRestApplication.class);
	}
}
