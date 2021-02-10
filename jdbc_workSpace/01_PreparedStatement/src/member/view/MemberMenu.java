package member.view;

import java.sql.Date;
import java.util.Iterator;
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
				+ "0. 프로그램 끝내기\n"
				+ "-----------------------------------\n"
				+ "선택 : ";
		while(true) {
			System.out.print(menu);
			int choice = sc.nextInt();
			Member member = null;
			int result = 0;
			String msg = null;
			List<Member> list;
			String memberId = null;
			String memberPassWord = null;
			String memberName = null;
			
			switch(choice) {
			
			//회원 전체 조회
			case 1 : 
				list = memberController.selectAll();
				displayMemberList(list);
				break;
				
			//회원 아이디 조회
			case 2 : 
				memberId = inputMemberId();
				member = memberController.selectOne(memberId);
				displayMember(member);
				break;
				
			//회원 이름 조회
			case 3 : 
				memberName = inputMemberName();
				member = memberController.selectName(memberName);
				displayMember(member);
				break;
				
			//회원가입
			case 4 : 
				System.out.println("회원 가입");
				//1.신규회원정보입력 -> Member객체
				member = inputMember();
				displayMember(member);
				
				//2.controller에 회원가입 요청(메소드호출)
				//DML(insert)요청 -> int리턴(처리된 행의 개수)
				result = 0;
				result = memberController.insertMember(member);
				
				//3.int에 따른 분기처리
				msg = result > 0 ? "회원 가입 성공!" : "회원 가입 실패!";
				displayMsg(msg);
				
				break;
				
			//회원 정보 변경
			case 5 : 
				System.out.println("회원정보변경");
				memberId = inputMemberId();
				memberPassWord = inputMemberPassWord();
				member = memberController.selectOne(memberId,memberPassWord);
				
				//아이디랑 비밀번호를 받아 회원 조회후 없는 회원일경우 없다고 표시해주고 종료
				if(member == null) {
					System.out.println("회원정보 없음.");
					break;
				}
				//기존정보 보여줌
				displayMember(member);
				//회원정보 변경
				member = modifyMember(memberId);
				//DB에 수정사항 요청
				result = 0;
				result = memberController.modifyInfo(member);
				//변경유무에 따른 분기처리
				msg = result > 0 ? "회원 정보 수정 성공!" : "회원 정보 수정 실패!";
				displayMsg(msg);
				if(result>0) {
					displayMember(member);
				}
				break;
				
			//회원 탈퇴
			case 6 : 
				System.out.println("회원 탈퇴");
				memberId = inputMemberId();
				memberPassWord = inputMemberPassWord();
				member = null;
				member = memberController.selectOne(memberId,memberPassWord);
				//아이디랑 비밀번호를 받아 회원 조회후 없는 회원일경우 없다고 표시해주고 종료
				if(member == null) {
					System.out.println("탈퇴할 회원정보 없음.");
					break;
				}
				//DB에 탈퇴 요청
				result = 0;
				result = memberController.deleteInfo(member);
				//변경유무에 따른 분기처리
				msg = result > 0 ? "회원 탈퇴 성공!" : "회원 탈퇴 실패!";
				displayMsg(msg);
				if(result>0) {
					displayMember(member);
				}
				break;
				
			//프로그램종료	
			case 0 : 
				System.out.print("정말로 끝내시겠습니까?(y/n) : ");
				if(sc.next().toUpperCase().charAt(0) == 'Y')
					return; //현재메서드를 호출한곳으로 (Run클래스로) 돌아감
				break;
				
			//잘못 입력했을시	
			default : System.out.println("잘못 입력 하셨습니다.");
			}
		}
	}
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
		
		System.out.print("등록날짜");
				
		//enrollDate
		return member;
	}
	private Member modifyMember(String memberId) {
		System.out.println("변경할 회원정보를 입력하세요.");
		Member member = new Member();
		member.setMemberId(memberId);
		
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
		
		System.out.print("등록날짜");
				
		//enrollDate
		return member;
	}
	
	/**
	 * DML 처리결과 통보용
	 */
	private void displayMsg(String msg) {
		System.out.println(">>> 처리결과 : " + msg);
	}
	/**
	 * DQL 처리 목록 보여줌
	 */
	private void displayMemberList(List<Member> list) {
		if(list == null || list.isEmpty()) {
			System.out.println(">>> 조회된 행이 없습니다.");
			return;
		}
		Iterator<Member> it = list.iterator();
		while(it.hasNext()) {
			Member m = it.next();
			System.out.println(m);
		}
	}
	/**
	 * 조회할 회원 아이디 입력
	 */
	private String inputMemberId() {
		System.out.print("조회할 아이디 입력 : ");
		return sc.next();
	}
	/**
	 * 조회할 회원 비밀번호 입력
	 */
	private String inputMemberPassWord() {
		System.out.print("비밀번호 입력 : ");
		return sc.next();
	}
	
	/**
	 * 조회할 회원 아이디 입력
	 */
	private String inputMemberName() {
		System.out.print("조회할 이름 입력 : ");
		return sc.next();
	}
	
	private void displayMember(Member member) {
		if(member ==null) {
			System.out.println("조회된 회원이 없습니다");
			return;
		}
		System.out.println(member);
	}
}
