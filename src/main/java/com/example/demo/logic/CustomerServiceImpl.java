package com.example.demo.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;

import org.apache.commons.lang3.BooleanUtils;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.db.CountryDao;
import com.example.demo.db.CustomerDao;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.CustomerAlreadyExistsException;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.utils.Util;
import com.mongodb.reactivestreams.client.internal.Publishers;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

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
		
		 return customerDB.findByEmail(customer.getEmail()).defaultIfEmpty(new Customer("empty", "", "", "", "", ""))
		.flatMap(cu -> {
			if(!cu.getEmail().equals("empty")) {
				return Mono.error(new CustomerAlreadyExistsException());
			}
			Mono<Country> countryFromDB = countryDB.findByCountryCode(customer.getCountryCode())
					.defaultIfEmpty(new Country(customer.getCountryCode(), customer.getCountryName()));
			return countryFromDB.flatMap(country -> {
				customer.setCountryName(country.getCountryName());
				customerDB.save(customer).subscribe();
				countryDB.save(country).subscribe();
				return Mono.just(customer);
			});
		});
	}

	@Override
	public Mono<Customer> getCustomerByMail(String email) {
		return customerDB.findByEmail(email).switchIfEmpty(Mono.error(new CustomerNotFoundException()));
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

