package com.kh.spring.user.model.service;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

	@Override
	public void getUserDetail(String id) {
		System.out.println("사용자 "+id+"의 이름은 홍길동 입니다.");
	}

}
