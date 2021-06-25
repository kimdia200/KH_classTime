package com.kh.jdk8.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Optional =-존재 할 수도 있고, 안 할 수도있는 객체를 감싸고있는 Wrapper객체
 * 변수명에 mybeMember, optUser와 같이 Optional타입을 암묵적으로 제시한다.
 * 
 * 1. Optional.empty() : 비어있는 Optional객체
 * 2. Optional.of(obj) : 결코 null이 아닌 객체를 담은 Optional객체
 * 3. Optional.ofNullable(obj) : Null일수도있는 Optional객체
 * 
 * get()
 * orElse(T)
 * orElseGet(Supplier)
 * orElseThrow(Supplier)
 * isPresent:boolean
 * ifPresent(Consumer)
 * 
 * 
 * 
 * if(s != null){
 * 	s.foo();
 * }
 * 
 * 
 *
 */
public class OptionalStudy {
	public static void main(String[] args) {
		OptionalStudy study = new OptionalStudy();
//		study.test1();
		study.test2();
	}
	
	
	public void test2() {
		//성공하는것
		Member member = new Member();
		member.setEmail("honggd@naver.com");
		Order order = new Order();
		order.setMember(member);
		getEmailOfMemberFromOrder(order);

		//실패하는것
		getEmailOfMemberFromOrder(null);//아예 객체 자체가 null
		getEmailOfMemberFromOrder(new Order());//객체 안에 member필드가 null
	}
	
	public String getEmailOfMemberFromOrder(Order order) {
		return Optional.ofNullable(order)
					   .map(o->o.getMember())
					   .map(member->member.getEmail())
					   .orElse("메일이 존재하지 않습니다.");
	}
	@Data
	@NoArgsConstructor
	
	static class Order {
		Member member;
		int count;
	}
	@Data
	@NoArgsConstructor
	static class Member {
		String id;
		String email;
	}
	
	public void test1() {
		List<String> list = Arrays.asList("홍길동", "신사", null, "");
		list.stream().forEach(s->System.out.println(s+" : "+getStringLength(s)));
	}
	
	public int getStringLength(String s) {
		Optional<String> maybeStr = Optional.ofNullable(s);
		return maybeStr.map(str->s.length()).orElse(0);
	}
}













