package com.kh.spring.tv;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kh.spring.tv.model.vo.LgTv;
import com.kh.spring.tv.model.vo.SamsungTv;

public class TvBeanMain {

	public static void main(String[] args) {
		//ApplicationContext 생성
		//bean을 관리하는 단위
		String configLocation ="/application-context.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		
		System.out.println("----------------------------- Spring-contatiner bean 초기화 완료 -----------------------------");
		
		
		//bean으로 객체를 불러오는 방법
		//1. 객체를 전달해서 불러오기
		LgTv lgtv1 = context.getBean(LgTv.class);
		System.out.println(lgtv1);
		
		LgTv lgtv2 = context.getBean(LgTv.class);
		System.out.println(lgtv2);
		
		//2. 정해준 id값을 입력해서 불러오기, 이경우 object로 반환하기때문에 casting필수
		SamsungTv sstv = (SamsungTv)context.getBean("samsungTv");
		System.out.println(sstv);
		
		lgtv1.powerOn();
		sstv.powerOn();
		
		lgtv1.changeChannel(3);
		
		sstv.changeChannel(3);
		
	}

}
