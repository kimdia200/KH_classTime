<%-- page 지시어 directive --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.io.*"%>
<%
	//jsp 스크립팅 요소 : scriptlet
	int sum = 0;
	for(int i = 1; i <= 10; i++)
		sum += i;
	
	//server - side 현재 밀리초
	long mil = new Date().getTime();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp - basic</title>
<script>
window.onload = function(){
	//합계 구하기
	var sum=0;
	for(var i=1; i<=10; i++){
		sum+=i;
	}
	document.querySelector("#sum").innerText = sum;
	
	//현재 밀리초 구하기
	var mil = new Date().getTime();
	document.querySelector("#now").innerText = mil;
	
};
</script>

</head>
<body>
	<h1>Basic</h1>
	<!-- [html주석]  아래 결과값들은 모두 같지만 처리 방식은 모두 다름!!! -->
	<p>java로 계산된 결과 : <%= sum %></p><%-- jsp스크립팅 요소 : 출력식 --%>
	<p>java로 계산된 결과 : <% out.print(sum); %></p>
	
	<p>client - side : javaScript로 계산된 결과 : <span id="sum"></span></p>
	
	<hr>
	
	<p>server - side : 현재시각(밀리초) <%= mil%></p>
	<p>client - side : 현재시각(밀리초) <span id="now"></span></p>
	
</body>
</html>