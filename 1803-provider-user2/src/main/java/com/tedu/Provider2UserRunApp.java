package com.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient		//提供者是作为Eureka客户端程序体现
@SpringBootApplication
public class Provider2UserRunApp {

	public static void main(String[] args) {
		SpringApplication.run(Provider2UserRunApp.class, args);
	}

}
