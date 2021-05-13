package com.kh.spring.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.kh.spring.user.model.service.UserService;

@Component
@Scope("prototype")
@Lazy //lazy-init="true"
public class UserController {
	@Autowired
	private UserService userService;
	
	public void getUserDetail(String id) {
		userService.getUserDetail(id);
	}
}
