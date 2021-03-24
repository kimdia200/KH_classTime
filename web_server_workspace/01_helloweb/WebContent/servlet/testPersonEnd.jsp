<%-- 
	jsp주석  
	jsp : java + html
	jsp의 모든 자바코드 <%..%>는 모두 서버단에서 처리되고, 그 결과만 html에 반영된다.
--%>
<%@ page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//jsp scriptlet 자바 공간임
	System.out.println(123);

	//사용자 입력값 가져오기
	//request, response에 선언 없이!!! 접근가능
	String name = request.getParameter("name");
	String color = request.getParameter("color");
	String animal = request.getParameter("animal");
	String[] foodArr = request.getParameterValues("food");
	
	System.out.println("name@jsp = "+ name);
	System.out.println("color@jsp = "+ color);
	System.out.println("animal@jsp = "+ animal);
	//java.lang 패키지를 제외한 다른 패키지는 임포트 시켜줘야한다.
	//상단에 java.util.Arrays 가 임포트된걸 볼 수 있다.
	System.out.println("foodArr@jsp = "+ Arrays.toString(foodArr));
	
	//저장된 속성(attribute가져오기)
	//Object로 전송된걸 받았기때문에 형변환 필수
	String recommendation = (String)request.getAttribute("recommendation");
	String colorName = (String)request.getAttribute("colorName");
%>
<!DOCTYPE html>
<html>
<head>
<title>개취 검사 결과</title>
<style>
.recommendation {
	font-size: 2em;
	color: <%= colorName %>;
	text-decoration: underline;
}
</style>
</head>
<body>
	<h1>개인 취향 검사 결과 jsp</h1>
	<!-- 출력식 안에서는 세미콜론을 찍지 않는다 -->
	<p><%= name %>님의 개인취향 검사 결과는</p>
	<p><%= color %>을 좋아합니다.</p>
	<p>좋아하는 동물은 <%=animal %>입니다.</p>
	<% if(foodArr != null) { %>
	<p>좋아하는 음식은 <%=Arrays.toString(foodArr) %>입니다.</p>
	<%} else{ %>
	<p>좋아하는 음식은 없습니다.</p>
	<%} %>
	<hr>
	<p class='recommendation'>오늘은 <%=recommendation %> 어떠세요?</p>
</body>
</html>