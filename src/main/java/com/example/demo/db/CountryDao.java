package com.example.demo.db;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.logic.Country;

public interface CountryDao extends ReactiveMongoRepository<Country, String> {

}