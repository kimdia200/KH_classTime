package kh.java.gui.swing.container.layout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kh.java.gui.util.MyUtil;

public class FlowLayoutTest extends JFrame{
	public static void main(String[] args) {
		new FlowLayoutTest(500,500,"flowLayout").setVisible(true);
	}
	
	public FlowLayoutTest(int width, int height, String title) {
		MyUtil.init(this, width, height, title);
		
		//JPanel의 기본 Layout객체
		JPanel panel = new JPanel();
		//원래 기본값은 center였음(left, right, center)
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		for (int i = 0; i < 15; i++) {
			panel.add(new JButton((i+1)+"번"));
		}
		
		add(panel);
	}
}
