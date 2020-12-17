package kh.java.oop.encapsulation.account;

/**
 * 구조체의 단점을 극복한 클래스
 * private 접근 제한자를 사용해 field의 직접 접근을 방지
 * public 접근제한자를 사용한 method를 이용해 우회적으로 사용
 *
 */

public class Account {
	private String name;
	private long balance=0; //잔액
	
	
	//public method를 통해 우회 접근
	//필드에 데한 메소드 getter, setter + 입금, 출금
	//this 현재 객체를 가리키는 메소드안의 숨은 참조값
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public void deposit(long money) {
		
		if(money>0) {
			balance += money;
		}else
			System.out.println("잘못된 금액이 입력 되었습니다.");
	}
	public void withdraw(long money) {
		if(money<=balance) {
			balance -= money;
		}else
			System.out.println("잔액이 부족합니다");
	}
}
