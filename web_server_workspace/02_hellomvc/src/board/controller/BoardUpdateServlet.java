package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MvcFileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		//2. 업무로직
		Board board = boardService.selectOneBoard(board_no);
		Attachment attach = boardService.selectOneAttach(board_no);
		board.setAttach(attach);
		request.setAttribute("board", board);
		
		
		//3. jsp포워딩
		request.getRequestDispatcher("/WEB-INF/views/board/boardUpdateForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//파일저장 
				//1. MultipartRequest객체 생성
				//   /WebContent/upload/board/업로드파일명.jpg
				//getServletContext() = application객체 반환
				//application.getRealPath = 웹루트 디렉토리의 절대경로 반환
				String saveDirectory = getServletContext().getRealPath("/upload/board");
				System.out.println("saveDirectory@servlet = "+saveDirectory);
				
				//최대 파일 허용 크기 10mb = 10 * 1kb * 1kb
				int maxPostSize = 10* 1024 * 1024;
				
				//인코딩
				String encoding = "utf-8";
				
				//파일명 변경정책 객체
//				FileRenamePolicy policy = new DefaultFileRenamePolicy();
				MvcFileRenamePolicy policy = new MvcFileRenamePolicy();
				
				MultipartRequest multipartRequest = new MultipartRequest(
															request, 
															saveDirectory,
															maxPostSize,
															encoding,
															policy);
				//파일저장
				//2. db에 게시글 / 첨부파일 정보 저장
				
				
				//2-1. 사용자 입력값 처리
//				title,writer,upFile,content
				int no = Integer.parseInt(multipartRequest.getParameter("no"));
				String title = multipartRequest.getParameter("title");
				String writer = multipartRequest.getParameter("writer");
				String content = multipartRequest.getParameter("content");
				
				//업로드한 파일명
				String originalFileName = multipartRequest.getOriginalFileName("upFile");
				String renamedFileName = multipartRequest.getFilesystemName("upFile");
				
				//삭제할 첨부파일 번호
				String attachNo = multipartRequest.getParameter("delfile");
				System.out.println("attachNo@servelt = "+attachNo);
				
				
				//2-2. 업무로직 :db에 insert
				Board b = new Board();
				b.setNo(no);
				b.setTitle(title);
				b.setWriter(writer);
				b.setContent(content);
				
				if(originalFileName != null) {
					Attachment attach = new Attachment();
					attach.setBoardNo(no);
					attach.setOriginalFileName(originalFileName);
					attach.setRenamedFileName(renamedFileName);
					b.setAttach(attach);
				}
				
				int result =0;
				if(attachNo != null) {
					result = boardService.deleteAttachment(attachNo);
				}
				
				result = boardService.updateBoard(b);
				
				
				//2-3. DML요청 : 리다이렉트 & 사용자피드백
				///mvc/board/boardList
				HttpSession session = request.getSession();
				if(result>0) {
					session.setAttribute("msg", "게시글 수정 성공");
				}else {
					session.setAttribute("msg", "게시글 수정 실패");
					response.sendRedirect(request.getContextPath()+"/board/boardView?no="+b.getNo());
				}
				
				response.sendRedirect(request.getContextPath()+"/board/boardView?no="+b.getNo());
	}
}
