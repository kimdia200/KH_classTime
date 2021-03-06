package com.kh.spring.member.controller;

import java.beans.PropertyEditor;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Model
 * MVC 패턴의 Model이 아니다.
 * view단에 전달하기 위한 데이터를 저장하는 Map
 * 
 * 1. Model <<interface>>
 * 		- addAttribute(name, value)
 * 		- viewName:String을 리턴
 * 2. ModelMap
 * 		- Model Interface의 구현체 이다 사용법은 동일
 * 		- addAttribute(name, value)
 * 		- viewName:String을 리턴
 * 3. ModelAndView
 * 		- addObject(name, value)
 * 		- setViewName를 이용해서 viewName설정
 * 		- ModelAndView객체를 리턴
 * 
 * -> 결국은 ModelAndView로 통합되서 spring container에 의해 관리된다.
 * 
 * @ModelAttribute | @SessionAttribute
 * - handler의 매개변수 앞에 사용
 * - 모델에 저장된 속성에 대한 getter
 * - name, required(기본값 true) 지정이 가능하다
 * 
 * @ModelAttribute
 *  - method레벨에 작성
 *  - 이 메서드는 model속성을 setter
 *  - 현재 controller클래스의 모든 handler에 앞서 실행되면, 모든 요청에서 해당 데이터에 접근 가능
 * 
 */
@Slf4j
@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember", "next"})
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * common메서드를 호출 하지 않아도 view단에서 사용이 가능하당...
	 * memberController의 메서드를 하나 사용할때마다  이 메서드도 자동으로 실행 되기 때문 로그흘 확인해보자
	 * view단에서는 ${common.adminEmail}, ${common.adminPhone} 사용가능
	 * @return
	 */
	@ModelAttribute("common")
	public Map<String, Object> common(){
		log.info("@modelAttribute(\"common\"");
		Map<String, Object> map = new HashMap<>();
		map.put("adminEmail", "admin@kh.or.kr");
		map.put("adminPhone", "070-1234-5678");
		return map;
	}
	
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
	public void memberLogin(@SessionAttribute(required = false) String next , @RequestHeader (name = "Referer", required = false) String referer, Model model) {
		log.info("referer = {}", referer);
		if(referer != null && next==null)
			model.addAttribute("next", referer);
	}
	
	@PostMapping("/memberLogin.do")
	public String memberLogin(@RequestParam String id, @RequestParam String password, @SessionAttribute(required = false) String next, Model model, RedirectAttributes redirectAttr) {
		//1. 업무로직
		Member member = memberService.selectOneMember(id);
		log.info("member = {}",member);
		//기존 비밀번호 수정하기 위해 아래에서 비크립트 비밀번호 알아냄
//		log.info("{} = encryptedPassword = {}",id, bCryptPasswordEncoder.encode(password));
		
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
			return "redirect:/member/memberLogin.do";
		}
		model.addAttribute("next",null);
		return "redirect:"+ (next != null ? next : "/");
	}
	
	/**
	 * @SessionAttributes를 통해서 등록한 session속성은 
	 * SessionStatus객체에 대해서 complete처리해야한다.
	 * 
	 * @return
	 */
	@GetMapping("/memberLogout.do")
	public String memberLogout(SessionStatus status, @RequestHeader (name = "Referer", required = false) String referer) {
		
		//세션에서 loginMember삭제
		if(!status.isComplete())
			//다썻어요 하고 처리해주는것
			status.setComplete();
		return "redirect:"+referer;
	}
	
	/**
	 * 로그인한 사용자 정보 열람하기
	 * @return
	 */
	@GetMapping("/memberDetail.do")
	public ModelAndView memberDetail(ModelAndView mav, @SessionAttribute Member loginMember) {
		log.info("loginMember = {}",loginMember);
		//속성 저장
		mav.addObject("time", System.currentTimeMillis());
		//viewName설정, 안써줘도 자동으로 잘찾아주네
		//mav.setViewName("member/memberDetail");
		return mav;
	}
	
	/**
	 * @실습문제 내정보 수정하기
	 * @내가한버전 
	 */
