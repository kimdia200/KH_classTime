package kh.java.oop.method;

public class Sample {

	public static void main(String[] args) {
		int a=10;
		String b="Before";
		String[] c = {"Before"};
		
		Sample s = new Sample();
		
		s.test1(a);
		s.test2(b);
		s.test3(c);
		
		
		System.out.println("\n\n\n========최종값=========");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c[0]);
	}
	
	
	public void test1(int a) {
		a=20;
		System.out.println(a);
	}
	public void test2(String a) {
		a="Change";
		System.out.println(a);
	}
	public void test3(String[] a) {
		a[0]="Change";
		System.out.println(a[0]);
	}
}
