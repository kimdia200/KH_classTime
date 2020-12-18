package kh.java.oop.field;
/**
 * 
 * 프로그램
 * main시작---------------------------------main끝
 * 
 * 지역변수                          메소드호출시 ----->메소드리턴
 * 멤버변수                          객체생성시 -------------->객체소멸(가비지컬렉터에 의해 제거)
 * 클래스변수 프로그램 시작시------------------->프로그램 종료
 * @author family
 *
 */
public class GalaxyNote20 {
	//전역변수
	//멤버변수 : instance변수, non-static변수
	private String owner;
	private String phoneNumber;
	
	//전역변수
	//클래스 변수 : static 변수
	//공유의 목적 : 모든 객체가 공유할 값
	public static final int WIDTH = 10;
	public static final int HEIGHT = 20;
	
	public void heyBixby() {
		//지역변수 : 접근제한자, static 키워드 사용불가
		int a = 10;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return this.owner;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
}
