package com.kh.spring.member.model.dao;

import com.kh.spring.member.model.vo.Member;

public interface MemberDao {

	int insertMember(Member member);

	Member selectOneMember(String id);

}
