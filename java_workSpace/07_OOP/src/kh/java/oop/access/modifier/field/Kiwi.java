package kh.java.oop.access.modifier.field;
/**
 * 접근제한자 Access Modifier
 * 
 * 1.public 다른 패키지, 다른 클래스,  어디서든 접근 가능
 * 2.protected 같은패키지, 다른패키지이지만 상속관계일경우
 * 3.(default) 같은 패키지 내에서만
 * 4.private 같은 클래스 내에서만 접근 가능
 * 
 * class의 접근제한자
 * 1.public 
 * 2.(default)
 * 
 *
 */
public class Kiwi {
	
	public int publicNum = 100;
	protected int protectedNum = 90;
	int defaultNum = 80;
	private int privateNum = 70;
}
