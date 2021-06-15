package com.kh.spring.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 
 * Stomp
 * - MessageBroker방식 처리
 * - publish 발행/subscribe 구독 패턴 처리
 * 		- 특정url을 구독하는 subscriber에게 발행한 메세지를 전달
 *
 */

@Configuration //@Configuration 은 본 자바 파일이 bean에 대한 설정파일임을 암시해주는 어노테이션
@EnableWebSocketMessageBroker
public class StompConfig implements WebSocketMessageBrokerConfigurer{

	/**
	 * client에서 최초 websocket 접속 url을 설정
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
			.addEndpoint("/stommmp")
			.withSockJS(); //server side에서도 sockjs선언
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//1. SimpleBroker
		//enableSimpleBroker메서드는 리턴타입이 가변인자로 접두어를 계속 추가가능
		registry
			.enableSimpleBroker("/topic","/bpp","/pull","/notice");
		
		//2. MessageHandler : 
		// app/a/..... = @MessageMapping("a") 라고 쓰면 사용할수있음
		registry
			.setApplicationDestinationPrefixes("/app","/push","/admin");
	}
}
