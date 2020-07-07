package com.example.demo.service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.CountryDao;
import com.example.demo.db.CustomerDao;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.CustomerAlreadyExistsException;
import com.example.demo.models.Country;
import com.example.demo.models.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDB;
	private CountryDao countryDB;

	@Autowired
	public CustomerServiceImpl(CustomerDao customerDB, CountryDao countryDB) {
		super();
		this.customerDB = customerDB;
		this.countryDB = countryDB;
	}

	@Override
	public Mono<Customer> createNewCustomer(Customer customer) {
		if(!customer.isValid()) {
			return Mono.error(new BadRequestException("Bad email or birthdate format"));
		}
		
		 return customerDB.findByEmail(customer.getEmail())
				 // this default customer acts as a flag for the next operation
				 .defaultIfEmpty(new Customer("empty", "", "", "", "", ""))
				 .flatMap(cu -> {
					if(!cu.getEmail().equals("empty")) {
						return Mono.error(new CustomerAlreadyExistsException());
					}
					
					return countryDB.findByCountryCode(customer.getCountryCode())
							.defaultIfEmpty(new Country(customer.getCountryCode(), customer.getCountryName()))
							.flatMap(country -> {
								customer.setCountryName(country.getCountryName());
								customerDB.save(customer).subscribe();
								countryDB.save(country).subscribe();
								return Mono.just(customer);});
					
				});
	}

	@Override
	public Mono<Customer> getCustomerByMail(String email) {
		return customerDB.findByEmail(email);
	}

	@Override
	public Mono<Void> deleteAll() {
		countryDB.deleteAll().subscribe();
		customerDB.deleteAll().subscribe();
		return Mono.empty();
	}

	@Override
	public Flux<Customer> getCustomers(String lastName, String minAge) {
		Integer age = null;
		if(!minAge.isEmpty()) {
			try {
				age = Integer.valueOf(minAge);
			}
			catch(NumberFormatException e) {
				System.err.println(e);
				return Flux.error(new BadRequestException("Invalid age request"));
			}
		}
		
		Flux<Customer> f = null;
		if(!lastName.isEmpty()) {
			
			f = customerDB.findByLastName(lastName);
		}
		else {
			f = customerDB.findAll();
		}
		if(age != null) {
			int mAge = age.intValue();
			Calendar c = Calendar.getInstance();
			c.add(Calendar.YEAR, -mAge);
			c.set(Calendar.AM_PM, Calendar.AM);
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.MINUTE, 0);
			Date minDate = c.getTime();
			f = f.filter(customer -> customer.getBirthdayDate().before(minDate)).sort(new Comparator<Customer>() {

				@Override
				public int compare(Customer c1, Customer c2) {
					return c1.getEmail().compareTo(c2.getEmail());
				}
			});
		}
		return f;
	}


}

