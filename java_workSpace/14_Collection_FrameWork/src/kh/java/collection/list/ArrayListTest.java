package kh.java.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {
	public static void main(String[] args) {
		ArrayListTest a = new ArrayListTest();
//		a.test0();
//		a.test1();
//		a.test2();
//		a.test3();
//		a.test4();
//		a.test5();
		a.test6();
		
	}
	public void test0() {
		
		Student[] arr = new Student[3];
		arr[0] = new Student(1,"홍길동");
		arr[1] = new Student(2,"신사임당");
		arr[2] = new Student(3,"세종대왕");
		
		//1. 학생2명 추가 = 배열크기가 3으로 제한 되어있으므로 다시 할당 해줘야한다
		Student[] arr2 = new Student[10]; //아예 넉넉하게 만들어줌 = 메모리낭비됨
		System.arraycopy(arr, 0, arr2, 0, arr.length);
		arr2[3] = new Student(4, "장영실");
		arr2[4] = new Student(5,"이황");
		
		//2. 중간의 학생 1명 삭제
		//데이터는 중간의 빈자리 없이 관리 해야한다.
		arr2[2] = null;//세종대왕 전학감
		arr2[2] = arr2[3];
		arr2[3] = arr2[4];
		arr2[4] = null;
		
		//3. 중간에 학생 1명 추가 : new Student(6, "뺑덕어멈");
		arr2[4] = arr2[3];
		arr2[3] = arr2[2];
		arr2[2] = arr2[1];
		arr2[1] = arr2[0];
		arr2[0] = new Student(6, "뺑덕어멈");
		
		System.out.println(Arrays.toString(arr2));
		
		
	}
	/**
	 * java.util.List 인터페이스
	 * java.util.ArrayList 구현클래스
	 */
	public void test1() {
		ArrayList list1 = new ArrayList();
		List list2 = new ArrayList();
		Collection list3 = new ArrayList(); //다형성의 예
		
		//맨 마지막에 요소추가 add
		list1.add("안녕");
		list1.add(123);
		list1.add(123.456);
		list1.add(true);
		list1.add(new Date());
		list1.add(new Student(1,"고주몽"));
		
		//toString override되어 있음.
		//저장된 순서유지, 중복 허용 을 확인할수 있음
		System.out.println(list1);
		
		//저장된 요소의 개수
		System.out.println("list1의 크기 : "+list1.size());
		
		//삭제 remove
		list1.remove(3);
		
		//중간에 요소추가
		list1.add(3,false);
		
		//반복문을 통해 요소에 접근가능 : 인덱스 사용가능
		for(int i=0; i<list1.size(); i++) {
			System.out.println(i+" : " + list1.get(i));
		}
	}
	
	/**
	 * Generics : 타입제한  <type parameter>
	 * 기본형을 사용 할 수 없다. WrapperClass사용 해야함
	 */
	public void test2() {
		List list1 = new ArrayList();		
		list1.add("안녕");
		list1.add(123);
		Object e1 = list1.get(0);
		Object e2 = list1.get(1); //요소는 기본적으로 Object타입
								  //다형성에 의해 가능한것
		System.out.println(((String)e1).length()); //형변환 해야하는 불편함
		
		//다형성 적용
		//<String> 타입변수 지정 : 요소의 타입제한
		List<String> list2 = new ArrayList<String>();
		list2.add("안녕");
//		list2.add(123); // String이 아닌 요소는 추가할 수 없다.
		String s1 = list2.get(0); //따로 다운캐스팅 할 필요 없이 String 으로 받아옴
		System.out.println(s1.length()); //바로 가능 
		
		//Wrapper class
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(31); //31 -> new Integer(31) : Auto-Boxing
		int num = list3.get(0); //new Integer(31) -> 31 : Auto-unBoxing
		
	}
	
	/**
	 * 실습해보깅
	 */
	public void test3() {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1,"홍길동"));
		list.add(new Student(2,"신사임당"));
		list.add(new Student(3,"세종대왕"));
		
		//1.학생 2명추가
		list.add(new Student(4,"장영실"));
		list.add(new Student(5,"이황"));
		
		//2. 중간의 학생 1명삭제(세종대왕)
