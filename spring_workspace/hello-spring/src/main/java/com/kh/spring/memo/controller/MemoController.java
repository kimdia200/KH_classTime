package com.kh.spring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	private MemoService memoService;
	
	@GetMapping("/memo.do")
	public ModelAndView selectMemoList(ModelAndView mav) {
		//class com.sun.proxy.$Proxy40 라고 찍히네
		//spring이 proxy(대리) 객체를 만들어서 의존주입 해준것을 확인 할 수 있다.
		/**
		 * 정리!
		 * 의존 주입 받은 객체는 우리가 작성햇던 MemoController, MemoServiceImpl, MemoDaoImpl타입 객체가 아닌
		 * proxy 객체이다.
		 * 
		 * proxy객체에서 의존주입된 메서드를 호출 하는방식으로
		 * 
		 * aspect의 advice가 실행되는것은 proxy객체에서 실행되는것이다.
		 * 이런 작업을 할수 있도록 new 연산자를 이용해서 필드를 작성하는것보다 @AutoWired 어노테이션으로 의존주입하는게 좋다
		 * 
		 * proxy객체의 종류
		 * 
		 * 1. jdk동적 proxy - interface구현체 class com.sun.proxy.$Proxy40
		 * 2. cglib - interface구현체 아닌경우
		 */
		log.debug("memoController = {}", this.getClass());
		log.debug("memoService = {}", memoService.getClass());
		
		List<Memo> list = memoService.selectMemoList();
		log.debug("list = {}",list);
		
		mav.addObject("list", list);
		
//		mav.setViewName("memo/memo");
//		위의 코드를 안써줘도 DefaultRequestToViewNameTranslator 빈이 요청 주소에서 viewName을 유추해서 찾아줌
		return mav;
	}
	
	@PostMapping("/insertMemo.do")
	public String insertMemo(@RequestParam String memo, RedirectAttributes redirectAttr) {
		log.debug("Memo = {}",memo);
		int result =  0;
		try {
			result = memoService.insertMemo(memo);
			
			if(result>0) {
				//성공
				redirectAttr.addFlashAttribute("msg", "메모 추가 성공");
			}
		} catch (Exception e) {
			log.error("메모 추가 실패",e);
			throw e;
		}
		
		return "redirect:/memo/memo.do";
	}
	
	@PostMapping("/deleteMemo.do")
	public String deleteMemo(@RequestParam String no, RedirectAttributes redirectAttr) {
		log.debug("no = {}",no);
		int result =  0;
		try {
			result = memoService.deleteMemo(no);
			
			if(result>0) {
				//성공
				redirectAttr.addFlashAttribute("msg", "메모 삭제 성공");
			}
		} catch (Exception e) {
			log.error("메모 삭제 실패",e);
			throw e;
		}
		
		return "redirect:/memo/memo.do";
	}
	
}
