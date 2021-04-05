package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리
//		title,writer,upFile,content
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String upFile = request.getParameter("upFile");
		String content = request.getParameter("content");
		//2. 업무로직 :db에 insert
		Board b = new Board();
		b.setTitle(title);
		b.setWriter(writer);
		b.setContent(content);
		int result =0;
		result = boardService.insertBoard(b);
		
		
		//3. DML요청 : 리다이렉트 & 사용자피드백
		///mvc/board/boardList
		HttpSession session = request.getSession();
		if(result>0) {
			session.setAttribute("msg", "게시글 등록 성공");
		}else {
			session.setAttribute("msg", "게시글 등록 실패");
		}
		response.sendRedirect(request.getContextPath()+"/board/boardList");
	}

}
