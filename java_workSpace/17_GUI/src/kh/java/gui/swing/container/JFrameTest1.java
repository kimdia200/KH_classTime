package kh.java.gui.swing.container;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 	JFrame객체 생성방법
 * 1. new JFrame() 직접 객체 생성하기 
 * 2. Jfame을 상속한 커스텀 클래스 작성
 * 
 */
public class JFrameTest1 {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		//제목지정
		f.setTitle("Hello Swing");
		
		//윈도우 크기지정
		f.setSize(300,200);
		f.setLocation(0,0);
		
		//스크린 가운데 띄우기
		f.setLocationRelativeTo(null);
		
		//X버튼 -> 프로그램종료 처리
		//이걸 해주지 않으면 프로그램은 꺼진거처럼 보여도 콘솔에서 계속 돌아가고있다.
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//자식 컴포넌트 : 컨테이너에 포함된 컴포넌트
		f.add(new JLabel("안녕"));
		
		//시각화처리(맨 마지막에 작성)
		f.setVisible(true);
	}
}
