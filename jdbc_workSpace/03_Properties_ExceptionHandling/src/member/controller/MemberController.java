package member.controller;

import java.util.List;

import member.model.exception.MemberException;
import member.model.service.MemberService;
import member.model.vo.Member;
import member.view.MemberMenu;

//컨트롤러 에서 무조건 예외처리 해준다
public class MemberController {
	
	private MemberService memberService = new MemberService();
	
	//1번.전체 회원 조회
	public List<Member> selectAll(){
		List<Member> list = null;
		try {
			list = memberService.selectAll();
		}catch(MemberException e) {
			//서버로깅
			//관리자 이메일 알림
			
			//사용자 피드백
			new MemberMenu().displayError(e.getMessage()+" : 관리자에게 문의하세요.");
		}
		return list;
	}
	
	//2번.아이디로 회원 한명 조회
	public Member selectOne(String memberId) {
		Member member = null;
		try {
			member=memberService.selectOne(memberId);
		} catch (MemberException e) {
			new MemberMenu().displayError(e.getMessage()+" : 관리자에게 문의하세요.");
		}
		return member;
	}
	
	//3번.이름으로 회원 조회
	public List<Member> selectName(String memberName) {
		List<Member> list = null;
		try {
			list=memberService.selectName(memberName);
		} catch (MemberException e) {
			new MemberMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요.");
		}
		return list;
	}

	//4번.회원가입
	public int insertMember(Member member) {
		int result=0;
		try {
			result = memberService.insertMember(member);
		} catch (MemberException e) {
			new MemberMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요.");
		}
		return result;
	}
	
//	//5번.정보수정
//	public int updateMember(Member member) {
//		return memberService.updateMember(member);
//	}
	//비밀번호 수정
	public int updatePassword(Member member) {
		int result = 0;
		try {
			result = memberService.updatePassword(member);
		} catch (MemberException e) {
			new MemberMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요.");
		}
		return result;
	}
	//이메일수정
	public int updateEmail(Member member) {
		int result = 0;
		try {
			result = memberService.updateEmail(member);
		} catch (MemberException e) {
			new MemberMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요.");
		}
		return result;
	}
	//전화번호수정
	public int updatePhone(Member member) {
		int result = 0;
		try {
			result = memberService.updatePhone(member);
		} catch (MemberException e) {
			new MemberMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요.");
		}
		return result;
	}
	//주소수정
	public int updateAddress(Member member) {
		int result = 0;
		try {
			result = memberService.updateAddress(member);
		} catch (MemberException e) {
			new MemberMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요.");
		}
		return result;
	}
	
	//6번. 회원탈퇴
	public int deleteMember(Member member) {
		int result = 0;
		try {
			result = memberService.deleteMember(member);
		} catch (MemberException e) {
			new MemberMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요.");
		}
		return result;
	}
	
	//7번. 탈퇴회원 조회
	public List<Member> selectDelAll(){
		List<Member> list = null;
		try {
			list = memberService.selectDelAll();
		}catch(MemberException e) {
			//서버로깅
			//관리자 이메일 알림
			
			//사용자 피드백
			new MemberMenu().displayError(e.getMessage()+" : 관리자에게 문의하세요.");
		}
		return list;
	}

	

	
}
