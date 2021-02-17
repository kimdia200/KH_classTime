package member.view;

import java.util.List;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;


public class MemberMenu {
	private MemberController memberController = new MemberController();
	private Scanner sc =  new Scanner(System.in);
	//Run 클래스에서 실행
	public void mainMenu() {
		String menu = 
				"========== 회원 관리 프로그램 ==========\n"
				+ "1. 회원 전체 조회\n"
				+ "2. 회원 아이디 조회\n"
				+ "3. 회원 이름 조회\n"
				+ "4. 회원 가입\n"
				+ "5. 회원 정보 변경\n"
				+ "6. 회원 탈퇴\n"
				+ "7. 탈퇴 회원 조회\n"
				+ "0. 프로그램 끝내기\n"
				+ "-----------------------------------\n"
				+ "선택 : ";
		do {
			System.out.print(menu);
//			int choice = sc.nextInt(); //숫자가 아닌걸 입력받으면 오류나서 스트링으로 처리가 베스트
			String choice = sc.next();
			List<Member> list = null;
			Member member = null;
			String memberId = "";
			int result = 0;
			
			switch(choice) {
			//회원 전체 조회
			case "1" : 
				list=null;
				list = memberController.selectAll();
				displayMemberList(list);
				break;
				
			//회원 아이디 조회
			case "2" : 
				member=null;
				memberId = inputMemberId();
				member = memberController.selectOne(memberId);
				displayMember(member);
				break;
			
			//회원 이름 조회
			case "3" : 
				list=null;
				String memberName = inputMemberName();
				//동명이인이 있을수 있으니까 list로 처리했음
				list = memberController.selectName(memberName);
				displayMemberList(list);
				break;
			//회원 가입
			case "4" : 
				result=0;
				member = inputMember();
				System.out.println(member);
				result = memberController.insertMember(member);
				if(result>0)
					System.out.println("회원가입 성공!!!");
				else
					System.out.println("회원가입 실패...");
				break;
			
			//회원 정보 변경
			case "5" :
				member = null;
				memberId = inputMemberId();
				member = memberController.selectOne(memberId);
				if(member == null)
					System.out.println("회원이 존재하지 않음");
				else
					updateMember(member);
				break;
			
			//회원 탈퇴
			case "6" : 
				result = 0;
				member = deleteMember();
				result = memberController.deleteMember(member);
				if(result>0)
					System.out.println("회원탈퇴 성공!!!");
				else
					System.out.println("회원탈퇴 실패...");
				break;
			//탈퇴 회원 조회
			case "7" :
				list=null;
				list = memberController.selectDelAll();
				displayMemberList(list);
				break;
			//프로그램 끝내기
			case "0" : 
				System.out.print("정말 끝내시겠습니까? (y/n) : ");
				if(sc.next().toUpperCase().charAt(0)=='Y')
					return;
				break;
			default :
				System.out.println("잘못 입력 하셨습니다.");
			}
		}while(true);
	}
	
	//회원정보수정
	private void updateMember(Member member) {
		String menu = "1.암호변경 \n"
				+ "2.이메일변경 \n"
				+ "3.전화번호변경\n"
				+ "4.주소변경\n"
				+ "9.메인메뉴로 돌아가기";
		do {
			int result = 0;
//			result = memberController.updateMember(member);
			System.out.println(menu);
			System.out.print("메뉴선택 : ");
			String num = sc.next();
			sc.nextLine();
			
			switch(num) {
			//암호변경
			case "1" : 
				result = 0;
				System.out.print("변경할 비밀번호 : ");
				member.setPassword(sc.nextLine());
				result = memberController.updatePassword(member);
				if(result>0)
					System.out.println("정보수정 성공!!!");
				else
					System.out.println("정보수정 실패...");
				break;
			//이베일변경
			case "2" : 
				result = 0;
				System.out.print("변경할 이메일 : ");
				member.setEmail(sc.nextLine());
				result = memberController.updateEmail(member);
				if(result>0)
					System.out.println("정보수정 성공!!!");
				else
					System.out.println("정보수정 실패...");
				break;
			//전화번호변경
			case "3" : 
				result = 0;
				System.out.print("변경할 전화번호(-빼고 입력) : ");
				member.setPhone(sc.nextLine());
				result = memberController.updatePhone(member);
				if(result>0)
					System.out.println("정보수정 성공!!!");
				else
					System.out.println("정보수정 실패...");
				break;
			//주소변경
			case "4" : 
				result = 0;
				System.out.print("변경할 주소 : ");
				member.setAddress(sc.nextLine());
				result = memberController.updateAddress(member);
				if(result>0)
					System.out.println("정보수정 성공!!!");
				else
					System.out.println("정보수정 실패...");
				break;
			//메인메뉴로 가기
			case "9" : 
				System.out.print("정말 메인메뉴로 돌아가시겠습니까? (y/n) : ");
				if(sc.next().toUpperCase().charAt(0)=='Y')
					return;
				break;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}while(true);
	}


	private Member deleteMember() {
		Member member = new Member();
		System.out.print("탈퇴할 아이디 : ");
		member.setMemberId(sc.next());
		System.out.print("비밀번호 : ");
		member.setPassword(sc.next());
		return member;
	}


	/**
	 * Member객체 출력
	 * @param member
	 */
	private void displayMember(Member member) {
		if(member == null)
			System.out.println("조회된 회원이 없습니다.");
		else
			System.out.println(member);
	}

	/**
	 * n행의 회원정보 출력
	 * @param list
	 */
	private void displayMemberList(List<Member> list) {
		if(list != null && !list.isEmpty()) {
			System.out.println("===============================================");
			for(int i =0; i<list.size(); i++)
				System.out.println((i+1)+" : " + list.get(i));
			System.out.println("===============================================");
		}else {
			System.out.println(">>> 조회된 회원 정보가 없습니다.");
		}
	}
	/**
	 * 회원가입시 멤버생성 메서드
	 * @return
	 */
	private Member inputMember() {
		System.out.println("새로운 회원정보를 입력하세요.");
		Member member = new Member();
		
		System.out.print("아이디 : ");
		member.setMemberId(sc.next());
		
		System.out.print("비밀번호 : ");
		member.setPassword(sc.next());

		System.out.print("이름 : ");
		member.setMemberName(sc.next());
		
		System.out.print("성별 (M/F) : ");
		member.setGender(String.valueOf(sc.next().toUpperCase().charAt(0)));
		
		System.out.print("나이 : ");
		member.setAge(sc.nextInt());
		
		System.out.print("이메일 : ");
		member.setEmail(sc.next());
		
		System.out.print("전화번호(-빼고 입력) : ");
		member.setPhone(sc.next());
		
		System.out.print("주소 : ");
		sc.nextLine();
		member.setAddress(sc.nextLine());
		
		System.out.print("취미 (,로 나열할것) : ");
		member.setHobby(sc.nextLine());
		
		//enrollDate
		return member;
	}
	
	public String inputMemberId() {
		System.out.print("조회할 아이디 : ");
		return sc.next();
	}
	private String inputMemberName() {
		System.out.print("조회할 이름 : ");
		return sc.next();
	}
	
	/**
	 * 사용자에게 오류메세지 출력하시
	 * @param errorMsg
	 */
	public void displayError(String errorMsg) {
		System.err.println(errorMsg);
	}
}
