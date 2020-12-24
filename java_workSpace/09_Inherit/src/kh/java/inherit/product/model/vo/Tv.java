package kh.java.inherit.product.model.vo;

public class Tv extends Product{
	private int size;
	private int ch;
	public Tv() {
		super();
	}
	public Tv(String brand, String productName, int price, int size, int ch) {
		super(brand, productName, price);
		this.size =size;
		this.ch = ch;
	}
	
	public String getTvInfo() {
		return super.getProductInfo() + ", "+size+", "+ch;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCh() {
		return ch;
	}
	public void setCh(int ch) {
		this.ch = ch;
	}
}
