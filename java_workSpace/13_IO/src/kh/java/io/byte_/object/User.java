package kh.java.io.byte_.object;

import java.io.Serializable;
import java.util.Calendar;
/**
 * 직렬화 할 수 있는 Serializable
 * 
 * 객체입출력스트림에서 사용되어질 객체는 반드시 Serializable을 구현해야한다.
 *
 */
public class User implements Serializable {
	
	/**
	 * JVM에서 File로 저장을하고나면 읽어올때 내 JVM애 등록되어있는 클래스 여야함으로 비교를 하게됨
	 * 
	 * 비교를 할때 두가지를 보는데 패키지포함된 풀네임, serialVersionUID(고유번호)를 확인함
	 * 없어도 크게 문제는 되지 않는당
	 */
	private static final long serialVersionUID = 1L;
	
	
	//모든 필드값도 객체입출력스트림을 사용하려면 Serializable을 구현해야한다
	//String, Calendar등 java에서 제공하는 클래스들은 거의다 구현이 되어있다.
	private String id;
	//객체입출력스트림에서 해당값을 전송하지 않는다 transient
	private transient String password;
	private String name;
	private Calendar regDate;
	public User() {
		super();
	}
	public User(String id, String password, String name, Calendar regDate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Calendar getRegDate() {
		return regDate;
	}
	public void setRegDate(Calendar regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", regDate=" + regDate + "]";
	}
}
