package com.kh.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 *  @SpringBootConfiguration : springboot관련 설정
 *  @EnableAutoConfiguration : application-context를 관리
 *  @ComponentScan: 현재 실행클래스가 속한 base-package기준으로 annotation활성화
 *
 */
@SpringBootApplication
//이 어노테이션 덕분에 springProject가 잘돌아간다
public class HelloSpringbootApplication {

	public static void main(String[] args) {
		//프로젝트에서 사용하는 tomcat은 내장된것을 사용함
		SpringApplication.run(HelloSpringbootApplication.class, args);
	}

}
