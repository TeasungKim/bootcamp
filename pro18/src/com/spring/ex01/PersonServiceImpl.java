package com.spring.ex01;

public class PersonServiceImpl implements PersonService {

	private String name;
	private int age;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void sayHello() {
		System.out.println(name);
		System.out.println(age);
	}
	
}
