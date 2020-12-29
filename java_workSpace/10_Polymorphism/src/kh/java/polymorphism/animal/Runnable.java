package kh.java.polymorphism.animal;
/**
 * 컴파일된 결과는 Runnable.class로 동일하다.
 * @author kimYS
 *
 */
public interface Runnable {
	//상수 public static final이 자동으로 추가됨
	int LEGS = 4;
	
	//추상메서드
	void run();
}
