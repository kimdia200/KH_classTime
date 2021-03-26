package member.model.vo;

import java.sql.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 
 * VO클래스
 * 
 * DB테이블의 한행의 정보를 가지고 있는 객체
 * 
 */
public class Member implements HttpSessionBindingListener{
	private String memberId;
	private String password;
	private String memberName;
	private String memberRole;
	private String gender;
//	시분초 정보까지 필요하다면 sql.Date가 아닌 Timestamp로 해줘야함
//	private Timestamp
	private Date birthday;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;
	
	
	//기본 생성자
	public Member() {
		super();
	}
	
	//파라미터 생성자
	public Member(String memberId, String password, String memberName, String memberRole, String gender, Date birthday,
			String email, String phone, String address, String hobby, Date enrollDate) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.memberRole = memberRole;
		this.gender = gender;
		this.birthday = birthday;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}

	//getter, setter
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	//toString
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName
				+ ", memberRole=" + memberRole + ", gender=" + gender + ", birthday=" + birthday + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", hobby=" + hobby + ", enrollDate=" + enrollDate + "]";
	}

	
	//인터페이스의 default 메서드 두개 오버라이드
	/**
	 * Session객체에 현재 객체가 속성으로 등록 될때 호출 되는 event listener
	 * 우리는 로그인할때 memberlogin 이라는 이름으로 등록하고있음
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println(memberName + "["+ memberId+"]님이 로그인 했습니다.");
	}
	
	/**
	 * Session객체에 현재 객체가 속성에서 해제 될때 호출되는 event listener
	 * Session 이 무효화 될때도 호출됨.
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println(memberName + "["+ memberId+"]님이 로그아웃 했습니다.");
	}
	
	
}
