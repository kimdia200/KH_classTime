package kh.java.gui.swing.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kh.java.gui.util.MyUtil;

/**
 * Event 컴퓨터(프로그램)와 사용자간의 상호작용
 * 
 * event -> os -> jvm -> event분배 쓰레드 -> 각 component event handler 메서드 호출
 * 
 * 1.저수준 이벤트 
 * key Event
 * mouse Event 
 * focus event 
 * window event....
 * 
 * 
 * 2.고수준 semantic Event(Component별 처리) 
 * action event 
 * item event 
 * adjustment event....
 * 
 *
 */
public class LowLevelEventTest1 extends JFrame {
	public static void main(String[] args) {
		new LowLevelEventTest1(300, 200, "저수준 이벤트").setVisible(true);
	}

	public LowLevelEventTest1(int width, int height, String title) {
		MyUtil.init(this, width, height, title);

		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		panel.add(label);

		
		
		//컴포넌트에 이벤트 핸들러 객체 바인딩
//		MyMouseListener listener = new MyMouseListener();
//		panel.addMouseListener(listener);
//		panel.addMouseWheelListener(listener);
//		panel.addMouseMotionListener(listener);
		
		//MyMouseListener2가 MouseListener의 기능만 구현해놨기때문
		panel.addMouseListener(new MyMouseListener2());
		
		add(panel);
	}

	public class MyMouseListener implements MouseListener,MouseWheelListener,MouseMotionListener{
		
		//눌러다 뗏다 = pressed+released = 1click
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked!"+e);
		}
		
		//마우스를 누른상태
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("mousePressed!");
		}
		
		//마우스를 누른상태에서 뗄때
		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("mouseReleased!");
		}
		
		//마우스가 범위안으로 들어왔을때
		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("mouseEntered!");
		}
		
		//마우스가 범위밖으로 나갈때
		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("mouseExited!");
		}
		
		//휠긁을때
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			System.out.println("mouseWheelMoved!");
		}
		
		//드래그할때는 이게 계속 작동해서 Moved는 작동이안됬음
		@Override
		public void mouseDragged(MouseEvent e) {
			System.out.println("mouseDragged");
		}
		
		//드래그 상태가 아닐때 마우스 움직일때마다 호출
		@Override
		public void mouseMoved(MouseEvent e) {
			System.out.println("mouseMoved"+e.getX()+", "+e.getY());
		}

	}
	
	//MouseAdapter클래스를 extends를 하면 원하는 기능만 구현이 가능하다
	//Adapter클래스를 한번 살펴보면 그이유를 알수있다.
	public class MyMouseListener2 extends MouseAdapter{
		//눌러다 뗏다 = pressed+released = 1click
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked! "+e.getX()+", "+e.getY());
		}
	}
}
