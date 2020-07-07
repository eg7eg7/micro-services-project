package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ListWithCustomer;
import com.example.demo.services.SongCustomer;

import reactor.core.publisher.Flux;

@RestController
public class SongCustomerController {
	SongCustomer songCustomerService;
	
	@Autowired
	public SongCustomerController(SongCustomer s) {
		songCustomerService = s;
	}
	
	@RequestMapping(path="/lists/withUserDetails",
			method = RequestMethod.GET,
			produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ListWithCustomer> getListWithUserDetails() {
		return this.songCustomerService.getListsWithCustomerDetails();
	}
}
