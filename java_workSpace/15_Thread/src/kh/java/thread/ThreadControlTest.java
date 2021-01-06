package kh.java.thread;

import javax.swing.JOptionPane;

public class ThreadControlTest {
	public static void main(String[] args) {
		ThreadControlTest t = new ThreadControlTest();
		t.test4();

		System.out.println("[" + Thread.currentThread().getName() + " 종료]");
	}

	public void test1() {
		Thread a = new Thread(new SleepThread('|', 5));
		Thread b = new Thread(new SleepThread('-', 3));

		a.setName("a쓰레드");
		b.setName("b쓰레드");

		a.start();
		b.start();
	}

	/**
	 * 3의배수 출력 쓰레드와 4의배수 출력 쓰레드를 각각 생성하고 실행하세요 [Thread이름 - 배수] 실행간격은 200밀리초 100초과시
	 * 중지할것
	 * 
	 */
	public void test2() {
		Thread a = new Thread(new MyThread(3, 200));
		Thread b = new Thread(new MyThread(4, 200));

		a.start();
		b.start();		
	}

	/**
	 * 쓰레드간의 종속관계
	 * 일반 쓰레드 - Daemon쓰레드
	 * 
	 * Daemon - linux계열의 컴퓨터에서 백그라운드 프로세스를 가리키는 말
	 */
	public void test3() {
		//countDown 쓰레드 50 49 48...
		Thread count = new Thread(new CountDownThread(50, 100));
		count.start();
		
		//main thread 일시정지
		JOptionPane.showMessageDialog(null, "확인을 누르면 메인쓰레드가 종료됩니다.");
	}
	
	/**
	 * 쓰레드 종료시키기
	 * 1. 해당쓰레드의 interrupt()메소드 호출
	 * 2. InterruptedException 발생
	 * 3. catch절에 다음액션(종료) 작성
	 * 
	 */
	public void test4() {
		Thread count = new Thread(new CountDownThread(50, 100));
		count.start();
		JOptionPane.showMessageDialog(null, "확인을 누르시면, 카운트다운을 종료합니다.");
		count.interrupt();
	}
}
