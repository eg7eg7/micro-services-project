package com.example.demo.logic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

	Mono<Customer> createNewCustomer(Customer customer);

	Mono<Customer> getCustomerByMail(String email);

	Mono<Void> deleteAll();

	Flux<Customer> getCustomers(String lastName, String minAge);

}
