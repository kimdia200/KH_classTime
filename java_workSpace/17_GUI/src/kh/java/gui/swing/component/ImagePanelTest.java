package kh.java.gui.swing.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kh.java.gui.util.MyUtil;

public class ImagePanelTest extends JFrame{
	public static void main(String[] args) {
		new ImagePanelTest(500,500,"백그라운드 이미지").setVisible(true);
	}
	
	public ImagePanelTest(int width, int height, String title) {
		MyUtil.init(this, width, height, title);
		
		//이미지사용하기 = 특이한점 라벨을 컨테이너로 사용한다
//		JLabel container = new JLabel(new ImageIcon("images/cat.PNG"));
//		add(container);
//		
//		pack();//실제 컴포넌트의 크기에 맞게 컨테이너의 크기를 확대/축소한다.
		
		ImagePanel panel = new ImagePanel("images/background.jpg");
		JButton btn = new JButton("안녕");
		panel.add(btn);
		add(panel);
		pack();
		
	}
	public class ImagePanel extends JPanel{
		private BufferedImage image;
		private int width;
		private int height;
		
		public ImagePanel(String fileName) {
			
			try {
				image = ImageIO.read(new File(fileName));
				width=image.getWidth();
				height=image.getHeight();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		/**
		 * 현재 패널크기를 백그라운드 이미지의 크기로 지정
		 */
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(width,height);
		}
		
		/**
		 * 
		 */
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			//img객체를 x,y값 만큼 떨어뜨려서 그리기 메서드
			g.drawImage(image, 0, 0, null);
		}
	}
}
