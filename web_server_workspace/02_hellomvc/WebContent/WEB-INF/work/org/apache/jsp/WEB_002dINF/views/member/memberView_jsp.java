/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.44
 * Generated at: 2021-03-29 12:14:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.util.Arrays;
import member.model.vo.*;

public final class memberView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {


	//선언문안에 메서드를 작성 할 수 있다.
	//강사님은 인라인으로 작성하고 인라인안에서 이 함수를 사용했음
	// value = "<% hobbyChecked(list, "운동")  % >"
	//강사님은 스플릿으로 짤라서 list에 넣었음(contatins를 사용하기 위해서)
	public String hobbyChecked(List<String> hobbyList, String hobby){
		return hobbyList != null && hobbyList.contains(hobby) ? "checked" : "";
	}

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1617008023884L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1616656820982L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_packages.add("member.model.vo");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("java.util.Arrays");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	//request는 일회용 , session은 생명주기가 보다 김
	/* String msg = (String)request.getAttribute("msg");
	String loc = (String)request.getAttribute("loc"); */
	
	String msg = (String)session.getAttribute("msg");
	if(msg!=null) session.removeAttribute("msg");//일회용 으로 쓰기위해! 안그럼 새로고침 할때마다 뜸...
	
	Member loginMember = (Member)session.getAttribute("loginMember");
	
	String signUpLog = (String)session.getAttribute("signUpLog");
	if(signUpLog!=null) session.removeAttribute("signUpLog");
	
