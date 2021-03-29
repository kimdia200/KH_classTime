package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet({ "/CheckIdDuplicateServlet", "/member/checkIdDuplicate" })
public class CheckIdDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 사용자 입력값 처리
				String memberId = request.getParameter("memberId");
				System.out.println("memberId@Servlet = " + memberId);

				// 2. 업무로직 :
				// 이미 존재하는 회원인지 확인
				Member member = new MemberService().selectOne(memberId);
				//member=null -> 사용가능한 id, member!=null -> 사용불가능(이미존재하는아이디)
				boolean available = member==null;
				request.setAttribute("available", available);
				System.out.println(available);
				
				//3. 응답처리 : 사용가능 여부를 jsp에서 html로 작성
				RequestDispatcher requestDispatcher = 
						request.getRequestDispatcher("/WEB-INF/views/member/checkIdDuplicate.jsp");
				requestDispatcher.forward(request, response);
	}

}
