package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.models.ListWithCustomer;

import reactor.core.publisher.Flux;

@Component
public class ReactiveDemoConsumer implements CommandLineRunner{
	@Override
	public void run(String... args) throws Exception {
		String url = "http://localhost:5002/lists/withUserDetails";
		
		WebClient webClient = WebClient.create(url);
		System.err.println("consumer initialized and requested GET /demoes");
		Flux<ListWithCustomer> results = webClient
			.get()
			.accept(MediaType.TEXT_EVENT_STREAM)
			.retrieve()
			.bodyToFlux(ListWithCustomer.class);
		
		results
//			.subscribe(); // do nothing consumer
		
//			.subscribe(demo->
//				System.err.println("retrieved new data: " + demo)); // handle successful events only
		
		.subscribe(demo->
				System.err.println("retrieved new data: " + demo),
				
				error->/*System.err.println("something wrong happened"),*/error.printStackTrace(), // handle errors
				
				()->System.err.println("done consuming data from service"),// complete runnable
			
				subscription->subscription.request(20) // subscription handling
				
				);
//		
	}

}
