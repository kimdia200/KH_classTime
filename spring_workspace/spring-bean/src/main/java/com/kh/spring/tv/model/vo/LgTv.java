package com.kh.spring.tv.model.vo;

public class LgTv implements Tv {

	//의존객체
	private RemoteControl remocon;
	
	/**
	 * bean>property
	 * setter를 이용해서 의존주입 받았다 Dependency Injection
	 * @param remocon
	 */
	public void setRemocon(RemoteControl remocon) {
		System.out.println("setRemocon : " + remocon);
		this.remocon = remocon;
	}

	public LgTv() {
		System.out.println("LgTv객체를 생성 했습니다.");
	}
	
	/**
	 *  bean>constructor
	 *  생상자를 이용한 의존주입 DI방법
	 * @param remocon
	 */
	public LgTv(RemoteControl remocon) {
		System.out.println("LgTv객체 생성 : "+ remocon);
		this.remocon = remocon;
	}

	@Override
	public void powerOn() {
		System.out.println("LgTv의 전원을 켰습니다.");
	}
	
	public void changeChannel(int no) {
		this.remocon.changeChannel(no);
	}
	
}
