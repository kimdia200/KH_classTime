package com.kh.person.model.vo;

import java.util.Objects;

public class Person {
	private String id;
	private String name;
	private char gender;
	private int age;
	private boolean married;
	public Person() {
		super();
	}
	public Person(String id, String name, char gender, int age, boolean married) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.married = married;
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
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", married=" + married
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(age,gender,id,married,name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (gender != other.gender)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (married != other.married)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
