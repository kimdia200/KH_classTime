package kh.java.oop.student;

public class Speaker {
	String brand;
	String color;
	int volume;
	int maxVolume;
	
	public void volumeUp() {
		volume++;
	}
	public void volumeDown() {
		volume--;
	}
	
	public void volumeUp(int num) {
		volume+=num;
	}
	public String info() {
		return brand+", "+color+", "+volume+", "+maxVolume;
	}
	
	
}
