package com.example.demo.models;

import java.util.Date;

public class ListWithCustomer {

	private String id;
	private String name;
	private Customer user;
	private Date createdTimestamp;

	public ListWithCustomer() {

	}

	public ListWithCustomer(String id, String name, Customer user, Date createdTimestamp) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.createdTimestamp = createdTimestamp;
	}

	public ListWithCustomer(List list, Customer customer) {
		this.user = customer;
		this.id = list.getId();
		this.name = list.getName();
		this.createdTimestamp = list.getCreatedTimestamp();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ListWithCustomer [id=" + id + ", name=" + name + ", user=" + user + ", createdTimestamp="
				+ createdTimestamp + "]";
	}

}
