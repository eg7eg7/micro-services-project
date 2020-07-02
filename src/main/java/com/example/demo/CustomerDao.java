package com.example.demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerDao extends ReactiveMongoRepository<Customer, String>{

}