//		list.remove(2);  //인덱스 삭제
		
		//동등비교삭제인데 equals 재정의 안되서 안됨
		//remove는 삭제에 성공했는지 실패했는데 boolean타입으로 리턴해줌
		System.out.println(list.remove(new Student(3,"세종대왕")));
		
		//equals, hashCode관련 부분(룰)
		System.out.println(new Student(3, "세종대왕").hashCode());
		System.out.println(new Student(3, "세종대왕").hashCode());		
		
		//3.맨앞에 학생 1명 추가 : new Student(6, "뺑덕어멈")
		list.add(0, new Student(6, "뺑덕어멈"));
		
		//4.출력해보기
		for(Student s : list)
			System.out.println(s);
	}
	
	public void test4() {
		//객체 할당부의 제네릭 타입은 생략이 가능(jdk1.7이상)
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(2);
		list.add(3);
		list.add(1);
		list.add(4);
		list.add(1,100); //(int - index)1번지에 (Integer)100 요소추가
		System.out.println(list);
		
		//삭제메서드
		//1.index로 삭제
		list.remove(2); //2번 인덱스 값 삭제
		System.out.println(list);
		
		//2.동등객체로 삭제 : 객체가 가진 값이 동일한 객채, equals가 true가 되는 객체
		list.remove(new Integer(3)); //숫자 3을 찾아서 삭제
		System.out.println(list);
		
		//3. 수정 : 해당인덱스의 객체를 제공된 매개인자로 대체
		list.set(1, 888);
		System.out.println(list);
		
		//다른 Collection 객체 추가
		List<Integer> another = new ArrayList<Integer>();
		another.add(7);
		another.add(8);
		another.add(9);
		list.addAll(another);
		System.out.println(list);
		
		//매개인자 요소를 포함하고 있는가
		System.out.println("Integer(9)가 있는가? = "+list.contains(9));
		
		//매개인자 요소가 몇번지에 있는가
		System.out.println("Integer(9)는 몇번인덱스? = "+list.indexOf(9)); //존재하는값
		System.out.println(list.indexOf(9000));//존재하지 않는값은 -1리턴
		
		//열람 : 기존 for문, forEach문, iterator방식
		Iterator<Integer> iter = list.iterator();
		System.out.println("이터레이터 작동");
		while(iter.hasNext()) {
			Integer i = iter.next();
			System.out.print(i+" ");
		}
		System.out.println();
		
		//모든요소 삭제
		list.clear();
		
		//리스트가 비어있는가?
		System.out.println(list.isEmpty());
		
		System.out.println(list);
		
	}
	
	/**
	 * 정렬
	 * 1.Comparable 인터페이스 : 
	 * 기본정렬(한가지)클래스에서 구현. compareTo메소드 재정의
	 * 
	 * 
	 * 2.Comparator 인터페이스 :
	 * 기본정렬외에 추가적으로 정렬 기준을 제시 해야할때
	 * 별도의 Comparator 구현 클래스를 작성해야함(갯수 제한 없음)
	 * 내부적으로는 compare메서드 재정의
	 */
	public void test5() {
		//List, set, map중에 정렬은 List만 가능함(나머지는 정렬에 의미가없음)
		List<String> list = new ArrayList<String>();
		list.add("나나나~");
		list.add("가버려~");
		list.add("허허허");
		list.add("다나가~");
		
		Collections.sort(list); //기본정렬기준(사전등재순)에 따라정렬
		
		Comparator<String> comp = Collections.reverseOrder();
		Collections.sort(list,comp); //역순정렬
		
		System.out.println(list);
		
	}
	
	/**
	 * 커스텀 클래스 정렬하기
	 * 
	 */
	public void test6() {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(4,"장영실"));
		list.add(new Student(5,"이황"));
		list.add(new Student(2,"신사임당"));
		list.add(new Student(3,"세종대왕"));
		list.add(new Student(1,"홍길동"));
		
		//기본정렬 : 학번 오름차순
//		Collections.sort(list); //Student에 compareTo 재정의해줘서 가능한것
		list.sort(null);//기본정렬인 경우 Comparator객체가 필요치 않다.
		//두개 같음
		
		//추가적인 정렬기준 생성
		//1. 이름 오름차순  (클래스 새로 만들어줬음)
		Comparator<Student> comp = new StudentNameAscending();
//		list.sort(comp);
		Collections.sort(list,comp);
		System.out.println(list);
		
		//2. 이름 내림차순
		Comparator<Student> comp2 = new StudentNameAscending().reversed();
		Collections.sort(list,comp2);
		list.sort(comp2);
		System.out.println(list);
		
		
	}
}
