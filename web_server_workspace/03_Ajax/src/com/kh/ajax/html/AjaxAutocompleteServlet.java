package com.kh.ajax.html;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/autocomplete")
public class AjaxAutocompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final List<String> list = 
			new ArrayList<String>(Arrays.asList(
					"강종성",																															
					"김경태",																															
					"김상훈",																															
					"김영미",																															
					"김윤수",																															
					"김주연 (93)",																															
					"김주연 (97)",																															
					"김한성",																															
					"박요한",																															
					"박정현",																															
					"배기원",																															
					"서민성",																															
					"손세라",																															
					"송민성",																															
					"송준호",																															
					"윤기상",																															
					"이승우",																															
					"이운산",																															
					"이원영",																															
					"이재성",																															
					"장호재",																															
					"전유진",																															
					"정문주",																															
					"주소민",																															
					"천호현",																															
					"최민순",																															
					"최한성",																															
					"황윤진",
					"남윤지"																															
				));

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값
		String search = request.getParameter("search");
		
		//2. 업무로직
		List<String> resultList = new ArrayList<String>();
		for(String name : list) {
			if(name.contains(search)) {
				resultList.add(name);
			}
		}
		System.out.println(resultList);
		
		//3. 응답처리
		response.setContentType("text/csv; charset=utf-8");
		PrintWriter out = response.getWriter();
		for(String name:resultList) {
			out.println(name);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
