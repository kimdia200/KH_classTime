package kh.java.email.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import kh.java.email.vo.Email;

/**
 * 직접 파일에 접근하는 성격의 코드를 작성
 * controller나 view단에 있어서는 안되는것
 * 
 */
public class EmailIO {

	/**
	 * emailList.txt라는 파일을 만들어서
	 * 
	 * Email객체 추가하기
	 */
	public void insertEmail(Email email) {
		List<Email> list = loadEmailList();
		list.add(email);
		File f = new File("emailList.txt");
		
		try ( ObjectOutputStream oos = 
				new ObjectOutputStream(
					new BufferedOutputStream(
						new FileOutputStream(f))))
		{
			
			for(Email e : list) {
				oos.writeObject(e);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public List<Email> loadEmailList() {
		List<Email> list = new ArrayList<>();
		File f = new File("emailList.txt");
		try(
			ObjectInputStream ois = 
				new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		){
			while(true) {
				Email email = (Email)ois.readObject();
				list.add(email);
			}
		}catch (FileNotFoundException e){
			try {
				//최초 등록시 오류가 발생할수있음
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}catch (EOFException e) {
			//처리코드 없음.
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
