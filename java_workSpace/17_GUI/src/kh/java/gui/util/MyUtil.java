package kh.java.gui.util;

import javax.swing.JFrame;

public class MyUtil {

	public static void init(JFrame f, int width, int height, String title) {
		f.setTitle(title);
		f.setSize(width,height);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
