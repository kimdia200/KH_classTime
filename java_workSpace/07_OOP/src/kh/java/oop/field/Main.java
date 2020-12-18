package kh.java.oop.field;

public class Main {

	public static void main(String[] args) {
		
		//객체1
		GalaxyNote20 ph1 = new GalaxyNote20();
		ph1.setOwner("김윤수");
		ph1.setPhoneNumber("010 1234 5678");
		System.out.println(ph1.getOwner() + " : " + ph1.getPhoneNumber());
		System.out.println(GalaxyNote20.WIDTH);

		//객체2
		GalaxyNote20 ph2 = new GalaxyNote20();
		ph2.setOwner("홍길동");
		ph2.setPhoneNumber("010 2222 3333");
		System.out.println(ph2.getOwner() + " : " + ph2.getPhoneNumber());
		System.out.println(GalaxyNote20.HEIGHT);
		
	}

}
