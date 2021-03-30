package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

//implements : javax.servlet.Filter
public class LoggerFilter implements Filter{

	/**
	 * init, destroy 메서드 두개는 8버전부터 default메서드로 변경됨에 따라
	 * 꼭 강제로 override해주지 않아도 된다.
	 * 
	 * doFilter에서 필요한 버전만 구현하면 될듯!!
	 */
	 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("LoggerFilter의 init메서드 호출");
	}

	@Override
	public void destroy() {
		System.out.println("LoggerFilter의 destroy 메서드 호출");
	}
	
	@Override
	//파라미터 request, response, FilterChain chain
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//1. servlet 호출전
		System.out.println("\n===========================================");
		HttpServletRequest httpReq = (HttpServletRequest)request;
		String uri = httpReq.getRequestURI();
		System.out.println(uri);
		
		System.out.println("---------------------------------------------");
		
		
		//다음 filterChain객체를 호출
		//만약 다음 filter 객체가 존재하지 않으면, servlet호출
		//chain.doFilter의 위치를 기준으로 
		//아래 코드 이전에 작성한 코드는 servlet으로 가기전에 작동하고
		//이후에 작성한 코드는 servlet에서 처리 이후에 작동한다.
		chain.doFilter(request, response);
		
		//2. servlet/jsp 처리 이후
		System.out.println("_____________________________________________");
	}
}
