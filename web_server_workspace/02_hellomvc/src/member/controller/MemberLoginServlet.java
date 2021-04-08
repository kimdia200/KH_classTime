package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.MvcUtils;
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
//		request.setCharacterEncoding("utf-8");
		
		//2. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
		//우리가 구현한 비밀번호 암호화는  단방향 암호화이기 때문에 DB에 있는 값을 복호화 하는게 아니라
		//로그인할때 입력받은 값을 다시 암호화 하여 암호화 한 값끼리 배교한것
		String password = MvcUtils.getSha512(request.getParameter("password"));
		String saveId = request.getParameter("saveId");
		System.out.println("memberId@Servlet = " + memberId);
		System.out.println("password@Servlet = " + password);
		System.out.println("password@Servlet = " + saveId);
		
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
		
		HttpSession session = request.getSession(true);
		if(member !=null && password.equals(member.getPassword())){
			//로그인성공
//			request.setAttribute("msg", "로그인에 성공했습니다.");
			//로그인을 유지하기 위해선 request에 담는게 아니라 session에 담는다.
			//getsession(create:Boolean) 기본값은 true로서
			//존재하지 않으면 새로 생성하겠다는 의미임
			System.out.println(session.getId());
			session.setAttribute("loginMember", member);
			
			
			//session timeout : web.xml보다 우선순위 높음
			//초단위로 작성
//			session.setMaxInactiveInterval(30);
			
			
			//saveId : cookie 처리
			Cookie c = new Cookie("saveId",memberId);
			c.setPath(request.getContextPath()); // setpath = 쿠키를 전송할 url
			if(saveId != null){
				//saveId  체크시
				//setMaxAge를 설정해주면 session의 생명주기와 다르게 원하는 만큼 설정해줄수있음 단위(초)
				c.setMaxAge(7*60*60*24);//7일짜리 영속쿠키로 지정
			}else {
				//saveId 체크 해제시
				//쿠키를 제거해주는 방법은 setMaxAge를 0으로 해서 삭제 해주는 방법
				c.setMaxAge(0);//0으로 해서 즉시삭제
			}
			response.addCookie(c);
			
			
			
		}else {
			//로그인 실패
//			request.setAttribute("msg", "로그인에 실패했습니다.");
			//request.getContextPath() -> "/mvc" 리턴
			
//			request.setAttribute("loc", request.getContextPath());
			//4. 응답 메시지 처리 - html을 만들기 위해 out을 쓸수도 있고 jsp에 위임 할 수 도 있다, 추가적으로 redirecte도 있음
//			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/index.jsp");
//			reqDispatcher.forward(request, response);

			
			//redirect방식으로 변경하면서 더이상 request로는 전달불가능함
			session.setAttribute("msg", "로그인에 실패했습니다.");
		}
		
		//4.redirect : url변경
		//성공하든 실패하든 redirect방식으로 변경
		System.out.println("이전 페이지 : "+request.getHeader("Referer"));
//		response.sendRedirect(request.getContextPath());
		response.sendRedirect(request.getHeader("Referer"));
	}
}
