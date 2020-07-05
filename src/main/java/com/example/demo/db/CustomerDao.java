package com.example.demo.db;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.logic.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerDao extends ReactiveMongoRepository<Customer, String> {

	Mono<Customer> findByEmail(String email);
	Flux<Customer> findByLastName(String lastName);
}
