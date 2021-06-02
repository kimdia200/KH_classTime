package com.kh.spring.menu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/menu")
public class MenuController {
	
	@GetMapping("/menu.do")
	public void menu() {
		
	}
	
	/**
	 * 현재 서버를 proxy로 rest server에 요청을 보낸다.
	 * 응답데이터를 그대로 client에게 다시 응답한다.
	 * proxy서버를 통해 데이터를 전달 받는경우 브라우저와 다르게 CORS SOP 제약조건이 없으므로
	 * 제한 없이 데이터를 사용 가능하다.
	 * 
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	@GetMapping(value = "/selectMenuList.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)//produces = 최종 리턴값 종류 및 인코딩 설정
	@ResponseBody
	//json문자열을 그대로 보내주기 위해 return type = String
	public String selectMenuList() throws Exception {
		BufferedReader br=null;
		try {
			//1. rest server 요청
			String menuUrl = "http://localhost:10001/springboot/menus";
			//java.net.URL, 예외는 그냥 throws 해버렸음
			URL url = new URL(menuUrl);
			br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			
			//2. 응답메세지 : 자바변수에 기록
			String data = "";
			StringBuilder sb = new StringBuilder();
			
			while((data = br.readLine()) != null) {
				sb.append(data);
			}
			log.debug("응답 json = {}", sb.toString());
			return sb.toString();
		} catch (Exception e) {
			log.error("menu 전체 조회 오류!");
			throw e;
		} finally {
			br.close();
		}
	}
}
