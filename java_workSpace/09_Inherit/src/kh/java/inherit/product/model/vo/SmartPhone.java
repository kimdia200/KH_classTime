package kh.java.inherit.product.model.vo;

public class SmartPhone extends Product {
	
	private String os;
	private String carrier;
	
	public SmartPhone() {
	}
	public SmartPhone(String brand, String productName, int price,
					String os, String carrier) {
		super(brand,productName,price);
		this.os = os;
		this.carrier = carrier;
	}
	public String getSmartPhoneInfo() {
		return getProductInfo() + ", "+os+", "+carrier;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
}
