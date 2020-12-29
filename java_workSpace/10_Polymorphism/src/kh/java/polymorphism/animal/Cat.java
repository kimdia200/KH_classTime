package kh.java.polymorphism.animal;

public class Cat extends Animal implements Runnable, Bitable{
	@Override
	public void say() {
		System.out.println("안녕하세요, 저는 고양이 입니다.");
	}
	@Override
	public void attack() {
		punch();
	}
	public void punch() {
		System.out.println("냥냥 펀치!");
	}
	@Override
	public void run() {
		System.out.println("미스 묘의 사뿐 사뿐");
	}
	@Override
	public void bite(String sound) {
		System.out.println("미스묘가 뭅니다 "+ sound);
	}
}
