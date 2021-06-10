package com.kh.spring.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration //@Configuration 은 본 자바 파일이 bean에 대한 설정파일임을 암시해주는 어노테이션
@EnableWebSocket //Websocket활성화 어노테이션
public class WebsocketConfig implements WebSocketConfigurer{
	
	@Autowired
	WebsocketHandler websocketHandler;
	

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//client의 const ws = new WebSocket("ws://localhost:9090/spring/websooocket")와 대응한다
		registry
		.addHandler(websocketHandler, "/websooocket")
		.withSockJS(); //server side에서도 sockjs선언
	}
	
}
