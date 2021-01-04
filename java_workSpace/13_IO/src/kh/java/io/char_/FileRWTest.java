package kh.java.io.char_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileRWTest {
	public static void main(String[] args) {
		FileRWTest f = new FileRWTest();
		f.test1();
		System.out.println();
		f.test2();
	}

	/**
	 * 문자 기반 스트림 2byte씩 처리
	 * char = 2byte 한글자를 읽어와서 찍어내는것
	 * 
	 * 기본스트림 = 한글짜씩 처리 구분자 : 해서 확인해봄
	 */
	private void test1() {
		//경로상의 실제 파일을 가르키는 자바객체
		//경로상에 존재해도 되고 존재하지 않아도됨(없으면 만들어줌)
		File f= new File("test.txt");
		FileReader fr = null;
			
		try {
			fr = new FileReader(f);
			
			int data = 0;
			
			while((data=fr.read())!=-1) {
				System.out.print((char)data+":");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 보조스트림 한줄씩 실행
	 * 구분자:를 넣어서 확인해봄
	 */
	private void test2() {
		File f = new File("test.txt");
		File dest = new File("char/test_copy.txt");
		
		//finally 안해도됨 JDK 1.7부터 사용 가능
		try(BufferedReader br = new BufferedReader(new FileReader(f));
			BufferedWriter bw = new BufferedWriter(new FileWriter(dest));
		){
			String data = null;
			
			while((data = br.readLine()) != null) {
				System.out.println(data+":");
				bw.write(data+"\n"); //readLine은 개행문자는 가져오지 않아서 추가해줌
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
