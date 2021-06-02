package com.kh.spring.menu.model.vo;

public class MenuBuilderTest {
	public static void main(String[] args) {
		MenuBuilderTest mt = new MenuBuilderTest();
		mt.test1();
	}
	
	private void test1() {
		//1. 기본생성자 + setter
		//2. 파라미터 생성자
		//3. builder패턴 Menu객체에 @builder 어노테이션을 적용하여 빌더패턴으로 생성 가능하게 끔 구현함
		
		Menu m = 
		Menu.builder()
			.id(1)
			.name("도토리묵")
			.restaurant("다람쥐네")
			.price(8000)
			.build();
		System.out.println(m);
	}
}
