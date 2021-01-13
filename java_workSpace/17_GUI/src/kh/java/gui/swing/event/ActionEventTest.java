package kh.java.gui.swing.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kh.java.gui.util.MyUtil;
/**
 * EventListener를 bind하는 방법
 * 1.내부클래스로 선언해서 객체생성
 * 2.익명 클래스로 선언과 동시에 객체생성(추상클래스, 인터페이스도 new연산자 사용가능)
 * 3.JFrame 커스텀 클래스에서 Listener구현하기
 *
 */
public class ActionEventTest extends JFrame implements ActionListener{
	
	JTextField input;
	
	public ActionEventTest(int width, int height, String title) {
		MyUtil.init(this, width, height, title);
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		input = new JTextField(10);
		
		//1.input컴포넌트에 ActionEventHandler바인딩
//		input.addActionListener(new MyActionListener());
		
		//2.무명클래스로 리스너만들기
//		ActionListener listener = new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("익명클래스 actionPerformed!!");
//				JOptionPane.showMessageDialog(null, input.getText());
//				
//				//초기화
//				input.setText("");
//				input.requestFocus();//바로 입력할 수 있도록 foucs를 가져오기
//			}
//		};
//		input.addActionListener(listener);
		
		//3.JFrameCustome클래스가 ActionListener구현하기
		input.addActionListener(this);
		
		panel.add(label);
		panel.add(input);
		add(panel);
		
	}
	//3번째 방법 (JFrame Custom클래스에서 Listener구현하기)
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed!!");
		JOptionPane.showMessageDialog(null, input.getText());
		
		//초기화
		input.setText("");
		input.requestFocus();//바로 입력할 수 있도록 foucs를 가져오기
	}
	
	public class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("actionPerformed!!");
			JOptionPane.showMessageDialog(null, input.getText());
			
			//초기화
			input.setText("");
			input.requestFocus();//바로 입력할 수 있도록 foucs를 가져오기
		}
	}
	public static void main(String[] args) {
		new ActionEventTest(300, 200, "액션 이벤트").setVisible(true);;
	}
	
}
