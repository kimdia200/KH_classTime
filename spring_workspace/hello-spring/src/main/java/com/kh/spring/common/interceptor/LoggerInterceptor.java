package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 핸들러 인터셉터로 사용하기 위해서는 HandlerInterceptorAdapter을 상속해줘야한다
 * 
 * 또한 servlet-context.xml에 등록해준다
 *
 */
@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter{

	/**
	 * Handler 호출전 실행
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.debug("=================== start ===================");
		log.debug(request.getRequestURI());
		log.debug("---------------------------------------------");
		
		return super.preHandle(request, response, handler); //true 리턴 고정
	}

	/**
	 * Handler 리턴이후
	 * ModelAndView객체 확인가능
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
		
		log.debug("---------------------------------------------");
		log.debug("ModelAndView = {}",modelAndView);
	}

	/**
	 * view단 작업 이후 
	 * (view단 = jsp작업 이후에 여길 한번 더 거치게됨)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		log.debug("---------------------------------------------");
		super.afterCompletion(request, response, handler, ex);
		log.debug("_____________________end________________________");
	}
	
}
