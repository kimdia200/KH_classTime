package com.kh.spring.websocket.config;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebsocketHandler extends TextWebSocketHandler{
	/**
	 * multithread에서 동기화를 지원하는 list
	 * 여러 쓰레드에서 접속할때 순서를 정해주고 해주는 arrayList상위버전
	 */
	List<WebSocketSession> sessionList = new CopyOnWriteArrayList<>();
	
	/**
	 * websocket연결 성공후 호출
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//연결요청이 일어나면 파라미터로 받은 세션을 세션리스트에 담아서 보관할것
		sessionList.add(session);
		log.debug("onopen({}) session : {}", sessionList.size(), session);
	}

	/**
	 * client가 message를 전송한 경우 호출
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//어떤메시지를 누가 보냈나
		log.debug("onmessage : {} from {}", message, session);
		
		//전달받은 메시지를 해당 채팅방의 사람들에게 모두 전송
		String sender = session.getId();
		
		for(WebSocketSession sess : sessionList) {
			TextMessage msg = new TextMessage(sender+" : "+message.getPayload());
			sess.sendMessage(msg);
		}
	}

	/**
	 * webSocket연결 해제후 호출
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//연결이 종료된다면 현재 관리하고있는 리스트에서 제거
		log.debug("onclose({}) session : {}", sessionList, session);
		sessionList.remove(session);
	}
	
}
