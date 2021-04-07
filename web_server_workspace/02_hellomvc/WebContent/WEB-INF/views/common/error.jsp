<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!-- isErrorPage = "true"로 지정하면, 던져진 예외 객체에 exception키워드로 선언없이 접근 가능하다 
	web.xml 에서 어떤에러가 뜰때 이페이지를 표시할건지 정해줄수 있다.
		-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error</title>
</head>
<body style="text-align: center">
	<h1>이용에 불편을 드려 죄송합니다.</h1>
	<p style="color: red;"><%=exception.getMessage() %></p>
</body>
</html>