package kh.java.object.array.person;

/**
 * data를 담을 용도의 클래스
 * VO Value Object class
 * Entity class (database)
 * DO Domain Object class 
 * DTO Data Transfer Object class
 * Bean 
 *
 */
public class Person {

	private String name;
	private int age;
	
	public Person () {
		
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void printPerson() {
		System.out.println(name + ", " + age);
	}
	
}



