package com.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringCloudApplication
@EnableFeignClients		//标识Feign客户端
public class ConsumerFeginRunApp {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerFeginRunApp.class, args);
	}

}
