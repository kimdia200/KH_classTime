package kh.java.thread.synchronization;

public class Account {
	private int balance;

	public Account(int balance) {
		super();
		this.balance = balance;
	}

	/**
	 * 출금메소드 : atm기는 이 메소드를 통해 출금 가능 1.synchronized 메소드 만들기 2.synchronized block
	 * 사용하기(추천)
	 */
	public void withdraw(int money) {
		String threadName = Thread.currentThread().getName();

		synchronized (this) {
			System.out.println("[" + threadName + " -> 잔액 : ￦" + balance + "]");
			if (money <= balance) {
				balance -= money;
				System.out.println("[" + threadName + " -> 출금 : ￦" + money + ", 잔액 : ￦" + balance + "]");
			} else {
				System.out.println("[" + threadName + " -> 출금 : ￦" + money + ", 잔액이 부족합니다.]");
			}
		}

	}

	public int getBalance() {
		return balance;
	}
}
