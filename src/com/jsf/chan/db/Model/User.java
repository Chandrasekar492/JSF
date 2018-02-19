package com.jsf.chan.db.Model;

public class User {

	private Long Id;
	private String name;
	private String address;
	private int age;
	private String gender;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", name=" + name + ", address=" + address + ", age=" + age + ", gender=" + gender
				+ "]";
	}
	
	
}
