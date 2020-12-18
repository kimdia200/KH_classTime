package kh.java.oop.init.block;
/**
 * 
 * 필드 값대입 순서
 * 
 * 1. 명시한 값이 없다면, type별 기본값이 대입됨
 * 2. 명시한 값이 존재하면 그값으로 대입
 * 3. 초기화블럭에서 대입한 값
 * 4. 생성자에서 대입한 값
 * 
 * 
 * 3번(클래스 초기화블럭, 멤버 초기화 블럭), 4번이 공존 할때 
 * 실행순서 : 클래스(static)초기화 블럭, 멤버 초기화 블럭, 생성자 순으로 실행됨
 */
public class Sample {
	
	private int num=999999;
	private static String s="필드에 쓴값";
	
	//클래스변수용 초기화 블럭
	static{
		System.out.println("기존 s = " + s);
		System.out.println("---static 초기화 블럭 시작 --- ");
		
		
		s=" 안녕, 나 static 이야";
		System.out.println("s = "+s+" 로 변경");
		
		System.out.println("---static 초기화 블럭 끝 ---");
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	
	//멤버변수용 초기화 블럭작성
	{	
		System.out.println("기존 num = " + num + "       \\\\필드에 쓴값");
		System.out.println("--- 초기화 블럭 시작 ---");
		
		num = 78;
		System.out.println("num = " + num + " 으로 변경");
		
		System.out.println("--- 초기화 블럭   끝  ---");
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	//생성자 : 메소드변형
	//객체 생성시 호출되는 메소드
	public Sample() {
		
		System.out.println("기존 num = " + num);
		System.out.println("기존 s = " + s);
		
		System.out.println("--- 생성자 시작 ---");
		
		num = 99;
		s = "잘가";
		System.out.println("num = " + num);
		System.out.println("s = " + s);
		
		System.out.println("--- 생성자 끝 ---");
	}
	
	public int getNum() {
		return num;
	}
	public String getS() {
		return s;
	}
	
}
