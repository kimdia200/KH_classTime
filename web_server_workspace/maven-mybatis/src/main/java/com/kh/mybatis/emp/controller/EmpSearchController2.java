package com.kh.mybatis.emp.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.emp.model.service.EmpService;
import com.kh.mybatis.emp.model.service.EmpServiceImpl;

public class EmpSearchController2 extends AbstractController {

	private EmpService empService = new EmpServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자입력값
			String searchType = request.getParameter("searchType");
			String searchKeyword = request.getParameter("searchKeyword");
			String gender = request.getParameter("gender");
			int salary = 0;
			try {
				salary = Integer.parseInt(request.getParameter("salary"));
			} catch(NumberFormatException e) {
			}
			String salaryCompare = request.getParameter("salaryCompare");
			String hireDate = request.getParameter("hire_date");
			String hiredateCompare = request.getParameter("hiredateCompare");
			
			//hire_date(문자열)이 아닌 sql.Date타입으로 처리
			Date hire_date = null;
			if(hireDate!= null && !"".equals(hireDate)) {
				hire_date = Date.valueOf(hireDate);
			}
			//요걸 던져주면 그냥 형변환 따로 안해도된다
			
			Map<String, Object> param = new HashMap<>();
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);
			param.put("gender", gender);
			param.put("salary", salary);
			param.put("salaryCompare", salaryCompare);
			param.put("hireDate",hireDate);
			param.put("hiredateCompare",hiredateCompare);
			param.put("hire_date",hire_date);
			System.out.println("param@controller = " + param);
			
			//2. 업무로직
			List<Map<String, Object>> list = empService.search2(param);
			System.out.println("list@controller = " + list);

			//3. jsp위임
			request.setAttribute("param", param);
			request.setAttribute("list", list);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return "emp/search2";
	}
	
	
}