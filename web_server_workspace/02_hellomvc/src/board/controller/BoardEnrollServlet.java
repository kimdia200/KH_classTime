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
 * Servlet implementation class BoardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	/**
	 * 0. form의 속성 enctype = "multipart/form-data" 추가
	 * 1. MultipartRequest 객체 생성 : 서버컴퓨터 파일 저장
	 * 		- request
	 * 		- 저장경로
	 * 		- encoding
	 * 		- 최대허용크기
	 * 		- 파일명 변경정책 객체 (똑같은 이름의 파일을 올려도 이름이 겹쳐지지않게 해주는 객체)
	 * 
	 * 2. db에 파일정보를  저장
	 * 		- 사용자가 저장한 파일명 (original_fileName)
	 * 		- 실제 저장된 파일명(renamed_fileName)
	 * 
	 * MultipartRequest객체를 사용하면, 기존 HttpServletRequest에서는 사용자입력값을 접근 할 수 없다.
	 * 사용자 입력값도 MultipartRequest에서 꺼내야함
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
//		FileRenamePolicy policy = new DefaultFileRenamePolicy();
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
//		title,writer,upFile,content
		String title = multipartRequest.getParameter("title");
		String writer = multipartRequest.getParameter("writer");
		String content = multipartRequest.getParameter("content");
		
		//업로드한 파일명
		String originalFileName = multipartRequest.getOriginalFileName("upFile");
		String renamedFileName = multipartRequest.getFilesystemName("upFile");
		
		//2-2. 업무로직 :db에 insert
		Board b = new Board();
		b.setTitle(title);
		b.setWriter(writer);
		b.setContent(content);
		
		if(originalFileName != null) {
			Attachment attach = new Attachment();
			attach.setOriginalFileName(originalFileName);
			attach.setRenamedFileName(renamedFileName);
			b.setAttach(attach);
		}
		
		int result =0;
		result = boardService.insertBoard(b);
		
		
		//2-3. DML요청 : 리다이렉트 & 사용자피드백
		///mvc/board/boardList
		HttpSession session = request.getSession();
		if(result>0) {
			session.setAttribute("msg", "게시글 등록 성공");
		}else {
			session.setAttribute("msg", "게시글 등록 실패");
			response.sendRedirect(request.getContextPath()+"/board/boardList");
		}
		
		int board_no = boardService.lastBoardNo();
		
		response.sendRedirect(request.getContextPath()+"/board/boardView?no="+board_no);
	}

}
