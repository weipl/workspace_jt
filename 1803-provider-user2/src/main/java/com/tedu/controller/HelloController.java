package com.tedu.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	//RESTFul请求，返回值json，里面就是一个字符串
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name){
		return "2 hello "+name;
	}
}
