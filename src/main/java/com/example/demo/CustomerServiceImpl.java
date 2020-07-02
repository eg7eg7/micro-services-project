package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDB;

	@Autowired
	public CustomerServiceImpl(CustomerDao customerDB) {
		super();
		this.customerDB = customerDB;
	}

	@Override
	public Mono<Customer> createNewCustomer(Customer customer) {
		// TODO Auto-generated method stub
		// check if mail is valid
		// check if date is valid
		return null;
	}

	@Override
	public Mono<Customer> getCustomerByMail(String email) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Mono<Void> deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Customer> getCustomers(String lastName, String minAge) {
		// TODO Auto-generated method stub
		//check if parameters are valid and throw exceptions if not
		// min age should be a number
		// check if they are empty
		return null;
	}


}

