package kh.java.gui.swing.container.layout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kh.java.gui.util.MyUtil;

public class NullLayoutTest extends JFrame{
	private JLabel labelID;
	private JTextField id;
	private JLabel labelPwd; 
	private JPasswordField pwd;
	private JButton btn;
	
	public static void main(String[] args) {
		new NullLayoutTest(500,500,"로그인").setVisible(true);
	}
	
	public NullLayoutTest(int width, int height, String title) {
		MyUtil.init(this, width, height, title);
		
		//Layout 무효화
		setLayout(null);
		
		labelID = new JLabel("아이디 : ");
		labelID.setBounds(50,100,60,50);
		
		id = new JTextField();
		id.setBounds(110, 100, 200, 50);
		
		labelPwd = new JLabel("비번 : ");
		labelPwd.setBounds(50, 160, 60, 50);
		
		pwd = new JPasswordField();
		pwd.setBounds(110, 160, 200, 50);
		
		btn = new JButton("LOGIN");
		btn.setBounds(320, 100, 100, 110);
		
		add(labelID);
		add(id);
		add(labelPwd);
		add(pwd);
		add(btn);
		
		//사용자 로그인버튼을 클릭했을때, 입력한 id, pwd값 가져오기
		//사용자 Event + EventHandling
		//1.이벤트 처리객체 생성(클래스 작성)
		//2.btn컴포넌트와 연결(binding)
		
		btn.addActionListener(new LoginListener());
//		btn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("id : "+id.getText());
//			}
//		});
	}
	
	/**
	 * JButton용 action event handler클래스 작성
	 * 
	 *  event handler
	 *  event listener
	 *
	 */
	public class LoginListener implements ActionListener{

		/**
		 * JButton의 ActionEvent가 발생시 자동호출되는 메서드
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			//외부클래스에 있는 필드값에 접근해서 가져왔음 private여도 상관없음
			System.out.println("id : "+id.getText());
			System.out.println("pwd : "+new String(pwd.getPassword()));
		}
	}
}
