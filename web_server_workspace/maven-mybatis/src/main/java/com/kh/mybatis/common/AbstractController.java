package com.kh.mybatis.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController {
	/**
	 * controller클래스에서 필요한 메소드를 override해서 사용하도록함.
	 * override 하지 않고 호출시, 예외를 던짐.
	 * @param request
	 * @param response
	 * @return String viewName - jsp경로 또는 redirect의 location
	 * @throws ServletException
	 * @throws IOException
	 */
	public String doGet(HttpServletRequest request, HttpServletResponse response)
								throws ServletException, IOException {
		throw new MethodNotAllowedException("GET");
	}
	
	public String doPost(HttpServletRequest request, HttpServletResponse response)
								throws ServletException, IOException {
		throw new MethodNotAllowedException("POST");
	}
}
