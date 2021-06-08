package com.kh.spring.security.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.spring.security.model.dao.SecurityDao;

import lombok.extern.slf4j.Slf4j;

@Service("securityService")
@Slf4j
public class SecurityServiceImpl implements SecurityService{

	@Autowired
	private SecurityDao securityDao;
	
	@Override
	//username은 구분자로 우리는 id를 사용하고있음
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//유저데이터 하나를 불러온다고 보면됨
		UserDetails member = securityDao.loadUserByUsername(username);
		log.debug("member = {}", member);
		if(member == null) {
			//이 예외 객체를 던져주는게 약속
			throw new UsernameNotFoundException(username);
		}
		return member;
	}
}
