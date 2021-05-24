package com.kh.spring.common.aop.test;

public class FooImpl implements Foo {

	@Override
	public void sayHello() {
		//주 업무로직
		System.out.println("Hello!!!!");
	}

	@Override
	public String getName() {
		return "foo";
	}
	
	
}
