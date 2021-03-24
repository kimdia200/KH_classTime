<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String main = request.getParameter("main_menu");
	String side = request.getParameter("side_menu");
	String drink = request.getParameter("drink_menu");
	int sum = (int) request.getAttribute("sum");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 결과창</title>
<style type="text/css">
#main {
	font-size: 3em;
	color: blue;
}

#side {
	font-size: 2em;
	color: purple;
}

#drink {
	color: green;
}

#sum {
	font-size: 3em;
	color: brown;
}
</style>
</head>
<body>
	<h1>감사합니다.</h1>
	<div>
		<span id='main'><%=main%></span>,<span id='side'><%=side%></span>,<span
			id='drink'><%=drink%></span>을/를 주문하셨습니다.
	</div>
	<div>
		총 결제금액은<span id='sum'><%=sum %>원</span>입니다.
	</div>
</body>
</html>