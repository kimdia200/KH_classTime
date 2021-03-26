package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet({ "/MemberLogoutServlet", "/member/logout" })
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 무효화 : 세션에 저장된 속성값을 모두 폐기 한다는 의미
		//getSession(false) = 만약 세션이 존재하지 않아도 새로 만들지 않고 null을 리턴 한다.
		//우리는 자료의 폐기가 목적이기때문에 새로 생성한걸 폐기하는건 의미 없음
		HttpSession session = request.getSession(false);
		
		//세션이 널이 아닐때만
		if(session != null) {
			//세션 무효화(모든 속성을 제거) 해주는 메서드
			session.invalidate();
		}
		
		//리다이렉트 : url변경
		response.sendRedirect(request.getContextPath());
	}
	
	
	/**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response); //요청이 get/post 뭐로 와도 동일하게 처리하겠다
    }
}
