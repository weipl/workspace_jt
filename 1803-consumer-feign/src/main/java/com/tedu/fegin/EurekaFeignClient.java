package com.tedu.fegin;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value="provider-user")
public interface EurekaFeignClient {
	//调用提供者的controller对应的方法
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable("name") String name);
}
