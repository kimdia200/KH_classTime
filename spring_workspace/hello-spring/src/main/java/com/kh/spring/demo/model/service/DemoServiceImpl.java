package com.kh.spring.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.demo.model.dao.DemoDao;

@Service
public class DemoServiceImpl implements DemoService{
	@Autowired
	private DemoDao demoDao;
}
