package com.kh.spring.member.controller;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member")
@SessionAttributes("loginMember")
public class MemberController {

	@GetMapping("/memberLogin.do")
	public void memberLogin() {}
	
	/**
	 * 
	 * Authentication
	 * 1. Principal : 인증된 사용자객체
	 * 2. Credential : 인증에 필요한 비밀번호
	 * 3. Authorities : 인증된 사용자가 가진 권한
	 * 
	 */
	@GetMapping("/memberDetail.do")
	public void memberDetail(Authentication authentication, @AuthenticationPrincipal Member member, Model model) {
		//1.security context holder bean
		//SecurityContextHolder로 부터 가져오는 방법
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		//2. handler의 매개인자로 authentication객체 요청
		// UsernamePasswordAuthenticationToken
		log.debug("authentication = {}", authentication); 
		log.debug("member = {}", (Member)authentication.getPrincipal());
		
		//3. @AuthenticationPrincipal Member member
		log.debug("member = {}", member);
		
		model.addAttribute("loginMember", (Member)authentication.getPrincipal());
		
	}
	
	@PostMapping("/memberUpdate.do")
	public String memberUpdate(Member updateMember, Authentication oldAuthentication, RedirectAttributes redirectAttr) {
		//1.업무로직 : db의 내용 수정
		//수정값들이 저장되어있는 updateMember객체
		log.debug("updateMember = {}", updateMember);
		
		
		//updateMember에 authorities setting
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(GrantedAuthority auth: oldAuthentication.getAuthorities()) {
			//SimpleGrantedAuthority는 GrantedAuthority의 자손으로 문자열로 권한을 입력받을수있음
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(auth.getAuthority());
			authorities.add(simpleGrantedAuthority);
		}
		
		updateMember.setAuthorities(authorities);
		
		updateMember.setPassword(((Member)oldAuthentication.getPrincipal()).getPassword());
		
		//2. security context 에서 principal 갱신
		Authentication newAuthentication = 
				//public UsernamePasswordAuthenticationToken(
						//Object principal, 
						//Object credentials,
						//Collection<? extends GrantedAuthority> authorities) {}
				new UsernamePasswordAuthenticationToken(
							updateMember,
							oldAuthentication.getCredentials(),
							oldAuthentication.getAuthorities()
						);
		SecurityContextHolder.getContext().setAuthentication(newAuthentication);
		
		//3.사용자 피드백
		redirectAttr.addFlashAttribute("msg", "사용자 정보 수정 성공");
		
		return "redirect:/member/memberDetail.do";
	}
	
	
	/**
	 * 커맨드객체 이용시 사용자입력값(String)을 특정필드타입으로 변환할 editor객체를 설정
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//Member.birthday:java.sql.Date 타입 처리
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//커스텀에디터 생성 : allowEmpty - true (빈문자열을 null로 변환처리 허용)
		PropertyEditor editor = new CustomDateEditor(sdf, true);
		binder.registerCustomEditor(java.sql.Date.class, editor);
	}
	
	
}
