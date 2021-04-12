package com.kh.ajax.text;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.vo.Member;

@WebServlet("/csv")
public class AjaxCsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 업무로직
		List<Member> list = new ArrayList<Member>();
		list.add(new Member("bibi", "비비", "비비.png"));
		list.add(new Member("IU", "아이유", "아이유.png"));
		list.add(new Member("maeTtuck", "유재석", "유재석.jpg"));
		list.add(new Member("hwang", "황제성", "hwang.jpg"));
		
		
		//2. 응답처리
		response.setContentType("text/csv; charset=utf-8");
		PrintWriter out = response.getWriter();
		for(Member m : list) {
			out.println(m);
		}
		
	}
}
