package member.controller;

import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;

/**
 * MVC패턴의 시작점이자 전체흐름을 제어.
 * 
 * view단으로부터 요청을 받아서 dao에 다시 요청. 그 결과를 view단에 다시 전달.
 * 
 * @author 윾수
 *
 */
public class MemberController {
	// Dao객체 생성
	private MemberDao memberDao = new MemberDao();

	// 전체회원 목록 불러오는 메서드
	public List<Member> selectAll() {
		return memberDao.selectAll();
	}

	// insert 메서드
	public int insertMember(Member member) {
		return memberDao.insertMember(member);
	}

	// 아이디 하나 조회하는 메서드(오버로딩)
	public Member selectOne(String memberId) {
		return memberDao.selectOne(memberId);
	}

	// 아이디 하나 조회하는 메서드(오버로딩)
	public Member selectOne(String memberId, String memberPassword) {
		return memberDao.selectOne(memberId, memberPassword);
	}
	
	//이름으로 조회
	public Member selectName(String memberName) {
		return memberDao.selectName(memberName);
	}
	
	//회원정보 수정
	public int modifyInfo(Member member) {
		return memberDao.modifyInfo(member);
	}

	//회원탈퇴 요청
	public int deleteInfo(Member member) {
		return memberDao.deleteInfo(member);
	}
}
