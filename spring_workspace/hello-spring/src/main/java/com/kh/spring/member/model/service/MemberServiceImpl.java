package com.kh.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDao memberdao;

	@Override
	public int insertMember(Member member) {
		return memberdao.insertMember(member);
	}

	@Override
	public Member selectOneMember(String id) {
		return memberdao.selectOneMember(id);
	}
}
