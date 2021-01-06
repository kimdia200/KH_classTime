package kh.java.thread.synchronization;
/**
 * 계좌 Account 계좌에 동시에 접근하는 ATM이 2대 있다 (ATM을 쓰레드로 설정)
 * 
 * 각 ATM기가 하나의 계좌에서 출금을 하는 상황
 * 
 */
public class WithdrawTest {
	public static void main(String[] args) {
		//계좌
		Account acc = new Account(1000);
		
		Thread atm1 = new Thread(new Atm(acc));
		Thread atm2 = new Thread(new Atm(acc));
		
		atm1.setName("atm1");
		atm2.setName("atm2");
		
		atm1.start();
		atm2.start();
	}
}
