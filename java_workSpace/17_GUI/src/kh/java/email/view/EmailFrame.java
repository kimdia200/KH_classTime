package kh.java.email.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kh.java.email.controller.EmailController;
import kh.java.email.vo.Email;
import kh.java.gui.util.MyUtil;

public class EmailFrame extends JFrame {
	private JTextArea textArea;
	private EmailController emailController = new EmailController();
	
	public EmailFrame(int width, int height, String title) {
		MyUtil.init(this, width, height, title);
		
		JPanel inputPanel = new JPanel();
		JTextField inputEmail = new JTextField(10);
		JButton submitBtn = new JButton("등록");
		inputPanel.add(inputEmail);
		inputPanel.add(submitBtn);
		
		
		//제출 이벤트 핸들러
		submitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = inputEmail.getText()+"\n";
				Email email = new Email(s);
				emailController.insertEmail(email);
				
				//사용자피드백
				JOptionPane.showMessageDialog(null, "정상동작 되었습니다");
				//inputEmail 초기화
				inputEmail.setText("");
				textArea.insert(s,0);
				//입력된 목록 불러오기
				loadEmailList();
			}
		});
		
		
		JPanel listPanel = new JPanel();
		textArea = new JTextArea(5,20);
		loadEmailList();
		listPanel.add(textArea);
		
		add(inputPanel, BorderLayout.NORTH);
		add(listPanel);
	}
	protected void loadEmailList() {
		List<Email> list = emailController.loadEmailList();
		System.out.println("list@emailFrame = " + list);
		
		textArea.setText("");//초기화
		
		for(Email email : list)
			textArea.append(email.getEmail());
		
		
	}
}
