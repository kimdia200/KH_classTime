package kh.java.io.byte_.system.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest {

	public static void main(String[] args) {
		FileIOTest f = new FileIOTest();
		f.test2();
		System.out.println("----정상종료----");
	}

	/**
	 * 한글은 3바이트라 1바이트씩 읽어오는 이방식은 한글이 깨진다...
	 * FileInputStream의 경우 원본파일이 없으면 NullPointException발생
	 * FileOutputStream의 경우 원본파일이 없어도 새로 생성해줌
	 */
	public void test1() {
		String filePath = "test.txt";
		String copyPath = "byte/test_copy.txt";
		// FileInputStream 은 byte기반으로서 Int형으로 표현
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			int data = 0; // 읽어온 데이터를 담을 변수 (byte는 경우의수가 모자름)
//			FileInputStream fis = new FileInputStream(filePath);   //1번
			fis = new FileInputStream(filePath); // 2번
			fos = new FileOutputStream(copyPath);

			while ((data = fis.read()) != -1) {
				System.out.print((char)data);
				fos.write(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { //IOException 은 FileNotFoundExcption 보다 상위라 이거하나만 써도됨
			e.printStackTrace();
		} finally {
			try {
				fis.close(); 
				// 1번의 경우 불가능....왜? 블럭이 달라서... 선언만 블럭밖으로 빼줌
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 내컴퓨터의 이미지 파일을 복사해서 byte폴더 하위에 위치 시킬것.
	 * 경로상의 한글을 피할것
	 */
	public void test2() {
		String filePath = "C:\\Users\\kimYS\\Desktop\\Git\\KH_classTime\\java_workSpace\\13_IO\\111.png";
		String copyPath = "byte/222.png";
		// FileInputStream 은 byte기반으로서 Int형으로 표현
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			int data = 0; // 읽어온 데이터를 담을 변수 (byte는 경우의수가 모자름)
//			FileInputStream fis = new FileInputStream(filePath);   //1번
			fis = new FileInputStream(filePath); // 2번
			fos = new FileOutputStream(copyPath);

			while ((data = fis.read()) != -1) {
				System.out.print((char)data);
				fos.write(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { //IOException 은 FileNotFoundExcption 보다 상위라 이거하나만 써도됨
			e.printStackTrace();
		} finally {
			try {
				fis.close(); 
				// 1번의 경우 불가능....왜? 블럭이 달라서... 선언만 블럭밖으로 빼줌
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
