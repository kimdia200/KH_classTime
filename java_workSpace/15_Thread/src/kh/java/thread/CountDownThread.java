package kh.java.thread;

public class CountDownThread implements Runnable {
	private int num;
	private long millis;
	
	
	public CountDownThread(int num, long millis) {
		super();
		this.num = num;
		this.millis = millis;
	}

	@Override
	public void run() {
		for(int i=num; i>=0; i--) {
			System.out.println(i);
//			delay(1000);
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println("["+Thread.currentThread().getId()+"종료]");
	}
	
	public void delay(int a) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
