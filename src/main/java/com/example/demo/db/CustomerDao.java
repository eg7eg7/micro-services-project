package com.example.demo.db;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.logic.Customer;

import reactor.core.publisher.Flux;

public interface CustomerDao extends ReactiveMongoRepository<Customer, String> {

	Flux<Customer> findByLastName(String lastName);
}
