package com.shinhan.sbproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAspectJAutoProxy //@Aspect 찾는다 
@ComponentScan({"com.shinhan.firstzone","com.shinhan.sbproject"}) //@Controller 찾는다
@EnableJpaRepositories({"com.shinhan.firstzone","com.shinhan.sbproject"}) //@Entity 찾는다
@SpringBootApplication
public class Sbproject1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sbproject1Application.class, args);
	}

}
