package kh.java.api.string;

import java.util.StringTokenizer;

public class StringTest {

	public static void main(String[] args) {
			StringTest st = new StringTest();
			
			st.test3();
	}
	/**
	 * String은 immutable(변경불가)
	 */
	public void test1() {
		String s1 = "java";
		System.out.println(s1);
		System.out.println(s1.hashCode());
		
		s1 += " oracle";
		System.out.println(s1);
		System.out.println(s1.hashCode());
	}
	/**
	 * StringBuilder mutable(변경가능) 싱글쓰레드용
	 * StringBuffer mutable(변경가능) 멀티쓰레드용 동기화를 지원
	 */
	public void test2() {
		StringBuilder sb = new StringBuilder();
		System.out.println(sb.hashCode());
		System.out.println(sb);
		
		sb.append(" oracle");
		System.out.println(sb.hashCode());
		System.out.println(sb);
	}
	
	/**
	 * csv 데이터 처리하기
	 * Comma Seperated Value
	 * 콤마로 구분된 값들
	 * java, oracle, html, javascript, css
	 * 
	 * 구분자는 실제 데이터에 사용되지 않는 어떠한 문자도 좋다.
	 * 1. String의 split
	 * 2. StringTokenizer
	 * 
	 * 
	 */
	public void test3() {
		String data = "java§oracle§html§spring§maven§kh/java/html";
		
		//1.split 사용 재사용 가능!
		String[] arr = data.split("§");
		for(String s : arr) {
			System.out.print("["+s+"] ");
		}
		System.out.println();
		
		//2.StringTokenizer 사용
		StringTokenizer tokenizer = new StringTokenizer(data,"§");
		//이객체를 만드는 순간 이데이터에 대한 처리가 끝났음
		while(tokenizer.hasMoreTokens()) {//다음 토큰이 남아있으면 true, 아니면 false
			System.out.print("가지고있는 토큰의 갯수 : "+tokenizer.countTokens());
			String s = tokenizer.nextToken();
			System.out.println("<"+s+"> ");
		}
		System.out.println("메서드종료후 가진 토큰의 갯수 : "+tokenizer.countTokens());
		//tokenizer를 다쓰고와서는 재사용이 불가능하다
		while(tokenizer.hasMoreTokens()) {//다음 토큰이 남아있으면 true, 아니면 false
			String s = tokenizer.nextToken();
			System.out.print("<"+s+"> ");
		}
		
		//구분자 처리 다른점
		//1.StringTokenizer는 빈문자열은 요소로 취급하지 않는다.
		//2.String todeknizer 구분자문자열을 문자단위로 인식처리.
		//3.split도 정규표현식을 사용하면 문자단위로 처리
		data = "a,,b,,c,d";
		arr = data.split(",");
		for(String s : arr)
			System.out.println("["+s+"]"); //,,두개를 연속쓰면 그사이는 공백으로 인식해서 입력받음
		
		tokenizer = new StringTokenizer(data,",");
		while(tokenizer.hasMoreTokens()) {//" "공백 이 아닌 ""빈문자열은 처리해주지 않는다
			String s = tokenizer.nextToken();
			System.out.print("<"+s+"> ");
		}
		System.out.println("\n==================================");
		data="a, b,c, d";
		//정규표현식(regular expression)
		arr = data.split("[, ]"); //대괄호안에 입력하면 ,와 공백 두개를 구분자로사용
		for(String s : arr)
			System.out.println("["+s+"]");
		
		tokenizer = new StringTokenizer(data,", ");//,와 공백 두개의 구분자로 인식
		while(tokenizer.hasMoreTokens()) {
			String s = tokenizer.nextToken();
			System.out.print("<"+s+"> ");
		}
	}
}
