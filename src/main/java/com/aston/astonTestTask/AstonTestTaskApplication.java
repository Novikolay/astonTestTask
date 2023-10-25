package com.aston.astonTestTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan("application")
//@EnableJpaRepositories("healthchecker")
//@EntityScan("customer")
@SpringBootApplication
public class AstonTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AstonTestTaskApplication.class, args);
	}

}
