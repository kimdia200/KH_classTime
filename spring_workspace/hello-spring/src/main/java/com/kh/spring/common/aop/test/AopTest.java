package com.kh.spring.common.aop.test;

/**
 * Spring에서 의존주입해서 Proxy를 만들어주면
 * 우리가 AOP를 사용하는 과정을 알아보기위한 Test클래스
 * 
 * AopTest - FooProxy(Foo interface 상속) - FooImpl(Foo interface상속) 
 * 실제로는 FooImpl의 주업무 메소드를 사용하지만
 * 메서드 사용 앞뒤로 보조업무를 FooProxy 클래스에서 하거나 return값도 수정 할 수 있다.
 *
 */
public class AopTest {
	
	Foo foo = new FooProxy(new FooImpl());
	
	public static void main(String[] args) {
		
		//Foo foo = new FooImpl();
		//foo.sayHello();
		
		
//		new AopTest().fooTest();
		String name = new AopTest().getName();
		System.out.println(name);
	}
	
	private String getName() {
		return foo.getName();
	}

	public void fooTest() {
		foo.sayHello();
	}
}
