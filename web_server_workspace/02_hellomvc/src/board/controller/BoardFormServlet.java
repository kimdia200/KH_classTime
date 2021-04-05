package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/boardForm")
public class BoardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.Get방식은 인코딩 처리 필요없음
		
		//1. 값은처리 해줄것이 없다.
		
		//2. 업무로직도 없다.
		
		//3. 단순 이동용 서블릿
		request.getRequestDispatcher("/WEB-INF/views/board/boardForm.jsp").forward(request, response);
	}

}
