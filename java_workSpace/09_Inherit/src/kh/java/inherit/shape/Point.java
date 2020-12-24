package kh.java.inherit.shape;

public class Point {

	private int x; //가로축 좌표
	private int y; //세로축 좌표
	
	public Point() {
		super();
	}
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "("+x+", "+y+")";
	}
		
}
