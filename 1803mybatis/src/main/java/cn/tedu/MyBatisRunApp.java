package cn.tedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="cn.tedu.mybatis.mapper")	//包扫描,扫描所有mybatis接口
public class MyBatisRunApp {
	public static void main(String[] args) {
		SpringApplication.run(MyBatisRunApp.class, args);
	}
}
