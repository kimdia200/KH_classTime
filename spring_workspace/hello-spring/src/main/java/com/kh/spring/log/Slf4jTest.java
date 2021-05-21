package com.kh.spring.log;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * PSA Portable Service Abstraction
 * 
 * Slf4j - 스프링이 제공하는 logging 추상체
 * 
 * 구현체
 * 1. log4j
 * 2. java.util.logging
 * 3. apache logging 
 * 4. logback
 * 
 * 기능들이 조금씪 다르다
 */

@Slf4j
public class Slf4jTest {

	//private static final Logger log = LoggerFactory.getLogger(Slf4jTest.class);
	
	public static void main(String[] args) {
		//log4j 에는 fatal이 없다
		//log.fatal("fatal");
		
		//요런식으로 파라미터도 사용할수있는건 log4j에서만 가능하다
		log.error("error - {}", "메세지메세지");
		log.warn("warn");
		log.info("info");
		log.debug("debug");
		log.trace("trace");
	}

}
