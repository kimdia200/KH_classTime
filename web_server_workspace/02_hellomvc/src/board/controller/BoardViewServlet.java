package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.BoardComment;
import common.MvcUtils;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
       
    public BoardViewServlet() {
        super();
    }

    /**
     * 게시글 상세보기
     * 
     * -- board + attachment 조회
     * -- 조인없이 두번 쿼리요청 할것
     * 
     * 게시글 등록 성공시 바로 상세보기 페이지로 이동할 것.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//0.인코딩처리는 필터로했음
		
		//1. 값처리
		int no = Integer.valueOf(request.getParameter("no"));
		
		//2. 업무로직
		Board board = boardService.selectOneBoard(no);
		if(board != null) {
			System.out.println("게시글 조회성공");

			//xss공격 방지
			board.setTitle(MvcUtils.escapeHtml(board.getTitle()));
			board.setContent(MvcUtils.escapeHtml(board.getContent()));
			
			//개행문자를 br처리해줌
			board.setContent(MvcUtils.convertLineFeedToBr(board.getContent()));
			System.out.println(board);
		}else {
			System.out.println("게시글 조회 실패");
		}
		Attachment attach = boardService.selectOneAttach(no);
		if(attach!=null) {
			System.out.println("첨부파일 존재함");
			System.out.println("첨부파일 정보 : " + attach);
			board.setAttach(attach);
		}else {
			System.out.println("첨부파일 존재하지 않음");
		}
		
		//댓글 가져오기
		List<BoardComment> commentList = boardService.selectBoardCommentList(board.getNo());
		if(commentList != null) {
			System.out.println("댓글 리스트 받기 성공");
			System.out.println(commentList);
		}else {
			System.out.println("댓글 리스드 받기 실패");
		}
		
		request.setAttribute("board", board);
		request.setAttribute("commentList", commentList);
		
		//3. dispatcher
		request.getRequestDispatcher("/WEB-INF/views/board/boardView.jsp").forward(request, response);
	}


}
