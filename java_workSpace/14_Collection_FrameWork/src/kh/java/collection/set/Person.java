package kh.java.collection.set;

import java.util.Objects;

public class Person {
	private String name;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Person))
			return false;
		if(((Person)obj).getName().equals(name))
			return true;
		else 
			return false;
	}
}
