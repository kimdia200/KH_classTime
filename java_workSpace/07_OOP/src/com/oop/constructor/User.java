package com.oop.constructor;

import java.util.Date;

/**
 * 
 * 생성자 Constructor
 * new 연산자를 통해 호출되는 메소드.
 * 객체 생성시 마지막에 호출되어 객체 필드 초기화 처리를 담당.
 * 
 * 리턴타입이 없고, 클래스명과 동일해야 한다.
 * 
 *기본생성자
 *파라미터 생성자가 없다면, JVM에 의해 자동생성
 *파라미터 생성자가 있다면, 자동생성 되지 않음. 직접 작성해줘야함
 *
 * 파라미터 생성자 
 * 필드값을 넘겨받아서 한번에 값세팅
 */
public class User {
	
	private String userId;
	private String passWord;
	private Date regDate;
	

	/**
	 * 기본생성자 : 파라미터 없음.
	 * 1.상속시 자식클래스에서 부모클래스의 기본생성자를 호출
	 * 2.java ee 스펙에 따라 객체 생성시 내부적 호출
	 */
	public User() {
		
	}
	
	/** 파라미터 생성자
	 * 파라미터를 이용하여 필드값 수정 가능
	 * 
	 * this()
	 * 다른 생성자를 호출해서 생성자 안의 중복된 코드 제거 가능
	 * 딱 한번 맨 첫줄에서만 호출이 가능하다.
	 */
	//파라미터 생성자1(this() 사용형 )
	public User(String userId, String passWord, Date regDate) {
		//this() 는 다른생성자를 호출하는것
		//반드시 코드 첫줄에 존재해야함! 반드시이이이!
		this(userId,passWord);
		this.regDate = regDate;
	}	
	//파라미터생성자1(기본형)
//	public User(String userId, String passWord, Date regDate) {
//		this.userId = userId;
//		this.passWord = passWord;
//		this.regDate = regDate;
//	}
	//파라미터생성자2
	public User(String userId) {
		this.userId = userId;
	}
	//파라미터생성자3
	public User(String userId, String passWord) {
		this.userId = userId;
		this.passWord = passWord;
	}
	
	
	
	public void printUser() {
		System.out.println(userId);
		System.out.println(passWord);
		System.out.println(regDate);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}