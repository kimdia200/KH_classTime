package kh.java.format;

public class PrintfTest {

	public static void main(String[] args) {
		PrintfTest p = new PrintfTest();
		p.test1();
		
	}

	public void test1() {
		boolean bool = true;
		char ch = '헐';
		String s = "안녕하세요";
		int i = 100;
		double dnum = 0.12345678;
		
		System.out.printf("%b이냐 %b이냐 %b로다!!!!^%n",bool,!bool,bool);
		
		System.out.printf("%c, %s%n",ch,s);
		
		//%.2f : 소수점 이하 둘째자리까지 반올림 처리
		System.out.printf("i = %d, dnum = %.2f%n",i, dnum);
		
		//%10s : 10개의 공간을 확보하고, 요소를 우측(좌측)정렬
		System.out.printf("[%10s][%-10s]%n","abc","abc");
		System.out.printf("[%10s][%-10s]%n","안녕","안녕");
		
		
		//%s는 거의 만능;
		System.out.printf("%s %s %s %s %.5s%n",
				bool
				,ch
				,s
				,i
				,dnum);
		
		
	}
}
