package member.controller;

import static member.model.service.MemberService.MEMBER_ROLE;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet({ "/DeleteMemberServlet", "/member/deleteMember" })
public class Member_Update_delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩처리(Get방식은 인코딩 필요없음)
		
		//2.값처리
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String memberName = request.getParameter("memberName");
		String birthDay = request.getParameter("birthDay");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String[] hobbys = request.getParameterValues("hobby");

		String hobby = "";
		for (int i = 0; i < hobbys.length; i++) {
			hobby += hobbys[i];
			if (i != hobbys.length - 1)
				hobby += ",";
		}
		
		System.out.println(memberId + "\n" + password + "\n" + password2 + "\n" + memberName + "\n" + birthDay + "\n"
				+ email + "\n" + phone + "\n" + address + "\n" + gender + "\n" + hobby + "\n");

		// 3. 업무 로직
		HttpSession session = request.getSession(false);

		// (강사님 코드)날짜타입으로 변경 : 1990-09-09
		Date birthday = null;
		if (birthDay != null && !"".equals(birthDay))
			birthday = Date.valueOf(birthDay);

		// MemberRole(Service에서 임포트), birthDay(형변환), enrollDate(새로하나 만듬)
		Member member = new Member(memberId, password, memberName, MEMBER_ROLE, gender, birthday, email, phone, address, hobby,
				new Date(Calendar.getInstance().getTimeInMillis()));

		int result = new MemberService().updateMember(member);

		// 로그인 성공 실패 분기처리
		if (result > 0) {
			System.out.println("회원정보변경 성공!!!");
			if(session!=null) {
				session.setAttribute("msg", "회원정보변경 성공!!!");
				session.setAttribute("loginMember", member);
			}
		} else {
			System.out.println("회원정보변경 실패...");
			if(session!=null) {
				session.setAttribute("msg", "회원정보변경 실패...");
			}
		}

		response.sendRedirect(request.getContextPath());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");

		// 2. 값처리
		String memberId = request.getParameter("memberId");

		// 3. 업무 로직
		HttpSession session = request.getSession(false);

		int result = new MemberService().deleteMember(memberId);
		if (result > 0) {
			System.out.println("회원탈퇴 성공!!!");
			if (session != null) {
				// 세션 무효화(모든 속성을 제거) 해주는 메서드
				Cookie c = new Cookie("saveId", memberId);
				c.setPath(request.getContextPath()); // setpath = 쿠키를 전송할 url
				c.setMaxAge(0);// 0으로 해서 즉시삭제
				response.addCookie(c);
				session.invalidate();
			}
			session = request.getSession(true);
			session.setAttribute("msg", "회원탈퇴 성공!!!");
		} else {
			System.out.println("회원탈퇴 실패...");
			session.setAttribute("msg", "회원탈퇴 실패...");
		}

		response.sendRedirect(request.getContextPath());
	}
}
