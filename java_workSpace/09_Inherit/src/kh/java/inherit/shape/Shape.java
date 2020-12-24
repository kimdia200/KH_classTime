package kh.java.inherit.shape;

public class Shape {
	private double area; //넓이
	
	public Shape() {
		super();
	}
	public Shape(double area) {
		super();
		this.area = area;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public void draw() {
		System.out.println("도형을 그리고있다.");
	}
	
	
}
