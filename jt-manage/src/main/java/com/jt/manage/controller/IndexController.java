package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	//跳转到后台首页
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/page/{moduleName}")
	public String index(@PathVariable String moduleName) {
		return moduleName;
	}
	
}
