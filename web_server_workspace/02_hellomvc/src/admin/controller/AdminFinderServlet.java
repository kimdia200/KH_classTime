package admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet({"/admin/memberFinder" })
public class AdminFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword= request.getParameter("searchKeyword");
		
		Map<String, String> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param@servlet = " + param);
		
		
		
		
		//2. 업무 로직
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e){
			System.out.println("cPage = 1로 유지");
		}
		
		int start = (cPage-1)*numPerPage+1;
		int end = cPage * numPerPage;
		List<Member> list = memberService.searchMember(param,start, end);
		System.out.println("list@servlet = "+list);
		int totalContents = memberService.selectFinderCount(param);
		System.out.println("검색한 멤버 갯수 : "+totalContents);
		
		//3. jsp에 html 응답메세지 작성 위임
		String url = request.getRequestURI()+"?searchType="+searchType+"&searchKeyword="+searchKeyword;
		System.out.println("URL = "+url);
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(request, response);
	}
}
