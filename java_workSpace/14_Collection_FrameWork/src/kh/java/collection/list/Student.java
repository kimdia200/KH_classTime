package kh.java.collection.list;

import java.util.Objects;

public class Student implements Comparable<Student> {
	private int no;
	private String name;
	
	public Student() {
		super();
	}

	public Student(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [no=" + no + ", name=" + name + "]";
	}
	
	/**
	 * 필드값이 동일하면 true가 나올 수 있도록 재작성한다.
	 * no, name
	 * 
	 * equals & hashCode는 동시에 오버라이딩 한다.
	 * equals가 true라면, hashCode값도 동일해야한다. 라는 암묵적 룰이있음
	 * 동일한 객체처럼 작동 할 수 있도록.(메모리상으론 다르지만 같은것처럼 이용할수 있게)
	 *  
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Student))
			return false;
		Student std=(Student)obj;
		
		if(this.no==std.no && this.name.equals(std.name))
			return true;
		else 
			return false;
	}
	/**
	 * equals에 사용한 필드를 똑같이 이용해서 hashCode를 생성할것.
	 */
	@Override
	public int hashCode() {
		//간단한 hashCode생성 메서드(jdk1.7)부터 생겼음
		return Objects.hash(no,name);
	}

	/**
	 * 기본정렬 : 학번 오름차순
	 * 양수 : 자리유지
	 * 0 : 자리유지
	 * 음수 : 자리바꿈
	 */
	@Override
	public int compareTo(Student other) {
		
		return this.no - other.no;
	}
}
