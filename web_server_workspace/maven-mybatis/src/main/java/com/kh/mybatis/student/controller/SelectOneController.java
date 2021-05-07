package com.kh.mybatis.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.student.model.service.StudentService;
import com.kh.mybatis.student.model.service.StudentServiceImpl;
import com.kh.mybatis.student.model.vo.Student;

public class SelectOneController extends AbstractController {

	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 1. 사용자 입력값
			int no = 0;
			try {
				no=Integer.parseInt(request.getParameter("no"));
			} catch (Exception e) {
				//처리코드 없음
			}

			// 2. 업무로직

			// a.전체 학생수 조회
			int total = studentService.selectStudentCount();
			System.out.println("total@controller = " + total);

			// b.학생 조회
			if(no !=0) {
				Student student = studentService.selectOneStudent(no);
				System.out.println("student@controller = "+student);
				request.setAttribute("student", student);
			}
			
			// 3.jsp위임
			request.setAttribute("total", total);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return "/student/selectOne";
	}
}
