package com.juanem.springboot.app.item.springbootitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SpringBootItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootItemApplication.class, args);
	}

}
