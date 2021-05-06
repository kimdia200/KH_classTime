package com.kh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.student.model.service.StudentService;
import com.kh.mybatis.student.model.service.StudentServiceImpl;
import com.kh.mybatis.student.model.vo.Student;

public class InsertStudentController extends AbstractController {
	//Controller가 의존하는 service단 객체는 interface를 통해 제어한다.
	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/student/insertStudent";
	}

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
		request.setCharacterEncoding("utf-8");
		//1. 사용자 입력값 처리
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		Student std = new Student();
		std.setName(name);
		std.setTel(tel);
		
		//2. 업무로직
		int result = studentService.insertStudent(std);
		
		//3. 사용자피드백 및 리다이렉트
		request.getSession().setAttribute("msg", "학생 등록 성공");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "redirect:/student/insertStudent.do";
	}
	
}
