package kh.java.inherit.shape;
/**
 * 
 * Circle has a Point : has a 포함관계(일반화 관계)
 * Circle ─────────> Point 
 * 
 * Circle is a Shape :  is a 상속관계(일반화 관계)
 *  
 *
 */
public class Circle extends Shape {

	private Point center;
	private int r;
	
	
	
	public Circle() {
		super();
		//파라미터 없이 생성자가 실행되면 기본값으로 세팅해준다
		this.center = new Point(100,100);
		this.r = 100;
	}
	public Circle(Point center, int r) {
		super();
		this.center = center;
		this.r = r;
	}
	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	
	/**
	 * override 상속받은 메소드를 고쳐쓸수 있다.
	 * 1. 메소드명, 파라미터 선언부, 리턴타입 모두 동일해야 한다.
	 * 도우미 = @override annotation !!!!!
	 * 
	 * 2. 접근제한자는 더 넓은 범위로 수정이 가능하다
	 * 3. 메소드가 던지는 예외클래스는 더 줄이거나, 자식클래스로 변경 가능하다 
	 */
	
	@Override
	public void draw() {
		System.out.println("중심점이"+center+"이고, "+
						"반지름이 "+r+"인 원을 그리고 있다");
	}
	
	
	
}
