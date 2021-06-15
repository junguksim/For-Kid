package com.ssafy.happyhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class HappyHouseSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyHouseSpringApplication.class, args);
	}

}
