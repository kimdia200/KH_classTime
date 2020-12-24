package kh.java.inherit.product.run;

import kh.java.inherit.product.model.vo.Desktop;
import kh.java.inherit.product.model.vo.SmartPhone;
import kh.java.inherit.product.model.vo.Tv;

public class ProductMain {

	public static void main(String[] args) {

		Desktop d = new Desktop("삼성","삼성대땅",1_000_000,
				"windows10","UHD_Curved","기계식 키보드");
		
		System.out.println(d.getDesktopInfo());
		
		SmartPhone s = new SmartPhone("애플","아이폰12",500_000,
										"IOS14","KT");
		System.out.println(s.getSmartPhoneInfo());
		
		Tv tv = new Tv("LG","LG 롤러블 TV",1_000_000_000,100,30);
		System.out.println(tv.getTvInfo());
	}
}
