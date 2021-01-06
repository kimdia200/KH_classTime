package kh.java.thread.synchronization;

public class Atm implements Runnable {
	private Account acc;

	public Atm(Account acc) {
		super();
		this.acc = acc;
	}

	@Override
	public void run() {
		while(acc.getBalance() > 0) {
			int money = (int)(Math.random() * 3 + 1) * 100;
			acc.withdraw(money);
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(Thread.currentThread().getName() + "종료!!!");
	}

}
