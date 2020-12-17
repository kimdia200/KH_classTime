package kh.java.oop.encapsulation.account;

public class AccountMain {

	public static void main(String[] args) {
		
		Account acc = new Account();
//		acc.name = "홍길동";
//		acc.balance = 1_000_000;
		acc.setName("홍길동");
		acc.setBalance(1_000_000);
		
		
		
		//입금
//		acc.balance +=500_000;
		acc.deposit(-500_000);
		//잔액확인
//		System.out.println(acc.name + "님의 계좌 잔액 : "+acc.balance + "원");
		System.out.println("[현재 "+acc.getName() + "님의 계좌 잔액 : "+acc.getBalance() + "원]");

		//출금
//		acc.balance -= 100_000;	
		acc.withdraw(100_000);
		//잔액확인
//		System.out.println(acc.name + "님의 계좌 잔액 : "+acc.balance + "원");
		System.out.println("[현재 "+acc.getName() + "님의 계좌 잔액 : "+acc.getBalance() + "원]");
		
		

	}

}
