package com.tedu.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	//RESTFul請求,返回值json,立面是個字符串
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return "hello "+ name;
	}
}
