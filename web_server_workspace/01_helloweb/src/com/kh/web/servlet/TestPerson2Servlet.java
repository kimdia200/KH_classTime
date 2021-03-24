package com.kh.web.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestPerson2Servlet extends HttpServlet {

	public TestPerson2Servlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 0. 인코딩 선언
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

		// 2. 업무 로직
		String recommendation = "";
		String colorName = "";
		switch (color) {
		case "빨강":
			recommendation = "빨간 짬뽕";
			colorName = "red";
			break;
		case "노랑":
			recommendation = "노란 참외";
			colorName = "orange";
			break;
		case "초록":
			recommendation = "초록색 시금치";
			colorName = "green";
			break;
		case "파랑":
			recommendation = "파란 파워에이드";
			colorName = "blue";
			break;
		}

		// 3. html 작성을 jsp에 위임.
		
		//!!! recommendation 과 colorName은 내가 만든 값으로
		//forward로는 새로생성된 데이터를 보낼수 없음
		//jsp에 새로 생성된 data를 전달하기 위해 request에 속성으로 지정한다.
		//data는 Object타입으로 전송됨
		request.setAttribute("recommendation", recommendation);
		request.setAttribute("colorName", colorName);
		
		//   /(슬래시) 로 시작한다면, /WebContent 에서 조회함.
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/servlet/testPersonEnd.jsp");
		
		reqDispatcher.forward(request, response);
		//testPersonEnd.jsp로 바톤터치 한것, jsp또한 하나의 서블릿으로 보면된다.
		//더이상 아래부분에 작성하면 안됨
		
		// 3. 응답메세지 처리 html (아래의 주석 작업읠 위의 3번이 대체할수 있음)
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<title>개취 검사 결과</title>");
//		out.println("<style>");
//		out.println(".recommendation { font-size: 2em; color:" + colorName + "; text-decoration: underline;}");
//		out.println("</style>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println("<h1>개인 취향 검사 결과 Post	</h1>");
//		out.println("<p>" + name + "님의 개인 취향 검사 결과는</p>");
//		out.println("<p>" + color + "색을 좋아합니다.</p>");
//		out.println("<p>좋아 하는 동물은 " + animal + "입니다.</p>");
//		out.println("<p>좋아하는 음식은 " + Arrays.toString(foodArr) + "입니다</p>");
//		out.println("<hr>");
//		out.println("<p class='recommendation'>오늘은 " + recommendation + " 어떠세요??</p>");
//		out.println("</body>");
//		out.println("</html>");
	}
}
