package com.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
	@Autowired	//必须调用提供者
	private RestTemplate restTemplate;
	
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name){
		//消费者之间调用提供者对外暴露的访问地址。VIP虚拟IP，类似域名
		String url = "http://provider-user/hello/"+name;
		return restTemplate.getForObject(url, String.class);
	}
}
