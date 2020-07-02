package com.example.demo.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.CustomerBoundary;
import com.example.demo.db.CountryDao;
import com.example.demo.db.CustomerDao;
import com.example.demo.exception.BadRequestException;

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
		// TODO Auto-generated method stub
		// check if mail is valid
		// check if date is valid
		//if(customer.getEmail().is)
		return null;
	}

	@Override
	public Mono<Customer> getCustomerByMail(String email) {
		return customerDB.findById(email);
	}

	@Override
	public Mono<Void> deleteAll() {
		countryDB.deleteAll();
		customerDB.deleteAll();
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
				throw new BadRequestException("Invalid age request");
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
			final int mAge = age;
			f.filter(new Predicate<Customer>() {
				
				@Override
				public boolean test(Customer t) {
					SimpleDateFormat f = new SimpleDateFormat(CustomerBoundary.birthdateFormat);
					Date d = null;
					try {
						d = f.parse(t.getBirthdate());
					} catch (ParseException e) {
						e.printStackTrace();
						return false;
					}
					Calendar c = Calendar.getInstance();
					c.add(Calendar.YEAR, -mAge);
					Date minDate = c.getTime();
					if(d.before(minDate) || d.compareTo(minDate) == 0) {
						return true;
					}
					return false;
				}
			});
			
		}
		f.sort(new Comparator<Customer>() {

			@Override
			public int compare(Customer o1, Customer o2) {
				return o1.getEmail().compareTo(o2.getEmail());
			}
		});
		return f;
	}


}

