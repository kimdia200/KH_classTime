package kh.java.polymorphism.animal;

public class Snake extends Animal {

	@Override
	public void attack() {
		bite();
	}
	
	public void bite() {
		System.out.println("뱀이 콱~ 찍~!");
	}
}
