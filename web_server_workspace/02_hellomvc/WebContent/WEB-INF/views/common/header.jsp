<%@page import="member.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<!-- 아래와 같이 작성시 /mvc 라는 경로가 변경되면 문제가 발생 할 수 있으므로 JSP로 현재 어플리케이션 경로를 가져옴 -->
<!-- <script src="/mvc/js/jquery-3.6.0.js"></script> -->
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
<script>
	<% if(msg!=null) {%>
		alert("<%= msg%>");
	<%}%>
	<% if(signUpLog!=null) {%>
	alert("<%= signUpLog%>");
<%}%>
	
	<%--
		로그인 실패를 했을때는 loc라는 값을 attribute에 저장 하는데
		loc가 존재하면 로그인에 실패 했다는 것이므로
		현재 페이지에 머물지 못하고 이전 페이지로 되돌려 보내버림
	--%>
	<%-- <% if(loc!=null) {%>
		location.href = "<%=loc%>";
	<%}%> --%>
	
	
	$(function(){
		$('#loginFrm').submit(function(){
			var $memberId = $(memberId);
			var $password = $(password);
			
			if(/^.{4,}$/.test($memberId.val())==false){
				alert("유효한 아이디를 입력하세요.");
				$memberId.select();
				return false;
			}
			
			if(/^.{4,}$/.test($password.val())==false){
				alert("유효한 비밀번호를 입력하세요.")
				$password.select();
				return false;
			}
			return true;
		});
	});
	
	
</script>
</head>
<body>
	<div id="container">
		<header>
			<h1>Hello MVC</h1>
			<!-- 로그인폼 시작 -->
			<div class="login-container">
			<%
				//로그인 실패시
				if(loginMember==null){
			%>
				<form id="loginFrm" action="<%=request.getContextPath()%>/member/login" method="post">
					<table>
						<tr>
							<!-- table 구조상 탭키시 이동하는 방향은 text:id -> submit -> text:pwd이지만 -->
							<!-- tabindex 속성을 사용해서 내가 원하는대로 조정 해줄 수 있다. -->
							
							<td><input type="text" name="memberId" id="memberId"
								placeholder="아이디" tabindex="1" value="<%= saveId != null ? saveId : ""%>"></td>
							<td><input type="submit" value="로그인" tabindex="3"></td>
						</tr>
						<tr>
							<td><input type="password" name="password" id="password"
								placeholder="비밀번호" tabindex="2"></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2"><input type="checkbox" name="saveId"
								id="saveId" <%= saveId != null ? "checked" : "" %>/> <label for="saveId">아이디저장</label>&nbsp;&nbsp; <input
								type="button" value="회원가입" onclick="location.href='<%= request.getContextPath() %>/member/memberEnroll';"></td>
						</tr>
					</table>
				</form>
				<!-- 로그인폼 끝-->
			<%
				//로그인 성공시
				}else{
			%>
				<%-- 로그인 성공시 --%>
				<table id="login">
					<tr>
						<td><%= loginMember.getMemberName() %>님, 안녕하세요.</td>
					</tr>
					<tr>
						<td> 
							<input type="button" value="내정보보기" />
							<input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath()%>/member/logout';"/>
						</td>
					</tr>
				</table>
			<%} %>
			</div>
			<!-- 메인메뉴 시작 -->
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="<%=request.getContextPath()%>">Home</a></li>
					<li class="notice"><a href="#">공지사항</a></li>
					<li class="board"><a href="#">게시판</a></li>
				</ul>
			</nav>
			<!-- 메인메뉴 끝-->
		</header>

		<section id="content">