package kh.java.operator;

import java.util.Scanner;

public class OperatorTest {

	public static void main(String[] args) {

		OperatorTest ot = new OperatorTest();
//		ot.test1();
//		ot.test1_1();
//		ot.test2();
//		ot.test2_1();
//		ot.test3();
		ot.test4();
		
		
	}
	
	/**
	 * 단항 연산자
	 */
	public void test1() {
		int a = 10;
		System.out.println(+a+", "+-a);
		
		boolean bool = a!= 10;
		System.out.println(!bool);
		
		System.out.println((long)Integer.MAX_VALUE +1 );
		
		//증감연산자 : ++ --
		//++a(전위 증감연산자)
		//먼저 1증감후, 인접연산에 사용
		//a++(후위 증감연산자)
		//인접연산에 먼저 사용된후, 1증감됨
		a++; // a=(a+1)
		a--; // a=a-1

		System.out.println("a = " + a);
		
//		System.out.println(++a +1);
		System.out.println(a++ +1);
		
		int k = 1;
		int m = ++k;
		System.out.println("k = " + k + ", m = " + m);
		
		int c = 1;
		int d = c++;
		System.out.println("i = " + c + ", o = " + d);
		
		
		int i = 3;
		int j = i++ + 10;
		System.out.println(" i = " + i +", j = " + j);
		
	}
	
	public void test1_1() {
		int x= 10;
		int y = 10;
		int z = ++x;
		System.out.println("x = " + x + ", y = "+ y + ", z = " + z);
		//x = 11, y = 10, z = 11
		
		
		z = y++;
		System.out.println("x = " + x + ", y = "+ y + ", z = " + z);
		//x = 11, y = 11, z = 10
		
		x = 20;
		z = x++ + --y;
		System.out.println("x = " + x + ", y = "+ y + ", z = " + z);
		//x = 21, y = 10, z = 30
		
		int m = 20;
		int n = m++ + m;
		System.out.println(" m = " + m + " n = " + n);
		//m = 21, n = 40
	}
	public void test2() {
		
		int a = 10;
		int b = 4;
		System.out.println(a+b);
		System.out.println(a-b);
		System.out.println(a*b);
		System.out.println(a/b);
		System.out.println(a%b);
		
		System.out.println(a + b * a / b -a % b);
		//a+(b*a/b) - (a%b)
		//18
		
		
		System.out.println(a > b); //크다 gt greater than
		System.out.println(a < b); //작다 lt less than
		System.out.println(a >= b); //크거나 같다 ge greater than equal
		System.out.println(a <= b); //작거나 같다 le less than equal 
		
	}

	/**
	 * 이항 연산자
	 * 논리 비교연산자 && ||
	 * 
	 * - && AND
	 * 		a && b : a가 참이면서 b가 참인가
	 * 		좌/우항 모두 참이면 참/ 아니면 거짓
	 * 
	 * - || OR
	 * 		a || b :a혹은 b가 참인가
	 * 		좌/우항 중 하나만 참이여도 참 / 아니면 거짓
	 */
	public void test2_1() {
		
		int i =3;
		System.out.println((i==0) && (i<10));
		
		System.out.println((i==0) || (i<10));
		
		
		int a = 1;
		int b = 1;
		System.out.println( a== 0 && ++b > 0);
		System.out.println("a = " + a + ", b = " + b);
		
		a = 1;
		b = 1;
		System.out.println(a > 0 || ++b > 0); // a>0 조건만족시 or 의 뒷부분은 실행되질 않아 ++b가 연산이 이뤄지지 않아서 b =1 
		System.out.println("a = " + a + ", b = " + b);
			
	}
	
	/**
	 * 삼항연산자
	 * (논리식) ? (true 일때 값) : (false)일때 값
	 */
	public void test3() {
		System.out.println(10>3 ? "ㅎㅎㅎㅎ" : "ㅋㅋㅋ");
		
		int a = 10;
		int b = 3;
		int c = a ==b ? a : b;
		System.out.println("c = " +c);
		
		
		//a가 b의 배수인가?
		String s = a%b == 0 ? "a는 b의 배수입니다" : "a는 b의 배수가 아닙니다.";
		System.out.println(s);
		
		//짝수 홀수 여부
		System.out.print("숫자를 입력해주세요 : ");
		Scanner sc = new Scanner(System.in);
		int d = sc.nextInt();
		System.out.println(d%2 == 0 ? "짝수" : "홀수");
	}
	
	/**
	 * 복합 대입연산자
	 * +=
	 * -=
	 * *=
	 * /=
	 * %=			 
	 */
	public void test4() {
		int n = 10;
//		n = n+5; //n공간에 n+ 5결과를 대입해라.
		n += 5;
		System.out.println("n += 5 = " + n);
		
//		n = n - 5;
		n -= 5;
		System.out.println("n -= 5 = " + n);
		
//		n = n * 5;
		n *= 5;
		System.out.println("n *= 5 = " + n);
		
//		n = n / 5;
		n /= 5;
		System.out.println("n /= 5 = " + n);
		
//		n = n % 5;
		n %= 5;
		System.out.println("n %= 5 = " + n);
		
	}
}
