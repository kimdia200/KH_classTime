package kh.java.gui.swing.event;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kh.java.gui.util.MyUtil;

public class LowLevelEventTest2 extends JFrame{
	JTextField input;
	JTextArea textArea;
	
	public class MyKeyListener implements KeyListener{
		
		//마우스의 Clicked랑 같은 원리다
		@Override
		public void keyTyped(KeyEvent e) {
			System.out.println("keyTyped : "+e.getKeyCode()+" " +e.getKeyChar());
		}
		
		//Pressed, Released는 마우스와 똑같다
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("pressed : "+e.getKeyCode()+" " +e.getKeyChar());
			if(e.getExtendedKeyCode()==KeyEvent.VK_ENTER) {
				System.out.println("엔터를 입력하셨습니다.");
				String s = input.getText();
				textArea.append(s+"\n");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	}
	public static void main(String[] args) {
		new LowLevelEventTest2(300,200,"키이벤트").setVisible(true);
	}
	
	public LowLevelEventTest2(int width, int height, String title) {
		MyUtil.init(this, width, height, title);
		
		//topPanel
		initTopPanel();
		
		//centerPanel
		initCenterPanel();
	}
	
	private void initTopPanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("사용자 입력 : "));
		input = new JTextField(10);
		panel.add(input);//컬럼수로 너비지정 가능, setSize굳이 안해도
		add(panel, BorderLayout.NORTH);
		
		//이벤트 핸들러 추가
		input.addKeyListener(new MyKeyListener());
	}
	
	private void initCenterPanel() {
		JPanel panel = new JPanel();
		textArea = new JTextArea(5,20);
		panel.add(textArea);
		add(panel);
	}
	
	
}
