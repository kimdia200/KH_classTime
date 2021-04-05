package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import common.MvcUtils;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet({ "/BoardListServlet", "/board/boardList" })
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BoardService boardService = new BoardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0. 인코딩 처리는 EncodingFilter가 선처리
		// 1. 사용자 입력값
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
		}

		// 2. 업무로직
		// a. contents 영역 : start ~ end
		int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;

		List<Board> list = null;

		// 페이지당 표시할 목록을 가져와야함 
		list = boardService.selectPageList(start, end);
		System.out.println("list@servlet : " + list);

		// totalContents 는 현재 게시글이 몇개 있나 count(*)값
		int totalContents = boardService.selectBoardCount();
		System.out.println("토탈컨텐츠 : " + totalContents);
		String url = request.getRequestURI();

		// b. pageBar영역(스태틱 메서드 사용)
		String pageBar = MvcUtils.getPageBar(cPage, numPerPage, totalContents, url);
		
		//4. JSP에 html 응답메세지 작성 위임
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
