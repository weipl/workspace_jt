package com.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient			//支持访问消费者
@SpringBootApplication
public class ConsumerClientRunApp {
	@Bean			//初始化模板对象，通过@Bean注解，spring创建这个对象实例
	@LoadBalanced 	//ribbon支持负载均衡算法，默认轮询
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerClientRunApp.class, args);
	}

}
