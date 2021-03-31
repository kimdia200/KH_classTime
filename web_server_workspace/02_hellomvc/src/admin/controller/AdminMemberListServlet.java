package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */
@WebServlet({ "/AdminMemberListServlet", "/admin/memberList" })
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService = new MemberService();   
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1. 업무로직 : DB에서 전체 회원 조회
		List<Member> list = null;
		
		list = memberService.selectList();
		System.out.println("list@servlet : "+list);
		
		//2. JSP에 html 응답메세지 작성 위임
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(request, response);
	}

}
