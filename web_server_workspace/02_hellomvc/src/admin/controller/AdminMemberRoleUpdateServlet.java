package admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberRoleUpdateServlet
 */
@WebServlet({ "/AdminMemberRoleUpdateServlet", "/admin/memberRoleUpdate" })
public class AdminMemberRoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 작업은 Filter에서 처리됨
		
		//값처리
		String memberId = request.getParameter("memberId");
		String memberRole = request.getParameter("memberRole");
		
		//업무로직 : id로 조회 해서 나머지 값을 가져오고 memberRole만 최신화 해준뒤 update
		//코드의 재활용 함
		Member member = memberService.selectOne(memberId);
		
		member.setMemberRole(memberRole);
		
		int result = 0;
		result = memberService.updateMember(member);
		HttpSession session = request.getSession();
		
		//업데이트 성공시
		if(result >0) {
			session.setAttribute("msg", memberId+"회원의 권한을 " + (memberRole.equals(MemberService.ADMIN_ROLE) ? "관리자" : "일반으")+"로 변경 완료함");
			
			//혹시 변경하는 아이디가 현재 접속한 아이디면 loginMember(현재접속정보를 담은 세션)도 최신화
			if(((Member)session.getAttribute("loginMember")).getMemberId().equals(memberId)) {
				session.setAttribute("loginMember", member);
			}
		}
		//업데이트 실패시
		else {
			session.setAttribute("msg", memberId+"회원의 권한 변경 실패함...");
		}
		
		response.sendRedirect(request.getContextPath()+"/admin/memberList");
//		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(request, response);;
	}
}
