package kh.java.thread;

public class CustomThread1 extends Thread {
	char ch;

	public CustomThread1(char ch) {
		super();
		this.ch = ch;
	}

	/**
	 * 쓰레드 작업 내용을 여기에 작성
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.print(ch);
		}
	}
}
