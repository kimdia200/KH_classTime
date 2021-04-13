<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello Ajax</h1>
	
	<h2>text</h2>
	<ul>
		<li><a href="<%=request.getContextPath()%>/text/text.jsp">text</a></li>
		<li><a href="<%=request.getContextPath()%>/text/autocomplete.jsp">autocomplete</a></li>
	</ul>
	
	<h2>HTML</h2>
	<ul>
		<li><a href="<%=request.getContextPath()%>/html/html.jsp">html</a></li>
	</ul>
	
	<h2>xml</h2>
	<ul>
		<li><a href="<%=request.getContextPath()%>/xml/xml.jsp">xml</a></li>
	</ul>
	
	<h2>json</h2>
	<ul>
		<li><a href="<%=request.getContextPath()%>/json/json.jsp">json</a></li>
	</ul>
	
	<h2>homework</h2>
	<ul>
		<li><a href="<%=request.getContextPath()%>/homework/smart.jsp">homework</a></li>
	</ul>
</body>
</html>