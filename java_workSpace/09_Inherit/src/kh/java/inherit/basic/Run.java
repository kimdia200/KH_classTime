package kh.java.inherit.basic;

public class Run {
	
	public static void main(String[] args) {
		Parent p = new Parent();
		p.name = "아부지";
		p.age = 50;
		p.printInfo();
		p.say();
		
		System.out.println("================================");
		Child1 ch1 = new Child1();
		ch1.name = "홍길동";
		ch1.age = 30;
		ch1.printInfo();
		ch1.say();
		ch1.game();
		System.out.println("================================");
		Child2 ch2 = new Child2();
		ch2.name = "마이콜";
		ch2.age =18;
		ch2.printInfo();
		ch2.say();
		ch2.listenToMusic();
		
		System.out.println("================================");
		GrandChild gc = new GrandChild();
		gc.name = "둘리";
		gc.age = 20;
		gc.printInfo();
		gc.say();
		
	}
	
}
