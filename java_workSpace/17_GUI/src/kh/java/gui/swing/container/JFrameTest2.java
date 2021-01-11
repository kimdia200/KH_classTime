package kh.java.gui.swing.container;

import javax.swing.JFrame;

public class JFrameTest2 extends JFrame {

	public JFrameTest2() {
		setTitle("두번째 프레임");
//		setLocation(200, 200);
//		setSize(300, 200);
		//setLocation 과 setSize를 동시에 처리할수 있는 메서드 존재
		//-> 강사님이 좀더 선호하는 방법
		setBounds(200,200,300,200);
		
		//리사이즈 방지 
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new JFrameTest2();
	}

}
