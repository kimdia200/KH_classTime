package kh.java.io.byte_.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * byte기반의 표준입출력
 * System.in : 사용자 키보드 입력
 * System.out : 콘솔출력
 * @author kimYS
 *
 */
public class SystemIOTest {
	public static void main(String[] args) {
		SystemIOTest s = new SystemIOTest();
//		s.test1();
		s.test2();
	}
	/**
	 * System.in
	 * 바이트 기반으로 읽는다는게 뭔지 확인해보자
	 * read() : int를 리턴하는 메서드(1byte는 256가지만 판별가능해서 int로 리턴 효율이 좋음)
	 * 			대상으로 부터 1byte를 읽어와서 int로 리턴하는 메서드
	 * 256가지(0~255) +1하면(-1 : 읽어올 값이 없는 경우)
	 * 
	 * //ctrl + z 를 입력하면 이클립스에서는 int(-1)을 리턴한다, 즉! 종료할수있다 
	 */
	public void test1() {
		System.out.println("키보드로 입력값을 주세요 : ");
		int data = 0; //읽어온 값을 처리할 변수
		
		//-1이면 읽어올 값이 없는거니까, 읽어올 값이 없을때까지 반복해라
		try {
			while((data = System.in.read())!=-1) {
				System.out.println("input : "+data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 2004년 JDK 1.5 버전이 나오기전
	 * Scanner 클래스가 등장전의 사용자 입력값 처리는 다음과 같다
	 */
	public void test2() {
		//보조스트림  : 기본스트림에 연결하여 사용한다, 여러개를 연결할 수 있다.
		//마지막 연결된 보조 스트림으로 입출력 제어 및 자원 반납을 한다.
		
//		BufferedReader br = new BufferedReader(System.in);
		//보조스트림 BufferedReader에 기본스트림 System.in을 연결했지만 사용 불가
		//왜?? 바이트기반, 문자열 기반 차이가 있기때문(메서드 정의 안됨)
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//System.in(기본스트림) -> InputStreamReader 브릿지스트림(보조) -> BufferReader 보조스트림
		String data = "";
		System.out.println("키보드로 입력하세요 : ");
		try {
			int sum = 0;
			while((data = br.readLine())!= null) {
//				System.out.println(data);
				sum+=Integer.parseInt(data);//받은 값을 숫자로 저장해서 누계
				System.out.println(sum);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//자원반납 밑에꺼 할거같지만 하면안된다!
//			try {
//				//닫다가 오류났을때 멈추면 안되니까...
//				br.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			//왜?????
			//표준 입출력을 사용하는 스트림클래스는 반납하면 안된다.
			//한번 닫힌 표준입출력은 프로그램이 종료될때까지 재사용 할 수 없기 때문에...
			
		}
	}
}
