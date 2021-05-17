package com.kh.spring.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

/**
 * 사용자 요청 하나당 이를 처리하는 Controller 메서드가 하나씩 존재한다.
 * 
 * 이런 메서드를 Handler(핸들러) 라고한다.
 * 핸들러 - controller에 존재하는 메서드
 * 
 * 
 * <h1>handler메서드가 가질 수 있는 매개변수</h1>
 * 
 * <pre>
 * HttpServletRequest
 * HttpServletResponse
 * HttpSession
 * java.util.Locale : 요청에 대한 Locale
 * InputStream/Reader : 요청에 대한 입력스트림
 * OutputStream/Writer : 응답에 대한 출력스트림. ServletOutputStream, PrintWriter
 * @PathVariable: 요청url중 일부를 매개변수로 취할 수 있다.
 * @RequestParam
 * @RequestHeader
 * @CookieValue
 * @RequestBody
 뷰에 전달할 모델 데이터 설정
 * ModelAndView
 * Model
 * ModelMap 
 * @ModelAttribute : model속성에 대한 getter
 * @SessionAttribute : session속성에 대한 getter
 * SessionStatus: @SessionAttribute로 등록된 속성에 대하여 사용완료(complete)처리
 * Command객체 : http요청 파라미터를 커맨드객체에 저장한 VO객체
 * Error, BindingResult : Command객체에 저장결과, Command객체 바로 다음위치시킬것.
 기타
 * MultipartFile : 업로드파일 처리 인터페이스. CommonsMultipartFile
 * RedirectAttributes : DML처리후 요청주소 변경을 위한 redirect를 지원
 * </pre>
 */ 

@Controller
public class DemoController {
	/**
	 * spring용 logging클래스
	 */
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private DemoService demoService;
	
	/**
	 * 사용자 요청을 처리하는 핸들러
	 */
	@RequestMapping("/demo/devForm.do")
	public String devForm() {
//		System.out.println("/demo/devForm.do 요청!");
		log.info("/demo/devForm.do 요청");
		//viewResolver빈에 의해서 /WEB-INF/views + demo/devForm + .jsp 를 붙여서 jsp파일로 위임
		return "demo/devForm";
	}
	
	@RequestMapping("/demo/dev1.do")
	public String dev1(HttpServletRequest request, HttpServletRequest response) {
		//1. 사용자 입력값 처리
		String name = request.getParameter("name");
		int career = Integer.valueOf(request.getParameter("career"));
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String[] lang = request.getParameterValues("lang");
		
		Dev dev = new Dev(career, name, career, email, gender, lang);
		
		//괄호 자리에 dev객체를 문자열로 처리해서 출력해라
		//printf와 비슷함
		log.info("dev = {}", dev);
		
		//2. 업무로직
		
		//3. jsp에 출력
		request.setAttribute("dev", dev);
		return "demo/devResult";
	}
	
	/**
	 * 1. name값과 일치하는 매개변수에 전달됨
	 * name값(userName)이 매개변수(name)와 일치하지 않는다면, name="userName" 지정
	 * (name속성값이 매개변수명 보다 우선순위 높음)
	 * 
	 * 2. required 는 기본값 true, 사용자가 선택적으로 입력하는 필드는 false로 명시 할 것
	 * 
	 * 
	 * 3. defaultValue를 지정한 경우, 값이 없거나, 형변환 오류가 발생해도 기본값으로 정상처리된다.
	 * 
	 * @param name
	 * @param career
	 * @param email
	 * @param gender
	 * @param lang
	 * @param model
	 * @return
	 */
	@RequestMapping("/demo/dev2.do")
	public String dev2(
			//@RequestParam(name = "userName") String name,
			@RequestParam String name,
			@RequestParam(defaultValue = "1") int career,
			@RequestParam String email,
			@RequestParam String gender,
			//@RequestParam(required = false) String[] lang,
			//원래 기본값 true;
			@RequestParam String[] lang,
			Model model
			) {
		
		Dev dev = new Dev(0, name, career, email, gender, lang);
		log.info("dev = {}", dev);


		//jsp에 위임
		model.addAttribute("dev", dev); // jsp에서 scope="request"에 저장되어 있음.
		
		return "demo/devResult";
	}
}