//	@PostMapping("/memberUpdate.do")
//	public String memberUpdate(@ModelAttribute Member member, RedirectAttributes redirectAttr, @RequestHeader (name = "Referer", required = false) String referer) {
//		try {
////			id name date email phone address gender hobby
//			log.debug("member = {}",member);
//			//0. 비밀번호 암호화 처리
//			
////			//1. 업무로직
//			int result = memberService.updateMember(member);
//			
////			//2. 사용자피드백
//			if(result>0) {
//				redirectAttr.addFlashAttribute("msg","정보수정 성공!");
//			}
//			else
//				redirectAttr.addFlashAttribute("msg","정보수정 실패!");
//		} catch (Exception e) {
//			log.error("사용자 정보 수정 오류!", e);
//			throw e;
//		}
//		return "redirect:"+ referer;
//	}
	
	
	/**
	 * member와 loginMember는 같은 name값을 필드로 갖기 때문에 loginMember도 수정이 자동으로 이뤄진다
	 * 파라미터에 등록만 해놓으면 
	 */
	@PostMapping("/memberUpdate.do")
	public ModelAndView memberUpdate(
								@ModelAttribute Member member,
								@ModelAttribute("loginMember") Member loginMember,
								ModelAndView mav, 
								HttpServletRequest request
							) {
		log.debug("member = {}", member);
		log.debug("loginMember = {}", loginMember);
		
		try {
			//1. 업무로직
			int result = memberService.updateMember(member);
			
			//2. 사용자 피드백 & 리다이렉트
//			mav.setViewName("redirect:/member/memberDetail.do");
			
			//리다이렉트시 자동생성되는 queryString 방지
			RedirectView view = new RedirectView(request.getContextPath()+"/member/memberDetail.do");
			view.setExposeModelAttributes(false);
			mav.setView(view);
			
			//ModelAndView와 RedirectAttributes 충돌시 FlashMap을 직접 사용
			FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
			flashMap.put("msg", "사용자 정보 수정 성공!!!!!!");
//			redirectAttr.addFlashAttribute("msg", "사용자 정보 수정 성공!");
			
		} catch (Exception e) {
			log.error("사용자 정보 수정 오류!", e);
			throw e;
		}
		return mav;
	}
	
	/**
	 * Spring에서 ajax를 사용하는 방법(Json)
	 * 1. gson - 응답 메세지에 json문자열을 출력하는 방식  - servlet에서 했던방법
	 * 2. jsonView Bean을 통해 처리하기 - model에 담긴 데이터를 json으로 변환, 응답에 출력
	 * 3. @ResponseBody - 리턴된 자바객체를 JSON으로 변환, 응답에 출력 - jacksonMessageConverter이용함
	 * 4. ResponseEntity<응답할 자바객체> 를 제네릭을 이용해서 리턴
	 * 
	 * 1번을 제외(해봤으니까) 하고 하나씩 해볼것
	 * 
	 * @param id
	 * @return
	 */
	
	//JsonView방식
	@GetMapping("/checkIdDuplicate1.do")
	public String checkIdDuplicate1(@RequestParam String id, Model model) {
		log.debug("id = {}", id);
		//1. 업무로직
		Member member = memberService.selectOneMember(id);
		boolean available = member == null;
		log.debug("member = {}",member);
		//2. model에 속성 저장
		model.addAttribute("available",available);
//		model.addAttribute("member",member);
		return "jsonView";
	}
	
	//@ResPonseBody 사용방식
	@GetMapping("/checkIdDuplicate2.do")
	@ResponseBody
	public Map<String, Object> checkIdDuplicate2(@RequestParam String id) {
		//1. 업무로직
		Member member = memberService.selectOneMember(id);
		boolean available = member == null;
		
		//2. map에 요소 저장후 리턴
		Map<String, Object> map = new HashMap<>();
		map.put("available", available);
		map.put("id", id);
		return map;
	}
	
	//ResponseEntity
	@GetMapping("/checkIdDuplicate3.do")
	public ResponseEntity<Map<String, Object>> checkIdDuplicate3(@RequestParam String id) {
		//1. 업무로직
		Member member = memberService.selectOneMember(id);
		boolean available = member == null;
		
		//2. map에 요소 저장후
		Map<String, Object> map = new HashMap<>();
		map.put("available", available);
		map.put("id", id);
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.body(map);
	}
}