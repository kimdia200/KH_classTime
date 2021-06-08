package com.kh.spring.member.model.vo;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
/**
 * UserDetails를 상속해서 해야지만 spring security를 사용 할 수 있다.
 * 
 * @author kimYS
 *
 */
public class Member implements UserDetails{

	private String id;  // 스프링 시큐리티 재료1 . username로 사용될 필드
	private String password; // 스프링 시큐리티 재료2 . password로 사용될 필드
	private String name;
	private String gender;
	private Date birthday;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
	
	
	//복수개의 권한을 관리
	// 재료3 . 문자열 data("ROLE_USER", "ROLE_ADMIN")를 처리할수있는 GrantedAuthority의 하위클래스
	private List<SimpleGrantedAuthority> authorities;
	
	//재료4. 활성화 여부 확인할수있는 필드
	private boolean enabled;

	/**
	 * 
	 * Collection - List/Set
	 * 
	 * Collection<? extends GrantedAuthority> : GrantedAuthority를 상속받는 ? = 자식타입
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getUsername() {
		return id;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}
