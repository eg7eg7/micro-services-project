package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.CustomerBoundary;
import com.example.demo.service.CustomerService;

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
			return customers.createNewCustomer(customer.toEntity()).map(val -> new CustomerBoundary(val));
		}
	

	@RequestMapping(
			path="/customers/{email}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
		public Mono<CustomerBoundary> getCustomerByEmail(@PathVariable("email") String email){
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
			return customers.getCustomers(lastName, minAge).map(val -> new CustomerBoundary(val));
	}
	
}
