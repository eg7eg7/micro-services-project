package com.example.demo.models;

public class Name {
	private String first;
	private String last;

	public Name() {

	}

	public Name(String first, String last) {
		super();
		this.setFirst(first);
		this.setLast(last);
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}
