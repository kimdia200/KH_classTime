<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="no1" value="${param.no1}" scope="page"/>
<c:set var ="no2" value="${param.no2}" scope="page"/>

<%-- <%
	Object no2 = 200;
	pageContext.setAttribute("no2", no2);
	//위의 c:set으로 설정한 no1하고 같은 타입!
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Core Basics</title>
</head>
<body>
	<h1>JSTL</h1>
	<p>Jsp Standard Tag Library</p>
	<p>표준이라고 써있지만 표준이 아니라 따로 jar파일 첨부해야함</p>
	<p><c:out value="${no1}"/></p>
	<p><c:out value="${no2}"/></p>
	<p>[<c:out value="${no1+no2}"/>] ${no1+no2}</p>
	
	<h2>조건식</h2>
	<!-- else 없음 -->
	<c:if test="${Integer.parseInt(no1)>Integer.parseInt(no2)}">
		${no1} > ${no2}
	</c:if>
	<c:if test="${Integer.parseInt(no1) < Integer.parseInt(no2)}">
		${no1} &lt; ${no2}
	</c:if>
	<c:if test="${Integer.parseInt(no1)==Integer.parseInt(no2)}">
		${no1} eq ${no2}
	</c:if>
	<c:set var="rnd" value="<%=Math.floor(Math.random()*100) %>"/>
	<p>${rnd}</p>
	<p>
		<c:choose>
			<c:when test="${rnd%5==0}">인형을 뽑았습니다.</c:when>
			<c:when test="${rnd%5==1}">권총을 뽑았습니다.</c:when>
			<c:otherwise>꽝입니다.</c:otherwise>
		</c:choose>
	</p>
	
	<script>
		console.log(${rnd});
	</script>
	
	<h2>반복문</h2>
	<!-- begin은 무조건 end보다 작아야함 증가해야한다, step은 0 보다 작을수없음 -->
	<c:forEach var="i" begin="1" end="6" step="1">
		<!-- h1~h6 -->
		<h${i}>Hello World${i}</h${i}>
		<!-- h6~h1 -->
<%-- 		<h${7-i}>Hello World</h${7-i}> --%>
	</c:forEach>
	
	<c:set var="list" value='<%= Arrays.asList("홍길동", "신사임당", "이순신") %>'/>
	<c:forEach items="${list}" var="name" varStatus="vs">
		<p>${vs.index} ${vs.count} -  ${name}</p>
	</c:forEach>
	
</body>
</html>