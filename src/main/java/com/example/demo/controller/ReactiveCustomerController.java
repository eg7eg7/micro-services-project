package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.exception.BadRequestException;
import com.example.demo.logic.Customer;
import com.example.demo.logic.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveCustomerController {
	private static final String byLastName = "byLastName";
	private static final String byAgeGreaterThan = "byAgeGreaterThan";
	
	private CustomerService customers;
	
	@Autowired
	public void setCustomers(CustomerService c) {
		this.customers = c;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(
			path="/customers",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public Mono<CustomerBoundary> createNewCustomer(@RequestBody CustomerBoundary customer){
		
		/* 
		 *  Returns Exception 500 if customer already exists
		 *  
		 *  
		 *  Creates a new country code if it does not exist, uses the old country name if already does
		 * */
			return customers.createNewCustomer(customer.toEntity()).map(val -> new CustomerBoundary(val));
		}
	

	@RequestMapping(
			path="/customers/{email}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public Mono<CustomerBoundary> getCustomerByEmail(@PathVariable("email") String email){
		
		/* 
		 *  Retrieves a customer by email, a void Mono if does not exist
		 *  
		 *  Returns 401 if bad request
		 * */
		
			return customers.getCustomerByMail(email).map(val -> new CustomerBoundary(val));
	}
	
	@RequestMapping(
			path="/customers",
			method = RequestMethod.DELETE)
		public Mono<Void> deleteCustomers(){
			return customers.deleteAll();
	}
	
	@RequestMapping(
			path="/customers",
			method = RequestMethod.GET,
			produces = MediaType.TEXT_EVENT_STREAM_VALUE)
		public Flux<CustomerBoundary> getCustomers(
				@RequestParam(name = byLastName, required = false, defaultValue = "") String lastName,
				@RequestParam(name = byAgeGreaterThan, required = false, defaultValue = "") String minAge){
		
		/* 
		 *  Retrieves a customer by email, a void Mono if does not exist
		 * */
		
			return customers.getCustomers(lastName, minAge).map(val -> new CustomerBoundary(val));
	}
	
}
