package com.kh.spring.common.aop.test;

public class FooProxy implements Foo{
	
	private Foo foo; //실제 FooImpl객체
	
	public FooProxy(Foo foo) {
		this.foo = foo;
	}
	
	@Override
	public void sayHello() {
		
		//before영역
		System.out.println("---------------before---------------");
		
		//주 업무로직 실행
		this.foo.sayHello();
		
		//after영역
		System.out.println("---------------after---------------");
	}

	@Override
	public String getName() {
		return this.foo.getName().toUpperCase();
	}
}
