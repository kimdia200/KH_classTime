package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * @WebServlet ~~~~ 은 web.xml에서 처리해야할 서블릿 등록을 자동으로 처리해줌
 * 
 * - urlPatterns:String[]
 * - name:String
 * 의 속성을 갖는다
 */
@WebServlet(name = "member/login", urlPatterns = { "/member/login" })
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//MemberLoginServlet.java가 controller의 역할을함
		
		
		//1. encoding처리 (대소문자 상관없음)
		//post방식만 인코딩 처리 필요함
		request.setCharacterEncoding("utf-8");
		
		//2. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		System.out.println("memberId@Servlet = " + memberId);
		System.out.println("password@Servlet = " + password);
		
		//3. 업무로직
		//memberId로 회원객체를 조회
		Member member = memberService.selectOne(memberId);
		System.out.println("member@Servlet =" + member);
		
		
		//로그인 성공/실패여부 판단
		//1. 로그인 성공 : member !=null && password.equals(member.getPassword())
		//2. 로그인 실패 : 
		//				아이디가 존재하지 않을때 member == null
		//				비밀번호가 틀릴때 member!=null && password.equals(member.getPassword())==false
		//그런데 실패의 경우 비밀번호가 틀릴때 비밀번호만 틀리다고 표시해주면 아이디가 맞다는 힌트를 주기 때문에 그냥 실패로 통일
		
		if(member !=null && password.equals(member.getPassword())){
			//로그인성공
//			request.setAttribute("msg", "로그인에 성공했습니다.");
			//로그인을 유지하기 위해선 request에 담는게 아니라 session에 담는다.
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", member);
		}else {
			//로그인 실패
			request.setAttribute("msg", "로그인에 실패했습니다.");
			//request.getContextPath() -> "/mvc" 리턴
			request.setAttribute("loc", request.getContextPath());
		}
		
		//4. 응답 메시지 처리 - html을 만들기 위해 out을 쓸수도 있고 jsp에 위임 할 수 도 있다, 추가적으로 redirecte도 있음
		request.setAttribute("member", member);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/index.jsp");
		reqDispatcher.forward(request, response);
	}
}
