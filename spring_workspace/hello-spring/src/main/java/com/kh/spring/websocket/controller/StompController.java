package com.kh.spring.websocket.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.kh.spring.websocket.model.vo.Notice;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StompController {
	
	/**
	 *   사용자가 app/a로 전달시, /app가 setApplicationDestinationPrefixes에 등록되어있어서
	 *   @MessageMapping("a") messageHandler로 전달됨
	 *   - prefix는 생략하고 작성할 것.
	 *   
	 *   @SendTo("bpp/a")에 의해 SimpleBroker로 전달된다.
	 *   - SimpleBroker
	 */
	@MessageMapping("/a")
	@SendTo("/bpp/a")
	public String app(String msg) {
		log.debug("/app 요청 : {}", msg);
		return msg;
	}
	
	@MessageMapping("/to/{id}")
	@SendTo("/pull/{id}")
	public String app2(@DestinationVariable String id, String msg) {
		log.debug("dm 대상 {}, 내용 {}", id, msg);
		return id +" : "+ msg;
	}
	
	//강사님 공지보내기
	@MessageMapping("/notice")
	@SendTo("/notice")
	public Notice notice(Notice notice) {
		log.debug("notice = {}", notice);
		return notice;
	}
	
	@MessageMapping("/notice/{id}")
	@SendTo("/notice/{id}")
	public Notice noticeDM(Notice notice, @DestinationVariable String id) {
		log.debug("notice = {}", notice);
		log.debug("id = {}",id);
		return notice;
	}
}
