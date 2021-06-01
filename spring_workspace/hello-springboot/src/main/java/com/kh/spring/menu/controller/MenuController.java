package com.kh.spring.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.menu.model.servoce.MenuService;
import com.kh.spring.menu.model.vo.Menu;

import lombok.extern.slf4j.Slf4j;

/**
 * 기존 @Controller 와 비교했을때
 * 
 * 모든 handler는 @ResponseBody로 처리된다.
 *
 *
 * C Post
 * R Get
 * U Put
 * D Delete
 * 
 * 
 */
@RestController
@Slf4j
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/menus")
	public List<Menu> menus(){
		List<Menu> list = null;
		try {
			//1. 업무로직
			list = menuService.selectMenuList();
			log.debug("list = {}", list);
			
			//2. 리턴하면 @ResponseBody에 의해서 json변환후, 응답출력 처리됨
		} catch (Exception e) {
			log.error("전체메뉴 조회 실패",e);
		}
		return list;
	}
	
	/**
	 * @PathVariable 경로변수
	 * 
	 * 경로를 중괄호로 감싸면 그경로는 파라미터의 pathVariable에서 가져다 쓸수있다.
	 */
	@GetMapping("/menus/{type}/{taste}")
	public List<Menu> menuBytype(@PathVariable String type, @PathVariable String taste){
		List<Menu> list = null;
		try {
			log.debug("type = {}, taste = {}",type,taste);
			Map<String, Object> param = new HashMap<>();
			param.put("type", type);
			param.put("taste", taste);
			
			list = menuService.selectMenuListByTypeAndTaste(param);
			log.debug("list = {}", list);
		} catch (Exception e) {
			log.error("/menus 오류",e);
			throw e;
		}
		
		return list;
	}
	
	/**
	 * @RequestBody 요청메세지의 body에 작성된 json문자열을 java객체로 변환
	 * 
	 * type의 경우 json으로 전달받을경우 MenuType의 getValue에 @JsonValue 어노테이션이 붙어있어서
	 * 자동으로 변환되어 입력해준다.
	 * 
	 * 즉 enum클래스를 처리할때는 Json으로 전달하여 @RequestBody 어노테이션을 활용하면 훨씬 간단하게 처리가능하다.
	 * 
	 * @param menu
	 * @return
	 */
	@PostMapping("/menu")
	public Map<String, Object> insertMenu(@RequestBody Menu menu){
		Map<String, Object> map = null;
		try {
			log.debug("menu = {}", menu);
			int result = menuService.insertMenu(menu);
			map = new HashMap<>();
			map.put("msg", "메뉴 등록 성공");
		} catch (Exception e) {
			log.error("메뉴 등록 실패");
			throw e;
		}
		return map;
	}
	
	@GetMapping("/menu/{id}")
	public Map<String, Object> selectMenu(@PathVariable int id){
		try {
			log.debug("id = {}",id);
			Menu menu = menuService.selectMenu(id);
			
			Map<String, Object> map = new HashMap<>();
			if(menu != null) {
				map.put("msg", "조회 성공");
				map.put("menu", menu);
			}else {
				map.put("msg", "조회 결과가 없습니다");
			}
			return map;
		} catch (Exception e) {
			log.error("DB조회 실패");
			throw e;
		}
	}
	
	@PutMapping("/menu/{id}")
	public Map<String, Object> updateMenu(@RequestBody Menu menu){
		try {
			log.debug("updatemenu = {}", menu);
			int result = menuService.updateMenu(menu);
			Map<String, Object> map = new HashMap<>();
			if(result>0) {
				map.put("msg", "업데이트 성공");
			}else {
				map.put("msg", "없데이트");
			}
			return map;
		} catch (Exception e) {
			log.error("메뉴 업데이트 실패");
			throw e;
		}
	}
}
