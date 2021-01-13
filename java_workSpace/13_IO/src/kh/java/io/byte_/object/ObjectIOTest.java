package kh.java.io.byte_.object;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
/**
 * 객체를 통째로 넣었다가 뺐다가 할수있는 스트림
 * 바이트 기반 스트림
 * 
 * 객체배열[10]을 보낸다고 하면 객체배열 하나만 씌여지고
 * 그 객체배열 하나만 가져와서 우리가 입맛대로 할수 있다.
 * @author kimYS
 *
 */
public class ObjectIOTest {
	public static void main(String[] args) {
		ObjectIOTest o = new ObjectIOTest();
		o.test1();
		o.test2();
	}
	
	/**
	 * 오브젝트를 만들어서 파일로 저장하기
	 */
	private void test1() {
		
		File f = new File("object/user.ser");
		try(//FileOutputStream = 대상을 가르키는 기본스트림, BufferedOutputStream, ObjectOutputStream = 보조스트림
			ObjectOutputStream oos = 
				new ObjectOutputStream(
						new BufferedOutputStream(
								new FileOutputStream(f))); 
		){
			
			User u1 = new User("honggd","1234,","홍길동",Calendar.getInstance());
			User u2 = new User("sinsa","1234,","신사임당",Calendar.getInstance());
			User u3 = new User("sejong","1234,","세종",Calendar.getInstance());
			oos.writeObject(u1);
			oos.writeObject(u2);
			oos.writeObject(u3);
			System.out.println("객체 쓰기 완료!!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void test2() {
		File f = new File("object/user.ser");
		try(//FileOutputStream = 대상을 가르키는 기본스트림, BufferedOutputStream, ObjectOutputStream = 보조스트림
				ObjectInputStream ois = 
					new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream(f))); 
		){
			while(true) {//다 읽어오면 EOFException발생 하니까 예외처리해줌
				System.out.println(ois.readObject());
			}
//			System.out.println(ois.readObject());//동적바인딩에 의해 override된 메서드 호출된것
//			System.out.println(ois.readObject());
//			System.out.println(ois.readObject());
//			System.out.println(ois.readObject().toString());//내가 혼자 한거
		}catch (EOFException e) { //더이상 읽어올게 없을경우!!!!
		System.out.println("파일 읽어오기 완료");
		}catch (IOException | ClassNotFoundException e) {//멀티 캐치절 버티컬바 하나인거 메모
			e.printStackTrace();
		}
	}
}
