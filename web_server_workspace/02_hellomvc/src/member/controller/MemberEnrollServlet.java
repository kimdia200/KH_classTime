package member.controller;

import static member.model.service.MemberService.MEMBER_ROLE;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet({ "/MemberEnrollServlet", "/member/memberEnroll" })
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//Get방식 요청시 
	//회원가입 페이지가 열리도록
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp").forward(request, response);
	}

	//Post방식 요청시
	//회원가입 처리 - db에 저장
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. 값처리
//		NULL
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
		
		String hobby="";
		for(int i=0;i<hobbys.length;i++) {
			hobby+=hobbys[i];
			if(i!=hobbys.length-1)
				hobby+=",";
		}
		
		
		System.out.println(memberId+"\n"+	
				password+"\n"+
				password2+"\n"+
				memberName+"\n"+
				birthDay+"\n"+
				email+"\n"+
				phone+"\n"+
				address+"\n"+
				gender+"\n"+
				hobby+"\n"
				);
		
		//3. 업무 로직
		HttpSession session = request.getSession(true);
		
		//이미 존재하는 회원인지 확인
		Member member = new MemberService().selectOne(memberId);
		if(member!=null) {
			System.out.println("회원가입 실패...(이미 존재하는 회원입니다.)");
			session.setAttribute("signUpLog", "회원가입 실패...");
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		int year = Integer.parseInt(birthDay.substring(0, 4));
		int month = Integer.parseInt(birthDay.substring(5, 7));
		int dayOfMonth = Integer.parseInt(birthDay.substring(8));
		Date birthday = new Date(new GregorianCalendar(year, month, dayOfMonth).getTimeInMillis());
		//MemberRole(Service에서 임포트), birthDay(형변환), enrollDate(새로하나 만듬)
		member = new Member(memberId, password, memberName, MEMBER_ROLE, gender, birthday, email, phone, address, hobby, new Date(Calendar.getInstance().getTimeInMillis()));
		
		int result = new MemberService().insertMember(member);
		if(result>0) {
			System.out.println("회원가입 성공!!!");
			session.setAttribute("signUpLog", "회원가입 성공!!!");
		}
		else {
			System.out.println("회원가입 실패...");
			session.setAttribute("signUpLog", "회원가입 실패...");
		}
		
		//로그인 성공 실패 분기처리
		response.sendRedirect(request.getContextPath());
	}
	
	

}
