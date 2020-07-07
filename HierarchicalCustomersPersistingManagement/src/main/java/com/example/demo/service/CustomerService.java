package com.example.demo.service;

import com.example.demo.models.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

	Mono<Customer> createNewCustomer(Customer customer);

	Mono<Customer> getCustomerByMail(String email);

	Mono<Void> deleteAll();

	Flux<Customer> getCustomers(String lastName, String minAge);

}
