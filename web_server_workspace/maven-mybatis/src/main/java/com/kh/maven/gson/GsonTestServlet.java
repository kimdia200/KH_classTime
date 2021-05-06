package com.kh.maven.gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/gson.do")
public class GsonTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "gson Maker");
		map.put("server-time", System.currentTimeMillis());
		map.put("num",123456);
		
		response.setContentType("application/json; charset=utf-8");
		
		Gson gson = new Gson();
		//자바객체 map을 응답객체에 써주세요
		gson.toJson(map,response.getWriter());
		
	}

}
