package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/boardCommentInsert")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 인코딩 처리 (필터에서 해줌)
		//1. 값 처리
//		boardNo, writer, commentLevel, commentRef, content
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String writer = request.getParameter("writer");
		int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
		int commentRef = Integer.parseInt(request.getParameter("commentRef"));
		String content = request.getParameter("content");
		
		BoardComment bc = new BoardComment(0, commentLevel, writer, content, boardNo, commentRef, null);
		System.out.println(bc);
		
		//2. 업무로직
		try {
			int result = boardService.insertBoardComment(bc);
			
			HttpSession session = request.getSession();
			if(result >0 ) {
				session.setAttribute("msg", "댓글 등록 성공");
			}else {
				session.setAttribute("msg", "댓글 등록 실패");
			}
			
			//3. 페이지 이동(위임)
			response.sendRedirect(request.getContextPath()+"/board/boardView?no="+bc.getBoardNo());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
