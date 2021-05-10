package com.kh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.student.model.exception.NoMatchingStudentException;
import com.kh.mybatis.student.model.service.StudentService;
import com.kh.mybatis.student.model.service.StudentServiceImpl;
import com.kh.mybatis.student.model.vo.Student;

public class UpdateStudentController extends AbstractController{

	private StudentService studentService = new StudentServiceImpl();
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		try {
			//1. 사용자 입력값 처리
			
			Student student = new Student();
			student.setNo(no);
			student.setName(name);
			student.setTel(tel);
			
			System.out.println("student@controller = "+student);
			
			//2. 업무로직
			int result = studentService.updateStudent(student);
			if(result==0) {
				throw new NoMatchingStudentException(String.valueOf(no));
			}
			
			//3. 사용자 피드백
			request.getSession().setAttribute("msg", "사용자 정보 수정 성공");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "redirect:/student/selectOne.do?no="+no;
	}
	
}
