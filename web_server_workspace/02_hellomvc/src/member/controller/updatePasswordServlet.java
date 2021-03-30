package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class updatePasswordServlet
 */
@WebServlet({ "/updatePasswordServlet", "/member/updatePassword" })
public class updatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//로그인을 해야만 접속이 가능한 페이지로
	//LiginFilter에 등록해주었고, EncodingFilter도 작동한다.
	
	
	/**
	 * 비밀번호 변경페이지 제공
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/updatePassword.jsp").forward(request, response);
	}

	/**
	 * 비밀번호 변경처리 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.인코딩 처리(EncodingFilter에서 처리해줬음)
		
		//2.값처리
		String password = MvcUtils.getSha512(request.getParameter("password"));
		String newPassword = MvcUtils.getSha512(request.getParameter("newPassword"));
		
		//3. 기존비밀번호 비교
		HttpSession session = request.getSession(false);
		Member member = (Member)session.getAttribute("loginMember");
		System.out.println(member);
		String oldPwd = member.getPassword();
		
		//기존비밀번호가 틀릴경우
		if(oldPwd!=password) {
			session.setAttribute("msg", "기존 비밀번호가 일치하지 않습니다.");
			response.sendRedirect(request.getContextPath()+"/member/updatePassword");
			return;
		}
		
		//기존비밀번호가 맞아서 비밀번호를 업데이트 하는 과정
		//멤버객체 비밀번호 수정
		member.setPassword(newPassword);
		
		int result = new MemberService().updateMember(member);

		// 로그인 성공 실패 분기처리
		if (result > 0) {
			System.out.println("회원정보변경 성공!!!");
			if(session!=null) {
				session.setAttribute("msg", "비밀번호 변경 성공!!!");
				session.setAttribute("loginMember", member);
			}
		} else {
			System.out.println("회원정보변경 실패...");
			if(session!=null) {
				session.setAttribute("msg", "비밀번호 변경 실패...");
			}
		}

		response.sendRedirect(request.getContextPath()+"/member/memberView");
	}
}
