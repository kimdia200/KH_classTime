package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter{

	/**
	 * adapter를 사용하므로 pre만 구현해도 상관없다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//로그인 여부를 체크해서 로그인 하지 않은 경우, return false;
		
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember == null) {
			//FlashMap을 통해서 redirect후 사용자 피드백 전달하기
			//그냥 map이라고 생각하면됨
			FlashMap flashMap = new FlashMap();
			flashMap.put("msg", "로그인후 사용 하실 수 있습니다.");
			
			//FlashMapManager를 통해서 flashMap저장
			FlashMapManager manager = RequestContextUtils.getFlashMapManager(request);
			manager.saveOutputFlashMap(flashMap, request, response);
			
			//위의 과정은 model.addFlashAttribute()와 비슷함
			
			//로그인후 최종이동할 url을 session속성 next에 저장
			//login.do 에서는 리다이렉트로 가면 referer가 없기 때문에 내가 세션에 next라는 이름으로 지금 인터셉터에서 담아줌
			String url = request.getRequestURL().toString();
			request.getSession().setAttribute("next", url);
			response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
}
