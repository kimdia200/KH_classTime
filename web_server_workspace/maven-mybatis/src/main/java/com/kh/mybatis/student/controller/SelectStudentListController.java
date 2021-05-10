package com.kh.mybatis.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.student.model.service.StudentService;
import com.kh.mybatis.student.model.service.StudentServiceImpl;
import com.kh.mybatis.student.model.vo.Student;

public class SelectStudentListController extends AbstractController {

	private StudentService studentService = new StudentServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 없음

		// 2. 업무로직
		try {

			List<Student> list = studentService.selectStudentList();
			System.out.println("list@controller = " + list);
			
			List<Map<String, Object>> mapList = studentService.selectStudentMapList();
			System.out.println("mapList@controller = " + mapList);
			
			// 3. jsp위임
			request.setAttribute("list", list);
			request.setAttribute("mapList", mapList);
			return "student/selectList";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return super.doPost(request, response);
	}

}
