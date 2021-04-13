package com.kh.ajax.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.member.model.vo.Member;

@WebServlet("/json")
public class AjaxJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값
		String searchId = request.getParameter("searchId");
		System.out.println(searchId);
		
		//2. 업무로직
		//DB에서 가져온 데이터라고 가정하자~
		List<Member> list = new ArrayList<Member>();
		list.add(new Member("bibi", "비비", "비비.png"));
		list.add(new Member("IU", "아이유", "아이유.png"));
		list.add(new Member("maeTtuck", "유재석", "유재석.jpg"));
		list.add(new Member("hwang", "황제성", "hwang.jpg"));

		//검색어가 존재하는 경우
		Member member= null;
		if(searchId != null) {
			Iterator<Member> it = list.iterator();
			while(it.hasNext()) {
				Member m = it.next();
				if(m.getId().equals(searchId)) {
					System.out.println(m);
					member=m;
					break;
				}
			}
		}
		
		//3. JSON 문자열로 변환 및 응답메시지에 출력하기
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(searchId !=null ? member : list);
		System.out.println(jsonStr);
		PrintWriter out = response.getWriter();
		out.println(jsonStr);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값
		//json이기 때문에 id=~~~~&name=@@@@@ 로 넘어올것
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		//2.업무로직
		
		//3.비동기로 처리했기 때문에 redirect 안해줘도 되고 응답메시지만 작성해주면됨
		response.setContentType("text/plain; charset=utf-8");
		response.getWriter().println("회원가입성공!");
		
	}

}
