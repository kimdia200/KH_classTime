package com.kh.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class StreamStudy {
	public static void main(String[] args) {
		StreamStudy study = new StreamStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
//		study.test6();
//		study.test7();
//		study.test8();
		study.test9();
	}
	/**
	 * reduce 
	 * 스트림의 요소로 연산처리후 하나의 결과값을 리턴
	 * 
	 * BinaryOperator : 매개변수와 리턴값의 자료형이 같은 Function
	 * 
	 */
	
	public void test9() {
		int result = Arrays
			.asList(1,2,3,4,5,6,7,8,9,10)
			.stream()
			.reduce(0, (sum, n)->{
				System.out.println("sum = " + sum + ", n = "+n);
				return sum + n;
				//리턴값은 매개인자 첫번째 값으로 전달됨
				//  (0, (sum,n) -----> 초기값 0, 리턴값 sum에 지속저장, n = 새로운 파라미터
			});
		
		System.out.println("result = " + result);
		
		result =
			Arrays
				.asList(1,2,30,4,15,67,7,8,9,10)
				.stream()
				.reduce(0, (max, n) -> max > n ? max : n);
	
		System.out.println("max = " + result);
		
		List<Person> list= 
				Arrays.asList(
						new Person("홍길동", 35),
						new Person("신사임당", 40),
						new Person("세종", 45),
						new Person("홍난파", 80),				
						new Person("전달력", 69)			
					);
			Person maxAgePerson = list.stream()
									  .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2) // Optional<Person>
									  .get();
			System.out.println(maxAgePerson);
			
			Person person=list.stream()
							  .reduce(new Person(), (identity, p)->{
								  identity.age += p.age;
								  identity.name += p.name;
								  return identity;
							  });
			System.out.println(person);

	}
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	static class Person{
		private String name;
		private int age;
	}
	
	/**
	 * 구구단도 가능
	 */
	public void test8() {
		IntStream
				.range(2, 10)
				.forEach(dan->{
					IntStream
							.range(1, 10)
							.forEach(n-> System.out.println(dan+" * "+n+" = " + (dan*n)));
				});
	}
	
	/**
	 * 기본형 stream
	 * - IntStream
	 * - LongStream
	 * - DoubleStream
	 */
	public void test7() {
		
		int[] arr = {1,2,3};
		IntStream intStream = Arrays.stream(arr);
		
		DoubleStream doubleStream = DoubleStream.of(1.1,2.3,4.56);
		doubleStream.forEach(System.out::println);
		
		//range | rangeClosed
		IntStream
			.range(0, 10)
			.forEach(System.out::print);
		System.out.println();
		IntStream
			.rangeClosed(1, 10)
			.forEach(System.out::print);
		System.out.println();
		
		int sum = IntStream.rangeClosed(1, 10).sum();
		System.out.println("1~10까지 총합 : " + sum);
		
		double avg = IntStream.rangeClosed(1, 100).average().getAsDouble();
		System.out.println("1~10까지 평균 : " + avg);
		
		IntSummaryStatistics summary = 
				IntStream
					.of(32, 50, 80, 77, 100, 27, 88)
					.summaryStatistics();
			System.out.println(summary);
			System.out.println(summary.getAverage());
	}
	
	/**
	 * anyMatch()
	 * 
	 * noneMatch()
	 */
	public void test6() {
		//하나라도 true이면 true
		Boolean bool = Arrays
							.asList("1", "b2", "c", "d4", "5")
							.stream()
							.anyMatch(s->s.startsWith("a"));
		System.out.println(bool);
		
		bool = Arrays
					.asList("홍길동", "1", "가나다")
					.stream()
					.noneMatch(s->Pattern.matches("[0-9]", s));
		System.out.println(bool);
	}
	
	/**
	 * collect
	 */
	private void test5() {
		
		List<Integer> list = Arrays.asList(1,2,3,4,5,4,3,2,1);
		List<Double> result = list.stream()
								  .map(n->Math.pow(n,2))
								  .collect(Collectors.toList());
		System.out.println(result);
		
		Set<Integer> result2 = list.stream()
								   .filter(n->n%2==0)
								   .collect(Collectors.toSet());
		System.out.println(result2);
		
		Map<Integer, String> result3 = list.stream()
										   .distinct()//stream 에서 collect로 map을생성할때 중복키는 덮어씌어지는게 아니라 에러남
										   .collect(Collectors.toMap(n->n, n->n+""+n+""+n));
		System.out.println(result3);
	}
	
	/**
	 * stream의 처리과정
	 * 1. collection, array로부터 stream생성
	 * 2. 중간연산, Intermediate Operations : peek, filter, map.... (요소에 대한 중간작업을 하는것들)
	 * 3. 단말연산, Terminal Operations : forEach, collect
	 * !!!!중간연산, 단말연산 쉽게 구분하는법 : Stream을 리턴을 하는가 하지 않는가
	 * 
	 * 최종단말연산 전까지는 중간연산을 완료하지 않는 특징이있다.
	 * 
	 * peek
	 */
	private void test4() {
		Arrays
			.asList(1,2,3,4,5,6,7,8,9,10)
			.stream()
			.peek(n->System.out.println("peek : " + n))
			.filter(n->n%2==0)
			.peek(n->System.out.println("peek : " + n))
			.filter(n->n%4==0)
			.forEach(System.out::println);
//		p1 p2 p2 p3 p4 p4 4 p5 p6 p6 p7 p8 p8 8 p9 p10 p10
	}
	
	
	/**
	 * map
	 * a -> b
	 * 
	 * stream은 기본적으로 읽기전용을 생성한다.
	 */
	private void test3() {
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		list
			.stream()
			.map(n-> n*10)
			.forEach(System.out::println);
		
		List<Integer> another =list.stream()
									.map(n->n*10)
									.collect(Collectors.toList());
									//collect(Collector<T>)인데 Collectors.toList()로 정의된걸 가져다 쓴것 
		
		System.out.println("list = " + list);
		System.out.println("another = "+another);
		//원본값은 변하지 않은걸 확인 할 수 있다.
		
		Stream
			.of("홍길동", "신사임당", "세종") //String에 대한 stream
			.map(String::length) //Integer에 대한 Map형식 Stream
			.forEach(System.out::println);
		
		//@실습문제 요소의 공백을 모두 제거하고 List<String>으로 변환
		String[] wordArr = {"a b c d", "홍 길동", "hello world"};
		List<String> wordList = Arrays.stream(wordArr).map(str->str.replaceAll("[ ]", "")).collect(Collectors.toList());
		System.out.println("wordList"+wordList);
		
				
	}
	
	/**
	 * distinct
	 * filter(predicate <T>) predicate는 파라미터T를 사용해서 boolean값을 리턴함
	 */
	private void test2() {
		List<Integer> list = Arrays.asList(5,1,2,3,2,4,3,2,1,2,4,5);
		Stream<Integer> stream = list.stream();
		stream
			.distinct()//중복제거
			.filter(n->n%2!=0)
			.sorted()//정렬
			.forEach(System.out::println);
		
		//@실습문제 강씨 성을 가진 사람만 남겨보세요
		String[] names = {"강감찬", "강원래", "홍길동", "강형욱"};
		Stream<String> nameStream = Arrays.stream(names);
		nameStream
				.sorted()
				.filter(n->n.charAt(0)=='강')
				.forEach(System.out::println);
	}
	
	/**
	 * stream
	 * 배열이나 컬렉션을 일관되게 제어하려는 추상화 객체
	 */
	private void test1() {
		int[] arr = {3,1,4,5,2};
		
		//IntStream = int(기본형다루는 스트림)
		//Arrays.stream()은 배열을 스트림으로 변경해줌
		IntStream arrStream = Arrays.stream(arr);
		//void forEach(IntConsumer action);
		arrStream
				.sorted()//정렬
				//.forEach(n->System.out.println(n));
				.forEach(System.out::println);
		
		List<String> list = new ArrayList<>();
		list.add("홍현희");
		list.add("홍난파");
		list.add("홍진호");
		list.add("홍진경");
		list.add("홍진호");
		
		//Stream<T> 는 T참조형에 대한 스트림
		//컬렉션-stream을 메서드를 사용하면 스트림으로 변경해준다.
		Stream<String> stringStream = list.stream();
		//void forEach(Consumer<? super T> action);
		stringStream
					.sorted()
					.forEach(System.out::println);
		
		// public static<T> Stream<T> of(T... values) 
		Stream<Double> doubleStream = Stream.of(0.1, 1.2, 3.456);
	}
}
