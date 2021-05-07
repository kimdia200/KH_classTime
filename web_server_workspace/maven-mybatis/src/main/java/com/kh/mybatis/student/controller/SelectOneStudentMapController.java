package com.kh.mybatis.student.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.student.model.service.StudentService;
import com.kh.mybatis.student.model.service.StudentServiceImpl;

public class SelectOneStudentMapController extends AbstractController{
	
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		
		//2. 업무로직
		Map<String, Object> student = studentService.selectOneStudentMap(no);
		System.out.println("student@controller = "+student);
		
		//3. json문자열을 응답메세지에 출력
		response.setContentType("application/json; charset=utf-8");
		
		//new Gson 말고 GsonBuilder를 쓰면 날짜형식의 포멧을 설정가능하다
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		gson.toJson(student, response.getWriter());
		
		//응답 메시지에 json문자열을 직접 출력하므로 리턴값은 null로 한다
		//Dispatcher에서 switch문에서 아무일도 없고 밑에 redirect부분에서는 문제없이 처리됨
		return null;
	}
}
