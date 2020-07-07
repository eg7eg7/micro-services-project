package com.example.demo;

import java.util.Date;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.models.Country;
import com.example.demo.models.Customer;
import com.example.demo.models.List;
import com.example.demo.models.ListWithCustomer;
import com.example.demo.models.Name;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReactiveDemoConsumer implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		String url = "http://localhost:5002/lists/withUserDetails";
		String customerServiceUrl = "http://localhost:5000/customers";
		String listServiceUrl = "http://localhost:5001/lists";

		System.err.println(
				"SongListWithCustomerDetails Consumer initialized, make sure customer and song lists services and online");

		
		/*
		 * Remove comments to create dummy customers with lists
		 * Update URL addresses before running
		 * 
		 * */
		
//		Flux<ListWithCustomer> results = WebClient.create(url).get().accept(MediaType.TEXT_EVENT_STREAM).retrieve()
//				.bodyToFlux(ListWithCustomer.class);
//
//		Flux.range(0, 50).subscribe(new Subscriber<Integer>() {
//			private Subscription sub;
//
//			@Override
//			public void onSubscribe(Subscription s) {
//				s.request(5);
//				sub = s;
//
//			}
//
//			@Override
//			public void onNext(Integer val) {
//				WebClient.create(listServiceUrl).post()
//						.bodyValue(new List("", "List number " + val, "mailtest" + val + "@gmail.com", new Date()))
//						.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(List.class).subscribe();
//
//				WebClient.create(customerServiceUrl).post()
//						.bodyValue(new Customer("mailtest" + val + "@gmail.com", new Name("customer", val + ""),
//								"01-01-" + (1800 + val), new Country("SP", "Spain")))
//						.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Customer.class)
//						.subscribe();
//
//				sub.request(5);
//
//			}
//
//			@Override
//			public void onError(Throwable t) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onComplete() {
//				// On complete, after data is created
//				results.subscribe(new Subscriber<ListWithCustomer>() {
//					private Subscription sub;
//					@Override
//					public void onSubscribe(Subscription s) {
//						sub = s;
//						s.request(10);
//						
//					}
//
//					@Override
//					public void onNext(ListWithCustomer t) {
//						System.err.println("Retrieved new list " + t);
//						sub.request(10);
//					}
//
//					@Override
//					public void onError(Throwable t) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void onComplete() {
//						System.err.println("Retrieved all lists");
//						
//					}
//				});
//
//			}
//		});

	}

}
