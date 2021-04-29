<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!-- isErrorPage = "true"로 지정하면, 던져진 예외 객체에 exception키워드로 선언없이 접근 가능하다 
	web.xml 에서 어떤에러가 뜰때 이페이지를 표시할건지 정해줄수 있다.
		-->
<%@ include file="/WEB-INF/views/common/header.jsp" %>
		
	<h1><%= exception.getMessage() %></h1>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
