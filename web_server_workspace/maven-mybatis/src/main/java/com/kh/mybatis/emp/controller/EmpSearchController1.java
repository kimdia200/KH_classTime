package com.kh.mybatis.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.emp.model.service.EmpService;
import com.kh.mybatis.emp.model.service.EmpServiceImpl;

public class EmpSearchController1 extends AbstractController{
	
	private EmpService empService = new EmpServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 입력값
			String searchType = request.getParameter("searchType");
			String searchKeyword = request.getParameter("searchKeyword");
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("searchType",searchType);
			param.put("searchKeyword",searchKeyword);
			
			System.out.println("param@controller = "+ param);
			
			//2. 업무로직
			List<Map<String, Object>> list = null;
			if(searchType==null || searchKeyword==null) {
				list = empService.selectAllEmp();
			} else {
				list = empService.search1(param);
			}
			System.out.println("List@controller = "+list);
			//3. jsp위임
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return "emp/search1";
	}
}