	//사용자 쿠키처리
	String saveId = null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie c : cookies){
			String name = c.getName();
			String value = c.getValue();
			System.out.println(name+" : "+value);
			if("saveId".equals(name))
				saveId = value;
		}
	}

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Hello MVC</title>\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"");
      out.print(request.getContextPath());
      out.write("/css/style.css\" />\r\n");
      out.write("<!-- 아래와 같이 작성시 /mvc 라는 경로가 변경되면 문제가 발생 할 수 있으므로 JSP로 현재 어플리케이션 경로를 가져옴 -->\r\n");
      out.write("<!-- <script src=\"/mvc/js/jquery-3.6.0.js\"></script> -->\r\n");
      out.write("<script src=\"");
      out.print(request.getContextPath());
      out.write("/js/jquery-3.6.0.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("\t");
 if(msg!=null) {
      out.write("\r\n");
      out.write("\t\talert(\"");
      out.print( msg);
      out.write("\");\r\n");
      out.write("\t");
}
      out.write('\r');
      out.write('\n');
      out.write('	');
 if(signUpLog!=null) {
      out.write("\r\n");
      out.write("\talert(\"");
      out.print( signUpLog);
      out.write("\");\r\n");
}
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t");
      out.write('\r');
      out.write('\n');
      out.write('	');
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t/**\r\n");
      out.write("\t\t* 로그인 폼 유효성 검사 \r\n");
      out.write("\t\t*/\r\n");
      out.write("\t\t$(\"#loginFrm\").submit(function(){\r\n");
      out.write("\t\t\tvar $memberId = $(memberId);\r\n");
      out.write("\t\t\tvar $password = $(password);\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif(/^.{4,}$/.test($memberId.val()) == false){\r\n");
      out.write("\t\t\t\talert(\"유효한 아이디를 입력하세요.\");\r\n");
      out.write("\t\t\t\t$memberId.select();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(/^.{4,}$/.test($password.val()) == false){\r\n");
      out.write("\t\t\t\talert(\"유효한 비밀번호를 입력하세요.\");\r\n");
      out.write("\t\t\t\t$password.select();\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"container\">\r\n");
      out.write("\t\t<header>\r\n");
      out.write("\t\t\t<h1>Hello MVC</h1>\r\n");
      out.write("\t\t\t<!-- 로그인폼 시작 -->\r\n");
      out.write("\t\t\t<div class=\"login-container\">\r\n");
      out.write("\t\t\t");

				//로그인 실패시
				if(loginMember==null){
			
      out.write("\r\n");
      out.write("\t\t\t\t<form id=\"loginFrm\" action=\"");
      out.print(request.getContextPath());
      out.write("/member/login\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<!-- table 구조상 탭키시 이동하는 방향은 text:id -> submit -> text:pwd이지만 -->\r\n");
      out.write("\t\t\t\t\t\t\t<!-- tabindex 속성을 사용해서 내가 원하는대로 조정 해줄 수 있다. -->\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"text\" name=\"memberId\" id=\"memberId\"\r\n");
      out.write("\t\t\t\t\t\t\t\tplaceholder=\"아이디\" tabindex=\"1\" value=\"");
      out.print( saveId != null ? saveId : "");
      out.write("\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"submit\" value=\"로그인\" tabindex=\"3\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"password\" name=\"password\" id=\"password\"\r\n");
      out.write("\t\t\t\t\t\t\t\tplaceholder=\"비밀번호\" tabindex=\"2\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\"><input type=\"checkbox\" name=\"saveId\"\r\n");
      out.write("\t\t\t\t\t\t\t\tid=\"saveId\" ");
      out.print( saveId != null ? "checked" : "" );
      out.write("/> <label for=\"saveId\">아이디저장</label>&nbsp;&nbsp; <input\r\n");
      out.write("\t\t\t\t\t\t\t\ttype=\"button\" value=\"회원가입\" onclick=\"location.href='");
      out.print( request.getContextPath() );
      out.write("/member/memberEnroll';\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t<!-- 로그인폼 끝-->\r\n");
      out.write("\t\t\t");

				//로그인 성공시
				}else{
			
      out.write("\r\n");
      out.write("\t\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t\t<table id=\"login\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print( loginMember.getMemberName() );
      out.write("님, 안녕하세요.</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td> \r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" value=\"내정보보기\" onclick=\"location.href='");
      out.print( request.getContextPath() );
      out.write("/member/memberView';\"/>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" value=\"로그아웃\" onclick=\"location.href='");
      out.print(request.getContextPath());
      out.write("/member/logout';\"/>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- 메인메뉴 시작 -->\r\n");
      out.write("\t\t\t<nav>\r\n");
      out.write("\t\t\t\t<ul class=\"main-nav\">\r\n");
      out.write("\t\t\t\t\t<li class=\"home\"><a href=\"");
      out.print(request.getContextPath());
      out.write("\">Home</a></li>\r\n");
      out.write("\t\t\t\t\t<li class=\"notice\"><a href=\"#\">공지사항</a></li>\r\n");
      out.write("\t\t\t\t\t<li class=\"board\"><a href=\"#\">게시판</a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</nav>\r\n");
      out.write("\t\t\t<!-- 메인메뉴 끝-->\r\n");
      out.write("\t\t</header>\r\n");
      out.write("\r\n");
      out.write("\t\t<section id=\"content\">");
      out.write("\r\n");
      out.write("<section id=enroll-container>\r\n");
      out.write("\t<h2>회원 정보</h2>\r\n");
      out.write("\t<form id=\"memberUpdateFrm\" action=\"");
      out.print(request.getContextPath() );
      out.write("/member/deleteMember\" method=\"post\">\r\n");
      out.write("\t\t<table>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>아이디</th>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"memberId\" id=\"memberId_\" value=\"\" readonly>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>패스워드</th>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"password\" name=\"password\" id=\"password_\" value=\"\" required>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>패스워드확인</th>\r\n");
      out.write("\t\t\t\t<td>\t\r\n");
      out.write("\t\t\t\t\t<input type=\"password\" id=\"password2\" value=\"\" required><br>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr> \r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>이름</th>\r\n");
      out.write("\t\t\t\t<td>\t\r\n");
      out.write("\t\t\t\t<input type=\"text\"  name=\"memberName\" id=\"memberName\" value=\"\"  required><br>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>생년월일</th>\r\n");
      out.write("\t\t\t\t<td>\t\r\n");
      out.write("\t\t\t\t<input type=\"date\" name=\"birthDay\" id=\"birthDay\" value=\"\"><br>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr> \r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>이메일</th>\r\n");
      out.write("\t\t\t\t<td>\t\r\n");
      out.write("\t\t\t\t\t<input type=\"email\" placeholder=\"abc@xyz.com\" name=\"email\" id=\"email\" value=\"\"><br>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>휴대폰</th>\r\n");
      out.write("\t\t\t\t<td>\t\r\n");
      out.write("\t\t\t\t\t<input type=\"tel\" placeholder=\"(-없이)01012345678\" name=\"phone\" id=\"phone\" maxlength=\"11\" value=\"\" required><br>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>주소</th>\r\n");
      out.write("\t\t\t\t<td>\t\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" placeholder=\"\" name=\"address\" id=\"address\" value=\"\"><br>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>성별 </th>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t       \t\t <input type=\"radio\" name=\"gender\" id=\"gender0\" value=\"M\">\r\n");
      out.write("\t\t\t\t\t\t <label for=\"gender0\">남</label>\r\n");
      out.write("\t\t\t\t\t\t <input type=\"radio\" name=\"gender\" id=\"gender1\" value=\"F\">\r\n");
      out.write("\t\t\t\t\t\t <label for=\"gender1\">여</label>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<th>취미 </th>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" name=\"hobby\" id=\"hobby0\" value=\"운동\" ><label for=\"hobby0\">운동</label>\r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" name=\"hobby\" id=\"hobby1\" value=\"등산\" ><label for=\"hobby1\">등산</label>\r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" name=\"hobby\" id=\"hobby2\" value=\"독서\" ><label for=\"hobby2\">독서</label><br />\r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" name=\"hobby\" id=\"hobby3\" value=\"게임\" ><label for=\"hobby3\">게임</label>\r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" name=\"hobby\" id=\"hobby4\" value=\"여행\" ><label for=\"hobby4\">여행</label><br />\r\n");
      out.write("​\r\n");
      out.write("​\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("        <input type=\"button\" onclick=\"updateMember();\" value=\"정보수정\"/>\r\n");
      out.write("        <input type=\"button\" onclick=\"deleteMember();\" value=\"탈퇴\"/>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<script>\r\n");
      out.write("\tfunction updateMember(){\r\n");
      out.write("\t\t//password\r\n");
      out.write("\t\tvar $p1 = $(\"#password_\");\r\n");
      out.write("\t\tvar $p2 = $(\"#password2\");\r\n");
      out.write("\t\tif(/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test($p1.val()) == false){\r\n");
      out.write("\t\t\talert(\"유효한 패스워드를 입력하세요.\");\r\n");
      out.write("\t\t\t$p1.select();\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif($p1.val() != $p2.val()){\r\n");
      out.write("\t\t\talert(\"패스워드가 일치하지 않습니다.\");\r\n");
      out.write("\t\t\t$p1.select();\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//memberName\r\n");
      out.write("\t\tvar $memberName = $(\"#memberName\");\r\n");
      out.write("\t\tif(/^[가-힣]{2,}$/.test($memberName.val()) == false){\r\n");
      out.write("\t\t\talert(\"이름은 한글 2글자 이상이어야 합니다.\");\r\n");
      out.write("\t\t\t$memberName.select();\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//phone\r\n");
      out.write("\t\tvar $phone = $(\"#phone\");\r\n");
      out.write("\t\t//-제거하기\r\n");
      out.write("\t\t$phone.val($phone.val().replace(/[^0-9]/g, \"\"));//숫자아닌 문자(복수개)제거하기\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif(/^010[0-9]{8}$/.test($phone.val()) == false){\r\n");
      out.write("\t\t\talert(\"유효한 전화번호가 아닙니다.\");\r\n");
      out.write("\t\t\t$phone.select();\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$(\"#memberUpdateFrm\").attr(\"method\",\"GET\").submit();\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction deleteMember(){\r\n");
      out.write("\t\t$(\"#memberUpdateFrm\").submit();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t//값 채워주기\r\n");
      out.write("\t\t$(\"#memberId_\").val(\"");
      out.print( loginMember.getMemberId() );
      out.write("\");\r\n");
      out.write("\t\t$(\"#password_\").val(\"");
      out.print( loginMember.getPassword() );
      out.write("\");\r\n");
      out.write("\t\t$(\"#password2\").val(\"");
      out.print( loginMember.getPassword() );
      out.write("\");\r\n");
      out.write("\t\t$(\"#memberName\").val(\"");
      out.print( loginMember.getMemberName() != null ? loginMember.getMemberName() : "");
      out.write("\");\r\n");
      out.write("\t\t$(\"#birthDay\").val(\"");
      out.print( loginMember.getBirthday() != null ? loginMember.getBirthday() : "");
      out.write("\")\r\n");
      out.write("\t\t$(\"#email\").val(\"");
      out.print( loginMember.getEmail() != null ? loginMember.getEmail() : "" );
      out.write("\")\r\n");
      out.write("\t\t$(\"#phone\").val(\"");
      out.print( loginMember.getPhone() );
      out.write("\")\r\n");
      out.write("\t\t$(\"#address\").val(\"");
      out.print( loginMember.getAddress() != null ? loginMember.getAddress() : "");
      out.write("\")\r\n");
      out.write("\t\t");
 if(loginMember.getGender().equals("M")){ 
      out.write("\r\n");
      out.write("\t\t\t$(\"#gender0\").prop(\"checked\", true);\r\n");
      out.write("\t\t");
}else{
      out.write("\r\n");
      out.write("\t\t\t$(\"#gender1\").prop(\"checked\", true);\r\n");
      out.write("\t\t");
}
      out.write("\r\n");
      out.write("\t\t");
 
		String[] hobbies = loginMember.getHobby().split("[,]");
		System.out.println(Arrays.toString(hobbies));
		if(hobbies.length!=0){
			for(int i=0; i<hobbies.length; i++){
				if(hobbies[i].equals("운동")){
      out.write("\r\n");
      out.write("\t\t\t\t\t$(\"#hobby0\").prop(\"checked\", true);\r\n");
      out.write("\t\t\t\t");
}
				else if(hobbies[i].equals("등산")){
      out.write("\r\n");
      out.write("\t\t\t\t\t$(\"#hobby1\").prop(\"checked\", true);\r\n");
      out.write("\t\t\t\t");
}
				else if(hobbies[i].equals("독서")){
      out.write("\r\n");
      out.write("\t\t\t\t\t$(\"#hobby2\").prop(\"checked\", true);\r\n");
      out.write("\t\t\t\t");
}
				else if(hobbies[i].equals("게임")){
      out.write("\r\n");
      out.write("\t\t\t\t\t$(\"#hobby3\").prop(\"checked\", true);\r\n");
      out.write("\t\t\t\t");
}
				else if(hobbies[i].equals("여행")){
      out.write("\r\n");
      out.write("\t\t\t\t\t$(\"#hobby4\").prop(\"checked\", true);\r\n");
      out.write("\t\t\t\t");
}
			}
		}
		
      out.write("\r\n");
      out.write("\t});\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("</section>\r\n");
      out.write("\r\n");
      out.write("    \t</section>\r\n");
      out.write("    \r\n");
      out.write("\t\t<footer>\r\n");
      out.write("\t\t\t<p>&lt;Copyright 1998-2021 <strong>KH정보교육원</strong>. All rights reserved.&gt;</p>\r\n");
      out.write("\t\t</footer>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
