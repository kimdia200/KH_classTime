package member.controller;

import java.util.List;

import member.model.service.MemberService;
import member.model.vo.Member;

public class MemberController {
	
	private MemberService memberService = new MemberService();
	
	//1번.전체 회원 조회
	public List<Member> selectAll(){
		return memberService.selectAll();
	}
	
	//2번.아이디로 회원 한명 조회
	public Member selectOne(String memberId) {
		return memberService.selectOne(memberId);
	}
	
	//3번.이름으로 회원 조회
	public List<Member> selectName(String memberName) {
		return memberService.selectName(memberName);
	}

	//4번.회원가입
	public int insertMember(Member member) {
		return memberService.insertMember(member);
	}
	
//	//5번.정보수정
//	public int updateMember(Member member) {
//		return memberService.updateMember(member);
//	}
	//비밀번호 수정
	public int updatePassword(Member member) {
		return memberService.updatePassword(member);
	}
	//이메일수정
	public int updateEmail(Member member) {
		return memberService.updateEmail(member);
	}
	//전화번호수정
	public int updatePhone(Member member) {
		return memberService.updatePhone(member);
	}
	//주소수정
	public int updateAddress(Member member) {
		return memberService.updateAddress(member);
	}
	
	//6번. 회원탈퇴
	public int deleteMember(Member member) {
		return memberService.deleteMember(member);
	}

	

	
}
