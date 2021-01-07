package kh.java.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class NetworkTest {
	public static void main(String[] args) {
		NetworkTest n = new NetworkTest();
//		n.test1();
//		n.test2();
		n.test3();
	}
	
	/**
	 * 
	 * InetAddress
	 * Ip주소에 대한 정보를 가진 클래스
	 * 
	 * - ip : 255,255,255,255 1바이트씩 4개 4바이트로 이뤄진 실제 주소
	 * - hostname : naver.com, iei.or.kr
	 */
	public void test1() {
		try {
			//서버 ip주소 가져오세요
			System.out.println("네이버주소의 ip주소(서버)");
			InetAddress naver = InetAddress.getByName("naver.com");
			System.out.println(naver.getHostAddress());
			
			//모든 서버 ip주소를 가져오세요
			System.out.println("\n네이버주소의 모든 ip주소(서버)");
			InetAddress[] arr = InetAddress.getAllByName("naver.com");
			System.out.println("서버갯수 : "+arr.length);
			for(InetAddress ip : arr) {
				System.out.println(ip.getHostAddress());
			}
			
			//내컴퓨터의 ip주소
			System.out.println("\n내컴퓨터의 ip주소");
			System.out.println(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * URL
	 * hostname + hostname + port + 자원path
	 * 
	 * https://
	 * docs.oracle.com
	 * :443 --> 프로토콜에 대한 기본포트는 생략가능 https-443, http-80, ftp-20
	 * /javase/8/docs/api/java/util/ArrayList.html
	 * 
	 * protocol : 통신규약 http, https, ftp ,smtp...
	 * 
	 * port : 연결통로, 서비스번호, 컴퓨터내에 특정프로그램을 가르키는 논리적인 번호
	 */
	public void test2() {
		
		try {
			URL url = new URL("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%BD%94%EB%A1%9C%EB%82%98");
//			URL url = new URL("https://docs.oracle.com:443/javase/8/docs/api/java/util/ArrayList.html");
			//프로토콜 출력
			System.out.println("protocol : "+url.getProtocol());
			System.out.println("host : "+url.getHost());
			System.out.println("port : "+url.getPort());
			//위에 new URL에서 :443 과같은 포트를 입력안했으면 -1이 찍히므로 getDefaultPort사용
			System.out.println("default port : "+url.getDefaultPort());
			System.out.println("Path : "+url.getPath());
			System.out.println(url.getQuery());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * URL 연결요청 -> 파일로 저장까지 해보자
	 * 
	 */
	public void test3() {
		String address = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%BD%94%EB%A1%9C%EB%82%98";
		URL url;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			url = new URL(address);
			URLConnection conn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			bw = new BufferedWriter(new FileWriter("search_result.html"));
			String s;
			while((s=br.readLine())!=null) {
				System.out.println(s+"\n");
				bw.write(s+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
