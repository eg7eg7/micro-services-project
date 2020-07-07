package com.example.demo.db;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.models.Country;

import reactor.core.publisher.Mono;

public interface CountryDao extends ReactiveMongoRepository<Country, String> {
	Mono<Country> findByCountryCode(String code);
}