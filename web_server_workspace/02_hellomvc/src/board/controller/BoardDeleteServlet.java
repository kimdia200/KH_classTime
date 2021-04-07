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
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 인코딩 처리 ( 필터에서 처리됨 )
		
//		error.jsp 테스트용
//		throw new BoardException("이용불편 ㅈㅅㅈㅅ");
		
		try {
			//1. 값처리
			int board_no = Integer.parseInt(request.getParameter("no"));
			
			//2. 업무로직
			int result = 0;
			result = boardService.deleteOneBoard(board_no);
			HttpSession session = request.getSession();
			if(result >0) {
				session.setAttribute("msg", "게시글이 삭제 되었습니다.");
			}else {
				session.setAttribute("msg", "게시글 삭제 실패.");
			}
			
			//3. 이동
			response.sendRedirect(request.getContextPath()+"/board/boardList");
		} catch (Exception e) {
			//예외 로깅
			e.printStackTrace();
			//예외페이지 전환을 위해 was에 예외던짐
			throw e;
		}
	}

}
