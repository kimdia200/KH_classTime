package kh.java.api.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * java.util.Calendar
 * java.util.Date 기본생성자, long타입생성자를 제외하고 사용금지!(deprecated)
 * 
 * java.time.LocalDateTime
 *
 */
public class DateTimeTest {

	public static void main(String[] args) {
		DateTimeTest dt = new DateTimeTest();
		dt.test3();
	}
	/**
	 * Calendar
	 */
	public void test1() {
		//현재 날짜, 시각 정보 조회
		Calendar cal1 = Calendar.getInstance(); //new Calendar 불가능 왜??추상클래스 라서
		Calendar cal2 = new GregorianCalendar();
		
//		System.out.println(cal1); //toString 으로 모든값 볼수있음
		
		//조회
		System.out.println(cal1.get(Calendar.YEAR));
		System.out.println(cal1.get(Calendar.MONTH)+1);
		System.out.println(cal1.get(Calendar.DATE));
		System.out.println(cal1.get(Calendar.HOUR));//12h
		System.out.println(cal1.get(Calendar.HOUR_OF_DAY));//24h
		System.out.println(cal1.get(Calendar.MINUTE));
		System.out.println(cal1.get(Calendar.SECOND));
		
		//요일(일1, 월2, 화3, 수4, 목5, 금6, 토7)
		System.out.println(cal1.get(Calendar.DAY_OF_WEEK));
		char[] dayOfWeek = {'일', '월', '화', '수', '목', '금','토'};
		System.out.println(dayOfWeek[cal1.get(Calendar.DAY_OF_WEEK)-1]);
		
		printCalendar(cal1);
		
	}
	/**
	 * yyyy/mm/dd hh24:mm:ss
	 */
	public void printCalendar(Calendar cal) {
		System.out.printf("%d/%02d/%02d %02d:%02d:%02d%n",
				cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH)+1,
				cal.get(Calendar.DATE),
				cal.get(Calendar.HOUR_OF_DAY),
				cal.get(Calendar.MINUTE),
				cal.get(Calendar.SECOND)
				);
	}
	
	/**
	 * 특정일 특정시각을 Calendar 객체로 생성
	 */
	public void test2() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2020,0,1,0,0,0); //2020년1월1일 0시 0분 0초
		printCalendar(cal1);
		
		Calendar cal2 = new GregorianCalendar(2021,0,1,0,0,0);
		printCalendar(cal2);
		
		//날짜 차이 계산하기
		Calendar now = Calendar.getInstance();
		//밀리초 단위로 변환해서 차이를 구한후, 다시 초분시일 단위로 환산
		//밀리초 = 1000분의 1초
		//unix second : 1970년 1월 1일 자정기준으로 흐른 밀리초를 얘기함
		long num1 = now.getTimeInMillis();
		long num2 = cal2.getTimeInMillis();
		long diff = (num2 - num1)/1000/60/60/24;
		System.out.println(num1);
		System.out.println(num2);
		System.out.println("새해까지 " +(diff+1)+"일 남음~");
	}
	/**
	 * Date클래스
	 * Date()
	 * Date(long millis)
	 * 위 생성자만 사용 가능하다
	 */
	public void test3() {
		Date now = new Date();
		System.out.println(now);
		
		//to Calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(now); //setTime은 date타입을 파라미터로 받아서 처리해줌
		printCalendar(cal);
		
		//calendar to date
		long mills = cal.getTimeInMillis();
		Date now2 = new Date(mills);
		System.out.println(now2);
	}
}
