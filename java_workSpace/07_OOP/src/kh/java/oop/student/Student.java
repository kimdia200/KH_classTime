package kh.java.oop.student;
/**
 * 
 * class -> 객체(Object(개념), instance(메모리조각))
 * 
 * 틀, 설계도
 * 
 * 클래스를 프로그램의 지향하는 바에 따라 설계하는 것을 추상화 라고 한다.
 * 
 *
 */
public class Student {
	//field : 학생객체의 속성(정적인 data), 클래스 영역에 작성
	private String name;
	private int point;
	
	//method : 학생객체의 기능
	public void printStudentInfo() {
		//method에서는 field를 참조 할 수 있다.
		System.out.println("이름 : "+ name);
		System.out.println("점수 : "+ point);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}	
}
