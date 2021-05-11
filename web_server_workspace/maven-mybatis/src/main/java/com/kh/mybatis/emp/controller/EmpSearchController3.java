package com.kh.mybatis.emp.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.common.AbstractController;
import com.kh.mybatis.emp.model.service.EmpService;
import com.kh.mybatis.emp.model.service.EmpServiceImpl;

public class EmpSearchController3 extends AbstractController {

	private EmpService empService = new EmpServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자입력값
			String[] jobCodeArr = request.getParameterValues("jobCode");
			String[] deptCodeArr = request.getParameterValues("deptCode");
			List<String> deptIdList = null;
			if(deptCodeArr != null) deptIdList = Arrays.asList(deptCodeArr);
			Map<String, Object> param = new HashMap<>();
			param.put("jobCodeArr",jobCodeArr);
			param.put("deptCodeArr",deptCodeArr);
			System.out.println(param);
			
			//2. 업무로직
			List<Map<String, Object>> deptList = empService.selectDeptList();
			List<Map<String, Object>> jobList = empService.selectJobList();
			List<Map<String, Object>> list = empService.search3(param);
			System.out.println("deptList = " + deptList);
			System.out.println(jobList);
			System.out.println("list@controller = " + list);

			//3. jsp위임
			request.setAttribute("deptIdList", deptIdList);
			request.setAttribute("deptList", deptList);
			request.setAttribute("jobList", jobList);
			request.setAttribute("list", list);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return "emp/search3";
	}
	
	
}