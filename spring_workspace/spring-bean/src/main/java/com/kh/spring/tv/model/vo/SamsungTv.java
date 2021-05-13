package com.kh.spring.tv.model.vo;

public class SamsungTv implements Tv {
	private RemoteControl remocon;
	
	public SamsungTv() {
		System.out.println("SamsungTv객체를 생성했습니다.");
	}
	
	public SamsungTv(RemoteControl remocon) {
		this();
		this.remocon = remocon;
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTv의 전원을 켰습니다.");
	}
	
	public void changeChannel(int no) {
		this.remocon.changeChannel(no);
	}

}
