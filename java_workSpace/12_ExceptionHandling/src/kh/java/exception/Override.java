package kh.java.exception;

import java.io.IOException;
import java.sql.SQLException;

/*override 상속받은 메소드를 고쳐쓸수 있다.
* 1. 메소드명, 파라미터선언부, 리턴타입 모두 동일해야한다.
* 		- @Override annotation
* 2. 접근제한자는 더 넓은 범위로 수정이 가능
* 		- private(x) - default(x) - protected - public
* 		- private메소드는 오버라이드 불가
* 		- default메소드는 거의 없음.
* 3. 메소드가 던지는 예외클래스는 줄이거나, 자식클래스로 변경가능 
*/
public class Override {
	
}

class Parent{
	public void test() throws SQLException, IOException{
		
	}
}

class Child extends Parent{
//	public void test() throws SQLException, IOException {}
//	public void test() throws IOException {}
//	public void test() throws SQLException {}
//	public void test() throws FileNotFoundException {}//IOException의 하위클래스이기에
//	public void test() throws Exception {}//상위클래스로는 던질수없다
}