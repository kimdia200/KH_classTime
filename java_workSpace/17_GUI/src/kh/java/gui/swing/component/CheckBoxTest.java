package kh.java.gui.swing.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kh.java.gui.util.MyUtil;

public class CheckBoxTest extends JFrame {
	
	JLabel resultLabel;
	
	public static void main(String[] args) {
		new CheckBoxTest(300, 200, "체크박스").setVisible(true);
	}

	public CheckBoxTest(int width, int height, String title) {
		MyUtil.init(this, width, height, title);
		// 메서드를 추가하면서 하나씩 하는 구조
		topPanel();
		sizePanel();
		resultPanel();
	}

	private void topPanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("과일을 선택하세요");
		panel.add(label);
		add(panel, BorderLayout.NORTH);
	}

	private void resultPanel() {
		JPanel panel = new JPanel();
		JCheckBox[] arr = new JCheckBox[3]; 
		arr[0] = new JCheckBox("apple");
		arr[1] = new JCheckBox("banana");
		arr[2] = new JCheckBox("peach");

		
		//이벤트핸들러 무명클래스 처리
		for(JCheckBox c : arr) {
			c.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//					System.out.println("actionPerformed!");
					List<String> checkedList = new ArrayList<>();
					for(JCheckBox chk : arr) {
						if(chk.isSelected())
							checkedList.add(chk.getText());
					}
					
					if(checkedList.isEmpty())
						resultLabel.setText("아무것도 선택하지 않았습니다.");
					else 
						resultLabel.setText(checkedList + "를 선택했습니다.");
				}
				//내가 만들어본거
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					String s = "";
//					int count=0;
//					for(JCheckBox d : arr) {
//						if(d.isSelected()) {
//							s +=d.getText()+" ";
//							count++;
//						}
//					}
//					if(count!=0)
//						resultLabel.setText(s);
//					else
//						resultLabel.setText("아무것도 선택하지 않았습니다.");
//				}
			});
			panel.add(c);
		}
		add(panel);
	}

	private void sizePanel() {
		JPanel panel = new JPanel();
		this.resultLabel = new JLabel("아무것도 선택하지 않았습니다.");
		panel.add(resultLabel);
		add(panel,BorderLayout.SOUTH);
	}
}