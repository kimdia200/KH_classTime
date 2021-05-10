package com.kh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.student.model.exception.NoMatchingStudentException;
import com.kh.mybatis.student.model.service.StudentService;
import com.kh.mybatis.student.model.service.StudentServiceImpl;

public class DeleteStudentController extends AbstractController{

	private StudentService studentService = new StudentServiceImpl();
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			//1. 사용자 입력값 처리
			int no = Integer.parseInt(request.getParameter("no"));
			//2. 업무로직
			int result = studentService.deleteStudent(no);
			if(result==0) {
				throw new NoMatchingStudentException(String.valueOf(no));
			}
			
			//3. 사용자 피드백
			request.getSession().setAttribute("msg", "사용자 정보 삭제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "redirect:/student/selectOne.do";
	}

	
}
