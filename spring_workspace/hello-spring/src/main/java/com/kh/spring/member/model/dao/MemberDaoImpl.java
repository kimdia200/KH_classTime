package com.kh.spring.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public int insertMember(Member member) {
		return session.insert("member.insertMember",member);
	}

	@Override
	public Member selectOneMember(String id) {
		return session.selectOne("member.selectOneMember",id);
	}

	@Override
	public int updateMember(Member member) {
		return session.update("member.updateMember",member);
	}
}
