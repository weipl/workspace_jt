package com.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tedu.fegin.EurekaFeignClient;


@RestController
public class HelloController {
	//调用接口方法
	@Autowired
	private EurekaFeignClient feignClient;
	
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name){
		return feignClient.hello(name);
	}
	
}
