package com.example.demo.services;

import com.example.demo.models.ListWithCustomer;

import reactor.core.publisher.Flux;

public interface SongCustomer {

	Flux<ListWithCustomer> getListsWithCustomerDetails();

}
