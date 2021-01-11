package kh.java.gui.swing.container.layout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kh.java.gui.util.MyUtil;

public class BorderLayoutTest extends JFrame {
	public static void main(String[] args) {
		new BorderLayoutTest(500, 500, "Border LayoutTest").setVisible(true);
	}

	public BorderLayoutTest(int width, int height, String title) {
		MyUtil.init(this, width, height, title);

		JPanel north = new BorderPanel("북", Color.CYAN);
		JPanel south = new BorderPanel("남", Color.gray);
		JPanel east = new BorderPanel("동", Color.magenta);
		JPanel west = new BorderPanel("서", Color.PINK);
		JPanel center = new BorderPanel("센터", Color.orange);
		
		this.add(north, BorderLayout.NORTH);
		this.add(south, BorderLayout.SOUTH);
		this.add(east, BorderLayout.EAST);
		this.add(west, BorderLayout.WEST);
		this.add(center, BorderLayout.CENTER);
	}

	/**
	 * 내부클래스
	 * 외부클래스에서 자유롭게 가져다 사용할 수 있다.
	 */
	public class BorderPanel extends JPanel {
		public BorderPanel(String title, Color color) {
			setBackground(color);
			JLabel label = new JLabel(title);
			add(label);//현재 JPanel객체에 추가
		}
	}
}
