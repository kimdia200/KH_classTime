package kh.java.loop;

public class BreakContinueTest {
/**
 * break : 반복문 블럭을 즉시 탈출(중지)
 * 
 * continue : 이하 코드를 실행하지 않고, 반복문 시작으로 이동
 * 	-for 문 : 증감식으로 이동
 *  -while 문 : 조건식으로 이동!
 */
	public static void main(String[] args) {
		BreakContinueTest b = new BreakContinueTest();
		b.test4();
	}
	
	public void test1() {
		int i = 1;
		while(true) {
			System.out.println(i);
			if(i++ == 100) {
				break;
			}
		}
	}
	/**
	 * 11의 배수가 1000이 넘기전까지만 출력하세요
	 */
	public void test2() {
		final int NUM = 11;
		int i = 1;
		while(true) {
			if(NUM*i<1000)
				System.out.println(NUM*i+" ");
			else
				break;
			i++;
		}
	}
	
	/**
	 * continue
	 */
	public void test3() {
//		int i = 1;
//		while(i<=100) {
//			//짝수만 출력하고 1씩 증가시키기
//			i++;
//			if(i % 2 != 0)
//				continue; //홀수면 아래 sysout 까지 가지않고 바로 반복문 처음 조건식으로 돌아감	
//			System.out.println(i);
//		}
		
		for(int i = 1 ; i<=100; i++) {
			if(i%2 !=0)
				continue;
			System.out.println(i);
		}
	}
	/**
	 * @실습문제 : ascii code 범위에서 숫자와 영문자만 출력하기
	 * 			단, continue를 무조건 사용해서 코드를 전개하세요.
	 */
	public void test4() {
		char i=0;
		while(i<128) {
			if(i<'0'||(i>'9'&&i<'A')||(i>'Z'&&i<'a')||i>'z') {
				i++;
				continue;
			}
			System.out.print(i++ + " ");
		}
	}

}
