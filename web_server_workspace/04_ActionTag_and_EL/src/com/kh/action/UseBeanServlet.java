package com.kh.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.person.model.vo.Person;

/**
 * Servlet implementation class UseBeanServlet
 */
@WebServlet("/standard/useBean.do")
public class UseBeanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 업무로직에 의해 생성된 데이터
		Person person = new Person("kimdia200", "김윤수", '남', 28,  false);
		
		//2. jsp위임
		request.setAttribute("kimdia200", person);
		request.getRequestDispatcher("/standard/useBean.jsp").forward(request, response);
	}

}
