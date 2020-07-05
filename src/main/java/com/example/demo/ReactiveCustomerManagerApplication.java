package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveCustomerManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveCustomerManagerApplication.class, args);
	}
}
