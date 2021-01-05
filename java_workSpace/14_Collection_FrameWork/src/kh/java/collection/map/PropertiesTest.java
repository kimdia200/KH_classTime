package kh.java.collection.map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Properties K,V 타입을 String, String으로 제한한 map 이 옛날게 왜 아직도 쓰이는가?!?!?! K-String,
 * V-String이 정보를 표현하는데 최적화 되어있기때문 Map<String,String> 이라고 해도되지만 더
 * 간단해서???(String명시안함) + 추가 메서드 지원 어쨋든 두개다 String일때는 이거 쓴대
 * 
 * +++ 한줄에 한쌍의 값만 저장함
 * 
 * - 파일 입출력메소드를 지원 - 설정 정보 표현에 최적의 표현
 * 
 * 그래서 옛날꺼지만 여전히 많이 쓰고있다.
 */
public class PropertiesTest {

	public static void main(String[] args) {
		PropertiesTest p = new PropertiesTest();
//		p.test1();
		p.test2();
	}

	/**
	 * 출력
	 */
	public void test1() {
		Properties prop = new Properties();
		prop.setProperty("url", "http://localhost:9090/kh-java");
		prop.setProperty("userName", "kimyunsu");
		prop.setProperty("passWord", "1234!@#$");

		System.out.println(prop);

		// 파일에 저장
		// .properties 라는 확장자 많이사용
		// .xml 도 많이사용
		try {
			//원하는 형식으로 저장
//			prop.store(new FileWriter("userInfo.properties"), "userInfo.properties");
			//xml로 저장할때(xml은 바이트단위이기 때문에 FileOutputStream사용)
			prop.storeToXML(new FileOutputStream("userInfo.xml"), "주석");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 입력
	 */
	public void test2() {
		Properties prop = new Properties();
		
		try {
			//읽어오기
//			prop.load(new FileReader("userInfo.properties"));
			//xml파일 읽어오기
			prop.loadFromXML(new FileInputStream("userInfo.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(prop);
		
		//1.요소 가져오기
		String url = prop.getProperty("url");
		String userName = prop.getProperty("userName");
		String passWord = prop.getProperty("passWord");
		
		System.out.println("========== key값으로 접근 ==========");
		System.out.println(url);
		System.out.println(userName);
		System.out.println(passWord);
		
		//2.전체 열람 메소드
		//Enumeration타입
		System.out.println("========== Enumeration<> 으로 접근 ==========");
		Enumeration<?> en = prop.propertyNames();
		while(en.hasMoreElements()) {
			String name = (String)en.nextElement();
			String value = prop.getProperty(name);
			System.out.println(name+" = "+value);
		}
	}
}
