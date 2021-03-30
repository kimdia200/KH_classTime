package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({ "/LoginFilter",
			 "/member/memberView",
			 "/member/deleteMember",
			 "/member/updatePassword"
})
public class LoginFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//로그인 여부 확인
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//기존 코드에서 수정, getSession()을 하면 세션이 없으면 만들어서라도 가져오기 때문에 기존에 if(session==null)은 불필요하다
		HttpSession session = req.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember==null) {
			session.setAttribute("msg", "로그인 이후 사용 할 수 있습니다.");
			res.sendRedirect(req.getContextPath());
			return;
		}
		
		chain.doFilter(request, response);
	}
}
