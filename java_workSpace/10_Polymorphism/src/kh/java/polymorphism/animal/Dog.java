package kh.java.polymorphism.animal;

public class Dog extends Animal implements Runnable,Bitable{

	@Override
	public void say() {
		System.out.println("안녕하세요, 저는 강아지입니다.");
	}
	@Override
	public void attack() {
		kick();
	}
	public void kick() {
		System.out.println("강아지 킥 날림!");
	}
		
	@Override
	public void run() {
		System.out.println("미스터 견의 성큼 성큼");
	}
	@Override
	public void bite(String sound) {
		System.out.println("미스터 견은 물어요~ "+ sound);
	}
}
