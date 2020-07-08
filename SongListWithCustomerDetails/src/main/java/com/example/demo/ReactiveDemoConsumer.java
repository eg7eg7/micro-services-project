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
		Flux<ListWithCustomer> results = webClient
			.get()
			.accept(MediaType.TEXT_EVENT_STREAM)
			.retrieve()
			.bodyToFlux(ListWithCustomer.class);
		System.err.println("SongListWithCustomerDetails Consumer initialized, make sure customer and song lists services and online");
		results.subscribe(
				b -> {System.err.println("retrieved new list with customer: " + b);},
				err-> {System.err.println("Retrieved an error " + err);},
				() -> {System.err.println("Retrieved all lists");},
				s -> {s.request(22);});
	
	}

}
