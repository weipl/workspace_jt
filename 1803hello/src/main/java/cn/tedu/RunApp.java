package cn.tedu; //必须放在包扫描这些目录之前（父目录）

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 创建web环境，创建上下文对象
public class RunApp {
	public static void main(String[] args) {
		//启动web服务，tomcat
		SpringApplication.run(RunApp.class, args);
	}
}
