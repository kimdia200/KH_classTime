package kh.java.thread;

public class ThreadBasicTest {

	public static void main(String[] args) {
		ThreadBasicTest t = new ThreadBasicTest();
		t.test2();
		System.out.print("메인끝");
	}
	
	/**
	 * 싱글쓰레드
	 */
	public void test0() {
		//작업 A
		for(int i=0; i<100; i++) {
			System.out.print('|');
		}
		//작업 B
		for(int i=0; i<100; i++) {
			System.out.print('-');
			
		}	
	}
	
	/**
	 * 멀티쓰레드 생성방법1
	 * 1.Thread 클래스 상속(Custom Class만듬)
	 */
	public void test1() {
		Thread th1 = new CustomThread1('|');
		Thread th2 = new CustomThread1('-');
		
		//우선순위 지정(1~10) 기본값 5
		th2.setPriority(Thread.MAX_PRIORITY);//10
		th1.setPriority(Thread.MIN_PRIORITY);//1
		
		//시작은 run이 아니라 start로 함
		th1.start();
		th2.start();
	}
	
	/**
	 * 멀테쓰레드 생성방법2
	 * Runnable 인터페이스 구현
	 */
	public void test2() {
		Runnable run1 = new CustomThread2('|');
		Runnable run2 = new CustomThread2('-');
		
		Thread th1 = new Thread(run1);
		Thread th2 = new Thread(run2);
				
		th1.start();
		th2.start();
	}
}
