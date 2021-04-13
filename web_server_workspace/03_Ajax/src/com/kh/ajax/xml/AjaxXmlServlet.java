package com.kh.ajax.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxXmlServlet
 */
@WebServlet("/xml")
public class AjaxXmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 업무로직
		List<Member> list = new ArrayList<Member>();
		list.add(new Member("bibi", "비비", "비비.png"));
		list.add(new Member("IU", "아이유", "아이유.png"));
		list.add(new Member("maeTtuck", "유재석", "유재석.jpg"));
		list.add(new Member("hwang", "황제성", "hwang.jpg"));
		
		//2. jsp위임
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/xml/members.jsp").forward(request, response);

	}

}
