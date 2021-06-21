package com.kh.jdk8.lambda;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 
 * Lambda표현식
 * java에서 함수형프로그래밍을 지원하기 위한 api(jdk8부터 사용가능)
 * 
 * - 객체지향프로그래밍(상태(field), 행동(method)을 객체로 관리)와 달리
 * 함수를 값으로써 취급한다.
 * - 상태관리를 하지 않고, 모든 것을 불변으로 처리한다.
 * 
 */
public class LambdaStudy {

	public static void main(String[] args) {
		LambdaStudy study = new LambdaStudy();
//		study.test0();
//		study.test1();
//		study.test2();
//		study.test3();
		study.test4();
	}
	
	/**
	 * @실습문제
	 */
	private void test4() {
		//현재시각 출력 람다식
		Runnable run = ()-> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			System.out.println(sdf.format(new Date()));
		};
		run.run();
		
		//로또 (1~45사이의 중복없는 난수 Set) 생성 람다식
		Supplier<Set<Integer>> sp = ()->{
			Set<Integer> set = new HashSet<>();
			while(set.size()<6) {
				set.add((int)(Math.random()*45)+1);
			}
			return set;
		};
		System.out.println(sp.get());
		
		//환율 계산기 람다식 : 원화 입력시 달러값을 리턴
		//1달러 = 1100원
		Function<Integer, Double> function = (krw)->krw/1100.0;
		System.out.println(function.apply(100000));
	}
	
	/**
	 * JDK가 제공하는 함수형 인터페이스
	 *  - 제네릭을 사용해서 람다식 작성 타임에 매개변수나 리턴타입이 결정되도록함.
	 *  
	 *  1. java.lang.runnable : 매개변수 없음 | 리턴값 없음 | run():void
	 *  2. java.util.function.Supplier<R> : 매개변수 없음 | 리턴 R | get():R  ----R = 제네릭
	 *  3. java.util.function.Consumer<T> : 매개변수 T | 리턴값 없음 | accept(T):void
	 *  4. java.util.function.Function<T, R> : 매개변수 T | 리턴 R | apply(T) : R
	 *  5. java.util.function.Predicate<T> : 매개변수 T | 리턴 Boolean | test(T):Boolean
	 */
	private void test3() {
		
		//1. Runnable 
		System.out.println("------------Runnable");
		Runnable r = () -> {
			//하고자 하는 처리를 여기에 입력하면된다
			for(int i=0; i<10; i++) {
				System.out.println(System.currentTimeMillis());
			}
		};
		r.run();
		
		//2. Supplier
		System.out.println("------------Supplier");
		Supplier<Long> sp = () -> System.currentTimeMillis();
		System.out.println(sp.get());
		
		//1~100사이의 난수를 리턴하는 람다식을 작성해보자
		sp = ()-> (long)(Math.random()*100)+1;
		System.out.println(sp.get());
		
		//3.Consumer : 매개변수 있고, 리턴값이 없음
		Consumer<String> con = (name)-> System.out.println("내 이름은 = "+name);
		con.accept("김윤수");
		
		Consumer<Date> printTime = date ->{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(sdf.format(date));
		};
		
		printTime.accept(new Date());
		
		//4. Function<T, R> 매개변수 있고 리턴값 있음.
		Function<String, Integer> function = str -> str.length();
		System.out.println(function.apply("안녕하세요"));
		System.out.println(function.apply("hello"));
		System.out.println(function.apply("bye bye"));
		
		
		//5. Predicate<T> : 매개변수 있고, 리턴타입이 boolean
		Predicate<Long> predicate = (n)-> n%3==0 && n%5==0;
		long num = sp.get();
		if(predicate.test(num)) {
			System.out.println("3의 배수 && 5의 배수 : "+num);
		}else {
			System.out.println("꽝");
		}
	}
	
	
	/**
	 * 함수형 인터페이스
	 * - 추상메서드가 딱 하나인 인터페이스를 베이스로 람다식을 작성 할 수 있다.
	 * - @FunctionalInterface 어노테이션을 사용하면, 문법오류를 컴파일타임에 확인 가능
	 */
	private void test2() {
		//파라미터 형식만 같다면 메서드 안의 작업내용은 내가 원하는 대로 커스텀 할 수 있음.
		
		Foo max = (a, b) -> a> b ? a : b;
		System.out.println(max.process(80, 77));
		
		Foo min = (a, b) -> a<b ? a : b;
		System.out.println(min.process(80,77));
		
		Foo sum = (a, b) -> a + b;
		System.out.println(sum.process(80, 77));
		
		//인터페이스를 통해 문자열 이름과 나이를 받아 출력 가능한 람다식 작성
		//1. 인터페이스
		//2. 람다식 작성
		//3. 람다식 호출
		Person p1 = (name, age) -> System.out.println("이름 : "+name+", 나이 : "+age);
		p1.print("김윤수", 29);
		
		//매번 인터페이스를 생성하고 호출하는게 불편하기때문에 JAVA JDK에서 기본 인터페이스를 제공한다.test3에서 실험함
		
	}
	@FunctionalInterface
	interface Person{
		void print(String name, int age);
	}
	
	/**
	 * 추상메서드를 두개 이상 작성시
	 * Invalid '@FunctionalInterface' annotation; LambdaStudy.Foo is not a functional interface
	 * 라고 에러메시지가 뜨는데 추상메서드가 두개이상이라서 함수형인터페이스가 아니라는것 
	 *
	 */
	@FunctionalInterface
	interface Foo{
		int process(int a, int b);
		//int process(int a);
	}
	

	/**
	 * 메소드만 전달 또는 변수에 저장이 불가능하므로, 인터페이스를 통해 처리해야한다.
	 * lambda식 사용시에는 인터페이스의 추상메소드가 단 하나만 존재해야한다.
	 * 보기에는 메소드만 전달하는 것처럼 보이지만, 실제로는 객체가 전달된 것임.
	 * 
	 */
	private void test1() {
//		Pita pita = (int a, int b) -> {
//			return Math.sqrt(a * a + b * b);
//		};

		//리턴절 한줄인 경우 {return } 생략가능
//		Pita pita = (int a, int b) -> Math.sqrt(a * a + b * b);
		
		//매개변수 선언부 자료형 생략가능
		Pita pita = (a, b) -> Math.sqrt(a * a + b * b);
		
		double c = pita.calc(100, 30);
		System.out.println("빗변 : " + c);
	}

	/**
	 * 피타고라스의 정리 : 빗변제곱 = 밑변제곱 + 높이제곱
	 * 
	 * 자바에서 메소드는 독립적으로 존재할 수 없다. 인자로 전달되거나, 리턴되거나 모두 불가능.
	 * 객체를 통해서만 전달 가능.
	 * lambda표현식 또한 이런 제한이 적용.
	 */
	private void test0() {
		//익명클래스 : 객체선언과 생성을 동시에 처리
		Pita pita = new Pita() {
			@Override
			public double calc(int a, int b) {
				return Math.sqrt(a * a + b * b);
			}
		};
		
		double c = pita.calc(100, 30);
		System.out.println("빗변 = " + c);
	}
	
	/**
	 * 빗변을 구하는 추상메소드 선언
	 */
	interface Pita {
		double calc(int a, int b);
	}
	

}