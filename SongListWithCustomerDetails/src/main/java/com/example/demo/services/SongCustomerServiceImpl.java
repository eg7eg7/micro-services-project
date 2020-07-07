package com.example.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.models.Customer;
import com.example.demo.models.List;
import com.example.demo.models.ListWithCustomer;

import reactor.core.publisher.Flux;

@Service
public class SongCustomerServiceImpl implements SongCustomer {

	private final String getLists = "/lists?orderAttr=ASC&sortAttr=id";

	@Value("${listService.url}")
	String listServiceUrl;

	@Value("${customerService.url}")
	String customerServiceUrl;

	@Override
	public Flux<ListWithCustomer> getListsWithCustomerDetails() {
		return WebClient.create(listServiceUrl + getLists).get().accept(MediaType.TEXT_EVENT_STREAM).retrieve()
				.bodyToFlux(List.class).concatMap(list -> {
					return WebClient.create(customerServiceUrl + "/customers/" + list.getUserEmail()).get().retrieve()
							.bodyToMono(Customer.class).map(customer -> {
								return new ListWithCustomer(list, customer);
							});

				});
	}

}
