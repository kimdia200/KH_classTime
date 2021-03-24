package com.kh.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class menuServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		int sum = 0;

		String main = request.getParameter("main_menu");
		String side = request.getParameter("side_menu");
		String drink = request.getParameter("drink_menu");

		switch (main) {
		case "한우버거":
			sum += 5000;
			break;
		case "밥버거":
			sum += 4500;
			break;
		case "치즈버거":
			sum += 4000;
			break;
		}

		switch (side) {
		case "감자튀김":
			sum += 1500;
			break;
		case "어니언링":
			sum += 1700;
			break;
		}

		switch (drink) {
		case "콜라":
			sum += 1000;
			break;
		case "사이다":
			sum += 1000;
			break;
		case "커피":
			sum += 1500;
			break;
		case "밀크쉐이크":
			sum += 2500;
			break;
		}
		request.setAttribute("sum", sum);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/menu/menuEnd.jsp");
		reqDispatcher.forward(request, response);
	}
}
