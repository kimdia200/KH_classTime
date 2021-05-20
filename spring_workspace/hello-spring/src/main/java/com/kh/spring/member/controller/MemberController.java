package com.kh.spring.member.controller;

import java.beans.PropertyEditor;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/memberEnroll.do")
	public void memberEnroll() {
		// 포인트 1. void반환타입
		// 포인트 2. /WEB-INF/views/member/memberEnroll.jsp로 자동 포워딩됨
		// DefaultRequestToViewNameTranslator 빈이 요청 주소에서 viewName을 유추해서 찾아준것임
	}
	
	@PostMapping("/memberEnroll.do")
	public String memberEnroll(Member member, RedirectAttributes redirectAttr) {
		
		try {
			log.info("member = {}",member);
			//0. 비밀번호 암호화 처리
			String rawPassword = member.getPassword();
			String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
			member.setPassword(encodedPassword);
			log.info("암호화 처리 이후 = {}",member);
			
			//1. 업무로직
			int result = memberService.insertMember(member);
			
			//2. 사용자피드백
			redirectAttr.addFlashAttribute("msg","회원가입성공");
			
		} catch (Exception e) {
			
		}
		return "redirect:/";
	}
	
	/**
	 * java.sql.Date, java.util.Date 필드에 값대입시
	 * 사용자 입력값이 없는경우 null로 처리될 수 있도록 설정
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//public CustomDateEditor(DateFormat dateFormat, boolean allowEmpty)
		//두번째 인자에서 true로 설정함으로써 null을 허용 하게 끔 해준것
		PropertyEditor editor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, editor);
	}
	
	@GetMapping("/memberLogin.do")
	public void memberLogin() {
	}
	
	@PostMapping("/memberLogin.do")
	public String memberLogin(@RequestParam String id, @RequestParam String password, Model model, RedirectAttributes redirectAttr) {
		//1. 업무로직
		Member member = memberService.selectOneMember(id);
		log.info("member = {}",member);
		
		//2. 로그인 여부 분기처리
		if(member != null && bCryptPasswordEncoder.matches(password, member.getPassword())) {
			//기존에는 로그인 성공시 reqeust.getsession에 저장했지만 스프링에서는 model에 저장한다
			//아래와 같이 저장하면 request Scope에 저장한것과 같다...
			//세션에 저장 하려면 class 레벨에 @SessionAttributes로 등록
			model.addAttribute("loginMember",member);
			
			redirectAttr.addFlashAttribute("msg","로그인 성공");
		}
		else {
			redirectAttr.addFlashAttribute("msg","로그인 실패");
		}
		
		return "redirect:/";
	}
}