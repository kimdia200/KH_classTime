package com.oop.constructor;

import java.util.Date;

public class UserMain {

	public static void main(String[] args) {
		// 1. 기본생성자 + setter
		User u = new User();
		u.setUserId("honggd");
		u.setPassWord("1234");
		u.setRegDate(new Date());
		u.printUser();
		
		//2. 파라미터생성자1
		User u2 = new User("신사임당", "5678", new Date());
		u2.printUser();
		
		//파라미터생성자2
		User u3 = new User("sejong");
		u3.printUser();
		
		//파라미터생성자3
		User u4 = new User("youngsil", "7878");
		u4.printUser();
	}

}
