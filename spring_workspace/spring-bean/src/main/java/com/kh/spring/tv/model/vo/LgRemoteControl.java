package com.kh.spring.tv.model.vo;

public class LgRemoteControl implements RemoteControl {

	@Override
	public void changeChannel(int no) {
		System.out.println("Life is Good! 채널을"+no+"번으로 변경합니다");
	}

}
