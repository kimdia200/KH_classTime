package com.kh.spring.security.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SecurityDaoImpl implements SecurityDao{

	@Autowired
	private SqlSessionTemplate session;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return session.selectOne("security.loadUserByUsername",username);
	}
	
}
