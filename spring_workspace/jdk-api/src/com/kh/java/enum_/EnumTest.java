package com.kh.java.enum_;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EnumTest {
	
	public static final int COLOR_BLACK=0;
	public static final int COLOR_RED=1;
	public static final int COLOR_BLUE=2;
	
	public static final int ITEM_OUTER = 0;
	public static final int ITEM_INNER = 1;
	public static final int ITEM_PANTS = 2;
	
	public static void main(String[] args) {
		EnumTest et = new EnumTest();
//		et.test1();
//		et.test2();
		et.test3();
	}
	
	/**
	 * 상수의 한계
	 * - 값에 대한 검증기능이 없다.
	 * - 코드 가독성, 전달력을 높여준다.
	 */
	private void test1() {
		Person p1 = new Person("홍길동", COLOR_BLACK);
		Person p2 = new Person("신사임당", COLOR_RED);
		System.out.println(p1);
		System.out.println(p2);
	}
	
	/**
	 * enum은 enum명, 상수명으로 접근한다.
	 *  - namespace를 통해서만 사용할 수 있다.
	 *  - 값에 대한 타입검증이 가능하다.
	 *  - 코드 가독성, 오타예방등의 기능
	 */
	private void test2() {
		Car car1 = new Car("소나타", CarColor.BLACK);
		Car car2 = new Car("그랜져", CarColor.WHITE);
		System.out.println(car1);
		System.out.println(car2);
	}
	
	/**
	 * 
	 *  enum도 내부값을 갖고 처리 할 수 있다.
	 *  MenuType.KR => kr
	 *  	
	 *
	 */
	private void test3() {
		Item item1 = new Item("젠틀몬스터", ItemType.GLASSES);
		Item item2 = new Item("르라보", ItemType.PERFUME);
		Item item3 = new Item("참존", ItemType.COSMETIC);
		
		//getter
		System.out.println(item1);
		System.out.println(item2);
		System.out.println(item3);
		
		//setter
		//{"name":"젠틀몬스터","itemType":123}
		//이걸 변환
		Item item4 = new Item("ㅇㅇㅇㅇㅇㅇ",ItemType.valueOf(123));
//		Item item4 = new Item("ㅇㅇㅇㅇㅇㅇ",new ItemType(123)); //enum은 생성자 호출이안된다
		System.out.println(item4);
	}
	
	@Data
	@AllArgsConstructor
	class Item{
		private String name;
		private ItemType itemType;
	}
	
	@Data
	@AllArgsConstructor
	class Car{
		private String name;
		private CarColor color;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	class Person{
		private String name;
		private int color;
	}
}
