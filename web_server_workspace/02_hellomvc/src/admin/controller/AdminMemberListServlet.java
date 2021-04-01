package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MvcUtils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * paging Recipe
 * 
 * A. Contents Section : 쿼리
 * 		1. start rownum ~ end rownum
 * 		2. cpage(현재 페이지), numPerPage(페이지당 표시할 컨텐츠 수)
 * 		
 * 
 * 
 * 
 * B. Pagebar Section : html
 * 		1. totalContents 총 컨텐츠 수
 * 		2. totalPage 전체페이지수
 * 		3. pageBarSize 페이지바에 표시할 페이지 갯수 (ex.10개 페이지인데 5개 까지 표시해서 1,2,3,4,5 ...라고 )
 * 		4. pageNo 증감변수
 * 		5. pageStart ~ pageEnd : pageNo의 범위
 *		
 * 		
 * 
 */
@WebServlet({ "/AdminMemberListServlet", "/admin/memberList" })
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService = new MemberService();   
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1. 사용자 입력값 : 현재페이지 cPage
		
		final int numPerPage = 10; //10개씩 보여줄게요
		int cPage = 1; //현재 페이지
		try {
			//null값을 대입하면 NumberFormatException이 발생하기 때문!
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch (NumberFormatException e) {
			//에러가 발생하면 처음 접속했다는 것(parameter값이 없다는것) 이기때문에
			//1을 넣어주면 되므로 여기서 따로 처리 할 값은 없다
			//기본값 1유지
		}
		
		
		//2. 업무로직 : DB에서 전체 회원 조회
		//cPage 1 : 1 ~ 10
		//cPage 2 : 11 ~ 20
		//cPage 3 : 21 ~ 30
		int start = (cPage-1)*numPerPage+1;
		int end = cPage * numPerPage;
		
		
		List<Member> list = null;
		
		list = memberService.selectList(start, end);
		System.out.println("list@servlet : "+list);
		
		//totalContents 는 현재 회원이 몇명이 있나 count(*)값
		int totalContents = memberService.selectMemberCount();
		System.out.println("토탈컨텐츠 : "+totalContents);
		
		
		//3. pagebar 영역 작업
		//url = 이동할 페이지 
		String url = request.getRequestURI();
		
		//pagebar는 여러페이지에서 사용가능하기 때문에 스태틱 메서드로 따로 관리
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		
		//4. JSP에 html 응답메세지 작성 위임
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(request, response);
	}

}
