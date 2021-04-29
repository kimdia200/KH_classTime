<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String life="life is very short!";
	String movie = "노인을 위한 나라는 없다";
	pageContext.setAttribute("life", life);
	pageContext.setAttribute("movie", movie);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>El Basics</title>
</head>
<body>
	<h1>El Basics</h1>
	<%-- scope생략시  pageScope - requestScope - sessionScope - application Scope순으로 뒤짐 --%>
	<p>${life}</p>
	<p>${coffee}</p>
	<p>${serverTime}</p>
	<p>${honngd}</p>
	<p>${honngd.id}</p>
	<p>${honngd.name}</p>
	<p>${honngd.gender}</p>
	<p>${honngd.age}</p>
	<p>${honngd.married}</p>
	<p>${book}</p>
	<p>${movie}</p>
	<p>${applicationScope.movie}</p>
	<%-- 
		EL은 NullPointerException을 유발하지 않는다.
		null인 경우에는 ""출력  
	--%>
	<p>[${cow.run}]</p>
	
	<h2>list</h2>
	<p>${list}</p>
	<p>${list[0]}</p>
	<p>${list[1]}</p>
	<p>${list[2]}</p>
	<p>${list[3]}</p>
	<p>${list[4]}</p>


	<h2>map</h2>
	<p>${map}</p>
	<p>${map.language}</p>
	<p>[${map.Dr.zang}]</p>
	<p>${map['Dr.zang']}</p>
	<p>${map['Dr.zang'].name}</p>
	<p>${map['Dr.zang']["name"]}</p>
	
	<h2>param</h2>
	<p>${param.pname }</p>
	<p>${param.pcount }</p>
	<p>${paramValues.option[0]}</p>
	<p>${paramValues.option[1]}</p>
	
	
	<h2>cookie</h2>
	<p>${cookie.JSESSIONID}</p>
	<p>${cookie.JSESSIONID.value}</p>
	
	<h1>header</h1>
	<p>${header}</p>
	<p>${header.accept}</p>
	<p>${header['user-agent']}</p>
	
	<h1>pageContext</h1>
	<!-- 
		getPage()
		getRequest()
			getMethod(): GET|POST
			getContextPath(): /action
		getResponse()
		getSession()
		getServletContext()
		getErrorData()
	 -->
	 <p>${pageContext.request.method}</p>
	 <p>${pageContext.request.contextPath}</p>
	 
	
	
	
	
	
	
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	
</body>
</html>