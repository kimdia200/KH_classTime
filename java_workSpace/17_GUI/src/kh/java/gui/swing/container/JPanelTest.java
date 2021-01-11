package kh.java.gui.swing.container;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelTest extends JFrame {
	public static void main(String[] args) {
		new JPanelTest();
	}

	public JPanelTest() {
		// 제목지정
		setTitle("Hello Swing");

		// 윈도우 크기지정
		setSize(800, 500);

		// 스크린 가운데 띄우기
		setLocationRelativeTo(null);

		// X버튼 -> 프로그램종료 처리
		// 이걸 해주지 않으면 프로그램은 꺼진거처럼 보여도 콘솔에서 계속 돌아가고있다.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//JFrame의 기본 LayoutManager객체는 BorderLayout이다.
//		setLayout(new BorderLayout()); //이게 기본값으로 설정되어있는거임(숨어져있다!)
		//null layout : layout manager객체를 사용하지 않고 직접 좌표를 지정함.
		setLayout(null);
		
		// 자식 컴포넌트추가 : 컨테이너에 포함된 컴포넌트
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.magenta);
//		panel1.setBackground(new Color(255,0,0)); //rgb값으로 조정가능
		JLabel label1 = new JLabel("panel1");
		panel1.setBounds(100, 50, 300, 400);
		panel1.add(label1);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.gray);
		JLabel label2 = new JLabel("panel2");
		panel2.setBounds(400, 50, 300, 400);
		panel2.add(label2);
		
		//프레임에 패널 추가
		add(panel1, BorderLayout.NORTH);
		add(panel2);

		// 시각화처리(맨 마지막에 작성)
		setVisible(true);
	}
}
