package com.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jt.common.vo.SysResult;

@RestController
public class CartController {
	@Autowired	//必须调用提供者
	private RestTemplate restTemplate;
	
	//我的购物车 /cart/query/{userId} RESTFul请求
	@RequestMapping("/query/{userId}")
	public SysResult myCart(@PathVariable Integer userId){
		String url = "http://provider-jt-cart/cart/query/"+userId;
		return restTemplate.getForObject(url, SysResult.class);
	}	
}



