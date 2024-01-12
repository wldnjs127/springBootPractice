package com.shinhan.sbproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.shinhan.firstzone","com.shinhan.sbproject"})
@EnableJpaRepositories({"com.shinhan.firstzone","com.shinhan.sbproject"})
@SpringBootApplication
public class Sbproject1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sbproject1Application.class, args);
	}

}
