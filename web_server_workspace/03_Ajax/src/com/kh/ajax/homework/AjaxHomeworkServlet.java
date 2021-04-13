package com.kh.ajax.homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.homework.controller.SmartService;
import com.kh.homework.model.vo.Smart;
import com.kh.homework.model.vo.SmartUp;

@WebServlet("/smart")
public class AjaxHomeworkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SmartService smartService = new SmartService();

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SmartUp> list = null;
		list = smartService.rankFive();
		
		//3. 처리
		PrintWriter out = response.getWriter();
		for(SmartUp s : list) {
			out.println(s);
		}
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 값처리
		String pname = request.getParameter("pname");
		int amount = Integer.parseInt(request.getParameter("amount"));
		//2. 업무로직
		
		//DB에 삽입
		Smart smart = new Smart(pname, amount);
		int result = 0;
		result = smartService.insertSmart(smart);
		if(result>0) {
			System.out.println("주문 추가 성공");
		}else {
			System.out.println("주문 추가 실패");
		}
		
		List<Smart> list = null;
		list = smartService.selectRecentFive();
		
		//3. 처리
		response.setContentType("text/plain; charset=utf-8");
		PrintWriter out = response.getWriter();
		for(Smart s : list) {
			out.println(s);
		}
	}
	
	

}
