package com.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
public class FeignRunApp {
	public static void main(String[] args) {
		SpringApplication.run(FeignRunApp.class, args);
	}
}
