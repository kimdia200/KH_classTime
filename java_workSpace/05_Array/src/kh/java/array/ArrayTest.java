package kh.java.array;

import java.util.Arrays;

public class ArrayTest {

	public static void main(String[] args) {

		ArrayTest a = new ArrayTest();
		a.test7();
		
	}
	
	
	/**
	 * 배열 : 동일한 자료형의 값이 여러개인 경우
	 */
	public void test0() {
		//동일한 자료형을 가진 여러 값
		int kor = 80;
		int math = 70;
		int eng = 90;
		int society = 75;
		int science = 45;
		
		//총점, 평균
		int total = kor + math + eng + society + science;
		double avg = (double)total / 5;
		
		System.out.println("총점 : " +total + "점, 평균 : " + avg + "점");

	}
	public void test1() {
		//1. 배열 변수 선언
		int[] subject;
		
		//이건 선언 + 할당 + 인덱스 값대입 all in one
//		int[] subject = {80,70,90,75,45};
				
		//2. 배열 할당
		subject= new int[5];
				
		//배열 초기값 확인해보자(각타입별 초기값으로 미리 초기화 되어있음을 확인할수있음)
		//값을 대입하지 않고서도 사용이 가능하다
		//int = 0; double =0.0; char=  ''; boolean = false;
		//char(0) = 공백, boolean(0)=false 이기때문
		//기본형이 아닌 참조형의 변수배열의 경우 기본값이 null(값없음)으로 처리된다.
		System.out.println(subject[0]);
		System.out.println(subject[1]);
		System.out.println(subject[2]);
		System.out.println(subject[3]);
		System.out.println(subject[4]);
		System.out.println("-------------------------");
		
		//3.배열 각번지에 값 대입 (각 4바이트씩 공간차지 4*5=20)
		subject[0] = 80;
		subject[1] = 70;
		subject[2] = 90;
		subject[3] = 75;
		subject[4] = 45;
		
		//값대입 됬는지 확인
		System.out.println(subject[0]);
		System.out.println(subject[1]);
		System.out.println(subject[2]);
		System.out.println(subject[3]);
		System.out.println(subject[4]);
		System.out.println("-------------------------");
				
		//4. 총점, 평균
//		int total =  subject[0]+subject[1]+subject[2]+subject[3]+subject[4];
		//반복문을 이용해 점수배열의 총점구하기
		int total=0;
		//의미를 찾기 힘든 magic number 대신 변수, 상수를 사용하자
		//아래에선 .length 를 사용해줬음
		for(int i =0;i<subject.length;i++) {
			total += subject[i];
		}
		double avg = (double)total / subject.length;
		System.out.println("배열길이 = "+subject.length);
		System.out.println("총점 : " + total + ", 평균 : " + avg + "점");
		
	}
	
	/**
	 * double 배열
	 */
	
	public void test2() {
		//배열 선언
		double[] arr;
		//배열 할당 : heap영역에 배열객체를 생성하고, 주소값을 arr에 대입
		arr = new double[3];
		
		//선언과 동시에 할당을 한줄로 해도 상관없다
		//double[] arr = new double[3];
		
		//배열 요소(각 인덱스)에 값 대입
		arr[0] = 1.1;
		arr[1] = 2.2;
		arr[2] = 3.3;//배열의 마지막index : (arr.length - 1)
		//arr[3]은 없음 index는 0부터 시작하기때문	
	}
	
	/**
	 * 초기화
	 * 배열변수 선언 + 배열 할당(heap) + 인덱스별 값대입
	 */
	public void test3() {
		
		//이게 하나로 합친것!
		char[] chArr = {'a','b','c'};
		
		//이게 풀어서 쓴것
		//선언
//		char[] chArr;
		//할당
//		chArr = new char[3];
		//인덱스별 값대입
//		chArr[0]='a';
//		chArr[1]='b';
//		chArr[2]='c';
		
	}
	
	/**
	 * 배열의 여소에 담길 값에 규칙이 있다면, 반복문을 사용 할 수 있다.
	 * 1,2,3,4,~100
	 * a , b, c .....z
	 * 2,4,6,8....100
	 */
	public void test4() {
		
		//1,2,3,4,~100
		int[] arr = new int[100];
		//값대입
		for(int i = 0 ; i<arr.length;i++) {
			arr[i] = i+1;
		}
		//값출력
		for(int i = 0 ; i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}	
		System.out.println();
//		------------------------------------------
		//a , b, c .....z
		char[] arr2 = new char[26];
		//값대입
		for(int i = 0 ; i<arr2.length;i++) {
			arr2[i] = (char)(i+97);
		}
		//값출력
		for(int i = 0 ; i<arr2.length;i++) {
			System.out.print(arr2[i]+" ");
		}
		System.out.println();
//		------------------------------------------
		
		//2,4,6,8....100
		int[] arr3 = new int[50];
		//값대입
		for(int i = 0 ; i<arr3.length;i++) {
			arr3[i] = i*2+2;
		}
		//값출력
		for(int i = 0 ; i<arr3.length;i++) {
			System.out.print(arr3[i]+" ");
		}
	}
	
	public void test5() {
		//a , b, c .....z
		char[] arr = new char[26];
		//값대입 , 값출력(마지막 콤마 안나오게)
		for(int i = 0 ; i<arr.length;i++) {
			arr[i] = (char)(i+97);
			if(i==arr.length-1)
				System.out.print(arr[i]);
			else
				System.out.print(arr[i]+", ");
		}
	}
	/**
	 * String[]
	 */
	public void test6() {
		String[] nameArr = new String[3];
		nameArr[0] = "홍길동";
		nameArr[1] = "신사임당";
		nameArr[2] = "장영실";
		
		for(int i = 0; i<nameArr.length; i++) {
			System.out.println(nameArr[i]);
		}
	}
	
	/**
	 * 배열의 특징
	 * 1. 배열의 길이는 변경할 수 없다.
	 * 2. 
	 */
	public void test7() {
		int[] arr = new int[10];
		System.out.println(arr.length);
		//hashCode 비교 : 객체 주소값에 접근할 수 있는 key값(정확하게 말하면 주소값은 아님)
		System.out.println(arr.hashCode());
		//toString : 객체를 문자열 정보로 표현
		System.out.println(arr);
		System.out.println(arr.toString());
		
//		-------------------------------------------
		System.out.println();
		arr = new int[7];
		System.out.println(arr.length);
		System.out.println(arr.hashCode());		
		System.out.println(arr);
		System.out.println(arr.toString());
		System.out.println(Arrays.toString(arr));
		
		//참조형 변수의 기본값 null
		arr = null; //삭제
		
//		System.out.println(arr.length);//NullPoinException 일어남 (위에서 삭제했기때문)
	}
}