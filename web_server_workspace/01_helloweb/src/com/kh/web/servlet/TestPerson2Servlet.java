package com.kh.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestPerson2Servlet extends HttpServlet{

	public TestPerson2Servlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 인코딩 선언
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		// 1-2. 복수개의 값을 가져올때
		String[] foodArr = request.getParameterValues("food");

		// 1-3. 값 확인
		System.out.println("name = " + name);
		System.out.println("color = " + color);
		System.out.println("animal = " + animal);
		System.out.println("foodArr = " + Arrays.toString(foodArr));

		
		//2. 업무 로직
		
		//3. 응답메세지 처리 html
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개취 검사 결과</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>개인 취향 검사 결과 Post	</h1>");
		out.println("<p>" + name + "님의 개인 취향 검사 결과는</p>");
		out.println("<p>" + color + "색을 좋아합니다.</p>");
		out.println("<p>좋아 하는 동물은 " + animal + "입니다.</p>");
		out.println("<p>좋아하는 음식은 " + Arrays.toString(foodArr) + "입니다</p>");
		out.println("</body>");
		out.println("</html>");
		
	}
}
