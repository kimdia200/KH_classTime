package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDelete
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		no, boardNo
		int no = Integer.parseInt(request.getParameter("no"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		System.out.println("no@servlet = "+ no);
		System.out.println("boardNo@servlet = "+boardNo);
		
		int result = 0;
		result = boardService.deleteComment(no);
		HttpSession session = request.getSession();
		if(result >0) {
			session.setAttribute("msg", "댓글이 삭제되었습니다.");
		}else {
			session.setAttribute("msg", "댓글 삭제에 실패 했습니다.");
		}
		
		String url = request.getHeader("Referer");
		response.sendRedirect(url);
	}

}
