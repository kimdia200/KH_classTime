package kh.java.object.array.person;

import java.util.Scanner;

/**
 * PersonManager는 Person을 field로써 참조한다.
 * PersonManager -------------> Person
 * @author family
 *
 */
public class PersonManager {
	private Person[] arr = new Person[3];
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Person 정보 입력 메소드
	 */
	public void inpuPerson() {
		for(int i=1; i<4; i++) {
			System.out.print(i+"번. 이름 : ");
			String name = sc.next();
			System.out.print(i+"번. 나이 : ");
			int age = sc.nextInt();
			arr[i-1]=new Person(name, age);
		}
	}
	/**
	 * Person 정보 출력 메소드
	 */
	public void printPerson() {
		for(Person p : arr)
			System.out.println(p.getName()+", "+p.getAge());
	}
}
