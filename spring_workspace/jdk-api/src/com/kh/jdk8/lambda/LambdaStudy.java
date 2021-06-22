package com.kh.jdk8.lambda;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
//		study.test4();
//		study.test5();
		study.test6();
	}
	/**
	 * 컬렉션도 람다식을 필요로 하는부분이 존재함
	 */
	public void test6() {
		//뒷부분 Integer생략안된데
		List<Integer> list = new ArrayList<Integer>() {
			{
				for(int i = 1; i<=10; i++) {
					add(i);
				}
			}
		};
		//특정 요소 제거(true이면 제거함) list.removeIf(Predicate<T>)
		list.removeIf(n->n%2==0);
		
		//요소 대체 list.replaceAll(UnaryOperator<T>)
		//UnaryOperator<T>: 매개변수와 리턴타입이 같은경우 Function<T,R> 대신 사용가능
		list.replaceAll(n->n*100);
		
		//모든 요소를 순회하는 list.forEach(Consumer<T>) 메서드를 사용해보자
		list.forEach(n-> System.out.println(n));
	}
	
	
	/**
	 * 메서드 참조 Method Reference 
	 * 람다식을 더욱 간결히 표현한 문법
	 * 베이스가 되는 함수형 인터페이스에 따라 달라질수있다.
	 * 
	 * $.ajax({
	 *  	success(data){},
	 *  	error:console.log
	 * });
	 * 처럼 줄여서 사용 할 수 있다.
	 * 
	 * 특징
	 * 1. static메서드를 간결하게 표현가능: Integer.parseInt("123") -> Integer::parseInt 라고 표현할수있다
	 * 2. non_static메서드도 사용가능 : "홍길동".equals(name) -> String::equals (두개의 인자를 받아서 처리)
	 * 3. 특정객체의 메서드: str::equals (한개의 인자를 받아서 처리)
	 * 4. 생성자 참조 : new Person() -> Person::new (함수형 인터페이스에 따라 여러 생성자를 호출 가능)
	 */
	private void test5() {
		//아래의 코드는 같은 코드이다
		//Consumer<String> printer = s -> System.out.println(s);
		Consumer<String> printer = System.out::println;
		printer.accept("홍길동");
		
		//1. static
		//Function<String, Integer> intParser = s->Integer.parseInt(s);
		Function<String, Integer> intParser = Integer::parseInt;
		int num = intParser.apply("1234567");
		System.out.println(num);
		System.out.println("------------------------------------");
		
		//2. non_static
		Function<String, Integer> strLength = String::length;
		int length = strLength.apply("123456789");
		System.out.println(length);
		
		//non_static메서드의 경우 메서드의 시그니처에 따라 다양하게 표현할수있다.
		//equals를 예로 들어 사용해보자 (generic이 세개가 필요하다 -> BiFunction이라는 제네릭 세개를 받는 함수형 인터페이스가 존재함)
		//만약 원하는 함수형 인터페이스를 자바에서 기본제공 해주지 않는다면 자신이 따로 인터페이스를 생성해야함
		
		//BiFunction<파라미터1타입, 파라미터2타입, 리턴타입>
		//BiFunction<String, String, Boolean> strEquals = (s1, s2) -> s1.equals(s2);
		BiFunction<String, String, Boolean> strEquals = String::equals;
		System.out.println(strEquals.apply("김윤수", "김윤수"));
		System.out.println("------------------------------------");
		
		//3. 특정객체 기준으로 작성하는 메서드
		String title = "소나기";
		//Predicate<String> equalsToTitle = s -> title.equals(s);
		Predicate<String> equalsToTitle = title::equals;
		System.out.println(equalsToTitle.test("소나기"));
		System.out.println(equalsToTitle.test("장마"));
		System.out.println("------------------------------------");
		
		//4. 생성자메서드 참조 - 테스트를 위해 클래스 하나 생성함(Person)
		//Supplier<Student> spNew = () -> new Person();
		Supplier<Student> stContr1 = Student::new;
		Student s1 = stContr1.get();
		System.out.println("s1 = "+s1);
		
		//BiFunction<String, Integer, Student> stContr2 = (stdName, stdAge) -> new Student(stdName, stdAge);
		BiFunction<String, Integer, Student> stContr2 = Student::new;//함수형인터페이스의 시그니처에 따라 작동
		Student s2 = stContr2.apply("김윤수",29);
		System.out.println("s2 = "+s2);
		
		//@문제 Student객체에서 nonNull이 붙은 생성자를 호출해서 메서드 레퍼런스로 구현하라
		Function<String, Student> stContr3 = Student::new;
		Student s3 = stContr3.apply("기임윤수");
		System.out.println("s3 = "+s3);
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@RequiredArgsConstructor //NonNull 어노테이션이 붙은 필드만 가지고 생성자를 만들어줌
	class Student{
		@NonNull
		private String name;
		private int age;
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
			Set<Integer> set = new TreeSet<>();
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