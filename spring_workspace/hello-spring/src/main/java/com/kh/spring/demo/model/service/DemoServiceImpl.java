package com.kh.spring.demo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.demo.model.dao.DemoDao;
import com.kh.spring.demo.model.vo.Dev;

@Service
public class DemoServiceImpl implements DemoService{
	@Autowired
	private DemoDao demoDao;

	
	/**
	 * Spring AOP기술을 이용해서 Session객체 관리(시작 ~ 끝), 트랜잭션 처리가 외부로 빠진다.
	 * 우리는 2번. dao업무요청만 해주면된다.
	 */
	@Override
	public int insertDev(Dev dev) {
		//1. SqlSession객체 생성
		//2. dao업무요청
		//3. transaction처리
		//4. SqlSession반납
		return demoDao.insertDev(dev);
	}


	@Override
	public List<Dev> selectDevList() {
		return demoDao.selectDevList();
	}


	@Override
	public Dev selectDevOne(int no) {
		return demoDao.selectDevOne(no);
	}
	
}
