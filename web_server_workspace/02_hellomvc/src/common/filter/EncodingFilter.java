package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * web.xml에 등록된 순서대로 처리됨.
 * 
 * 그후 어노테이션으로 작성된 친구가 처리된다.
 * 
 * 따라서 필터의 순서를 정확하게 매칭 시켜야되는 구조라면
 * web.xml에 순서대로 작성하도록 한다.
 *
 */

@WebFilter({ "/EncodingFilter", "/*" })
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("인코딩 필터 작동!");
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		System.out.println("인코딩 필터 종료!");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
