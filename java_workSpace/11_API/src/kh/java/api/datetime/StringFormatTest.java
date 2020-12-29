package kh.java.api.datetime;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 날짜 : java.text.SimpleDateFormat
 * 숫자  : java.text.DecimalFormat
 * @author kimYS
 *
 */
public class StringFormatTest {

	public static void main(String[] args) {
		StringFormatTest s = new StringFormatTest();
		s.test2();
	}
	
	/**
	 * simpleDateFormat
	 */
	public void test1() {
		Date d = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd (E) HH:mm:ss");
		
		String result = f.format(d);
		System.out.println(result);
	}
	
	/**
	 * DecimalFormat
	 * 
	 * # 해당자리에 데이터가 없는경우, 생략
	 * 0 해당자리에 데이터가 없어도 0으로 표시
	 */
	public void test2() {
		double num = 123456790.12345;
		DecimalFormat df = new DecimalFormat("＄#,###");
		System.out.println(df.format(num));
		
		df.applyPattern("￦#,###.###"); //applyPattern 은 패턴을 바꾸겠다는것, 매번 새로생성하지 않아도됨
						//이거 역슬래스 아니고 특수문자임!
		System.out.println(df.format(num));
	}
}
