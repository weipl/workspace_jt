package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.jt.mapper")		//mybatis扫描所有接口文件
public class CartRunApp {

	public static void main(String[] args) {
		SpringApplication.run(CartRunApp.class, args);
	}

}
