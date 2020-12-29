package kh.java.api.wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * boolean - Boolean
 * 
 * char - Character
 * 
 * byte - Byte
 * short - Shoert
 * int - Integer
 * long - Long
 * 
 * float -Float
 * double - Double
 */
public class WrapperClassTest {

	public static void main(String[] args) {
		WrapperClassTest wc = new WrapperClassTest();
		wc.test2();
	}

	public void test1() {
		int num=100;
		Integer i = new Integer(100);
		//auto-boxing : 기본형을 Wrapper에 담는것(자동으로 일어남)
		//auto-unboxing : Wrapper에 있는 값을 기본형으로 바꾸는것
		System.out.println(num+i); //객체랑 기본형이랑 더한다??원래는 말이안되는것
		
		//num + i.intValue 로 처리됨 intValue는 자동호출
		System.out.println(num+i.intValue());
		
		Integer j = 3; //auto-boxing 기본형을 참조형에 담았음~
		j = new Integer(3); //이렇게 처리가 된거임
		
		System.out.println(j==3);
		System.out.println(j.intValue()==3);
		System.out.println(j.hashCode());
		System.out.println(Integer.MAX_VALUE);
		
		//기본형이 아닌 참조형만 써야하는 경우
		//Generics
//		List<int> list = new ArrayList<>();  //불가능~
		List<Integer> list= new ArrayList<>();
	}
	/**
	 * WrapperClass는 형변환 stataic 메서드 지원.
	 * 문자열 데이터를 각각의 타입으로 변환이 가능하다.
	 */
	public void test2() {
		int i= Integer.parseInt("100"); //문자열 100을 정수100으로 바꿧음!
		System.out.println(i*100);
		
		double d = Double.parseDouble("123.456");
		System.out.println(d*100);
		
		boolean bool = Boolean.parseBoolean("true");
		System.out.println(!bool);
		
		char ch = "abc".charAt(0);
		System.out.println((int)ch);
		
	}
}
