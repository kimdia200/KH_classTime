package kh.java.inherit.product.model.vo;

public class Desktop extends Product{
	//부모클래스에 있는 필드값
//	private String brand;
//	private String productName;
//	private int price;
	private String os;
	private String monitor;
	private String keyboard;
	
	public Desktop() {
		
	}
	public Desktop(String brand, String productName, int price,
			String os, String monitor, String keyboard) {
		super(brand,productName, price);
		this.os = os;
		this.monitor = monitor;
		this.keyboard = keyboard;
	}
	
	public String getDesktopInfo() {
		return getBrand()+", "+getProductName() + ", "+getPrice()+", "+
				os+", "+monitor+", "+keyboard;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getMonitor() {
		return monitor;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	public String getKeyboard() {
		return keyboard;
	}
	public void setKeyboard(String keyboard) {
		this.keyboard = keyboard;
	}
}
