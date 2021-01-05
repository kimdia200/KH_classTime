package kh.java.collection.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Collection - set - HashSet 순서로 되어있음
 * 가장 중요한 특징! 중복을 허용하지 않는다
 * 저장된 순서를 보장하지 않는다
 * 
 * 그러나 LinkedHashSet은 중복을 허용하지 않는데도 저장된 순서를 유지해줌
 * TreeSet은 오름차순 정렬까지 지원해줌(중복x, 순서유지o, 오름차순o)
 *
 */
public class HashSetTest {
	public static void main(String[] args) {
		HashSetTest h = new HashSetTest();
//		h.test1();
//		h.test2();
//		h.test3();
//		h.test4();
		h.test5();
	}

	private void test1() {
		HashSet<Integer> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		Collection<Double> set3 = new HashSet();
		
		//요소추가
		set2.add("안녕");
//		set2.add(123); 제네릭 덕분에 다른 타입의 요소는 추가가 불가능하다~!
		set2.add("Hello World");
		set2.add("ㅋㅋㅋ");
		set2.add("ㅋㅋㅋ");//중복된 값을 넣어보자
		
		//내용확인 (랜덤으로 배치)
		System.out.println(set2);
		
		//저장된 요소 개수 확인
		System.out.println("저장된 갯수 : "+set2.size());
		
		//인덱스를 통한 요소가져오기는 지원하지 않는다.
//		set2.get(2); 이런건 없다
		
		//반복문을 통해서 전체 열람만 가능하다
		//1.일반 for문은 index로 접근하기 때문에 사용할 수 없다.
		//2.for-Each문
		for(String s : set2) {
			System.out.println(s);
		}
		//3.Iterator
		Iterator<String> iterator = set2.iterator();
		while(iterator.hasNext()) {
			String s = iterator.next();
			System.out.println(s);
		}
		
		//삭제 : 동등한 객체를 찾아 삭제(equals & hashCode override되어있어야함)
		set2.remove("ㅋㅋㅋ");
		System.out.println(set2);
		
		//전체삭제
		set2.clear();
		
		//비어있는지 검사 , 존재하면 false, 비어있으면 true
		System.out.println(set2.isEmpty());
		
	}
	/**
	 * List -> Set : 중복제거
	 * 
	 * 
	 * Set -> List : 정렬을 해주기 위해
	 */
	public void test2() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list);
		
		//set으로 변환(제네릭 동일)
		Set<Integer> set = new HashSet<Integer>(list);
		System.out.println(set);
		
		//list변환 : 내림차순 정렬까지
		list=new ArrayList<Integer>(set);
		list.sort(Collections.reverseOrder()); //이거 몰랐다 메모
//		Collections.sort(list, Collections.reverseOrder()); //위에랑 똑같음
		System.out.println(list);
	}
	
	/**
	 * 커스텀 클래스 중복처리
	 */
	public void test3() {
		Set<Person> set = new HashSet<Person>();
		set.add(new Person("홍길동"));
		set.add(new Person("신사임당"));
		set.add(new Person("홍길동"));
		
		System.out.println(set);
	}
	
	/**
	 * LinkedHashSet
	 * TreeSet
	 */
	public void test4() {
		Set<Integer> set1 = new LinkedHashSet<Integer>();
		set1.add(34);
		set1.add(25);
		set1.add(100);
		set1.add(1);
		System.out.println(set1); //저장순서 유지(인덱스접근은 아님)
		
		Set<Integer> set2 = new TreeSet<Integer>(set1);
		System.out.println(set2); //오름차순 정렬 지원
	}
	/**
	 * 로또숫자 뽑기
	 * 1~45사이에 중복없는 난수 6개
	 */
	public void test5() {
		Set<Integer> set = new TreeSet<Integer>();
		while(set.size()<6) {
			int ran = (int)(Math.random()*45)+1;
			set.add(ran);
		}
		System.out.println(set);
	}
}
