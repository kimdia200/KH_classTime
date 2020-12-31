package kh.java.io.byte_.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataIOTest {
	public static void main(String[] args) {
		DataIOTest d = new DataIOTest();
//		d.test1();
		d.test2();
		System.out.println("정상종료");
	}
	
	/**
	 * 파일 쓰기 했음
	 */
	private void test1() {
		File f = new File("data/sample.dat");
		try(
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
		){
			//byte단위로 사용하는게 아니라 자바 자료형별로 작성 가능
			dos.writeBoolean(true); //1바이트 작성
			dos.writeInt(300); //4바이트작성
			dos.writeDouble(3.14); //8바이트작성
			dos.writeChar('안'); //2바이트 작성
			dos.writeChar('녕'); //2바이트 작성
			dos.writeUTF("자바자바자바"); //utf8,참조형으로 작성(먼가있겠지)
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DataOutputStream 작업완료!");
	}
	/**
	 * 이번에는 읽어올거임
	 */
	private void test2() {
		File f = new File("data/sample.dat");
		
		try(
			DataInputStream dis = new DataInputStream(new FileInputStream(f));
		){

			//씌여진 자료형 순서대로 읽어올것, 안그러면 파일이 깨질 수 있음.
			System.out.println(dis.readBoolean());
			System.out.println(dis.readInt());
			System.out.println(dis.readDouble());
			System.out.println(dis.readChar());
			System.out.println(dis.readChar());
			System.out.println(dis.readUTF());
//			dis.readChar(String)//2byte식 대로 나열
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
