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

public class UpdateEmpController extends AbstractController {

	private EmpService empService = new EmpServiceImpl();

	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값
		int empId = Integer.parseInt(request.getParameter("empId"));

		// 2. 업무로직 : 직급목록, 부서목록, 사원 1명 정보(부서명, 직급명)
		List<Map<String, Object>> jobList = empService.selectJobList();
		List<Map<String, Object>> deptList = empService.selectDeptList();
		Map<String, Object> emp = empService.selectEmpOne(empId);

		System.out.println("emp@controller = " + emp);
		System.out.println("jobList@controller = " + jobList);
		System.out.println("deptList@controller = " + deptList);

		request.setAttribute("jobList", jobList);
		request.setAttribute("deptList", deptList);
		request.setAttribute("emp", emp);
		return "emp/updateEmp";
	}

	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 사용자 입력값
		Map<String, Object> map = new HashMap<String, Object>();
		int empId = Integer.parseInt(request.getParameter("empId"));
		map.put("empId", empId);

		String jobCode = null;
		if (request.getParameter("jobCode") != null && !request.getParameter("jobCode").equals("")) {
			jobCode = request.getParameter("jobCode");
			map.put("jobCode",jobCode);
		}
		
		String deptCode = null;
		if (request.getParameter("deptCode") != null && !request.getParameter("deptCode").equals("")) {
			deptCode = request.getParameter("deptCode");
			map.put("deptCode",deptCode);
		}
		System.out.println("empId@controller = " + empId);
		System.out.println("jobCode@controller = " + jobCode);
		System.out.println("deptCode@controller = " + deptCode);

		// 2. 업무로직 : 직급목록, 부서목록, 사원 1명 정보(부서명, 직급명)
		List<Map<String, Object>> jobList = empService.selectJobList();
		List<Map<String, Object>> deptList = empService.selectDeptList();
		Map<String, Object> emp = empService.selectEmpOne(empId);
		int result = empService.updateEmp(map);
		if(result>0) {
			request.getSession().setAttribute("msg", "정보 수정 성공");
		}else{
			request.getSession().setAttribute("msg", "정보 수정 실패");
		}
		
		System.out.println("emp@controller = " + emp);
		System.out.println("jobList@controller = " + jobList);
		System.out.println("deptList@controller = " + deptList);
		
		request.setAttribute("jobList", jobList);
		request.setAttribute("deptList", deptList);
		request.setAttribute("emp", emp);
		
		return "redirect:/emp/updateEmp.do?empId="+empId;
	}

}
