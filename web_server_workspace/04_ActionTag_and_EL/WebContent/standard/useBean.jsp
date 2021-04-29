<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	해당 스코프에서 id와 동일한 속성명으로 저장된 객체를  가져온다.
	존재하지 않으면, 해당타입의 객체를 하나 생성한다.
	
	OGNL Object Graph Navigation Language
	
	property 접근방식
	-getter : getter에서 get을 제외하고 소문자로 시작하는 이름
	-setter : setter에서 set을 제외하고 소문자로 시작하는 이름
 -->
<jsp:useBean id="kimdia200" class="com.kh.person.model.vo.Person" scope="application"></jsp:useBean>
<jsp:useBean id="sinsa" class="com.kh.person.model.vo.Person"></jsp:useBean>
<jsp:setProperty property="id" value="sssinsa" name="sinsa"/>
<jsp:setProperty property="name" value="씬사임당" name="sinsa"/>
<jsp:setProperty property="gender" value="여" name="sinsa"/>
<jsp:setProperty property="age" value="50" name="sinsa"/>
<jsp:setProperty property="married" value="true" name="sinsa"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>useBean</title>
<style>
	table,th,td{
		border:1px solid #000;
		padding:5pd;
		margin-bottom:20px;
	}
</style>
</head>
<body>
	<h1>user</h1>
	<table>
		<tr>
			<th>id</th>
			<%-- property 속성값은 vo 객체의 getter에서 get을 제외하고 소문자로 시작하는 나머지 이름 --%>
			<td><jsp:getProperty property="id" name="kimdia200"/></td>
		</tr>
		<tr>
			<th>name</th>
			<td><jsp:getProperty property="name" name="kimdia200"/></td>
		</tr>
		<tr>
			<th>gender</th>
			<td><jsp:getProperty property="gender" name="kimdia200"/></td>
		</tr>
		<tr>
			<th>age</th>
			<td><jsp:getProperty property="age" name="kimdia200"/></td>
		</tr>
		<tr>
			<th>married</th>
			<td><jsp:getProperty property="married" name="kimdia200"/></td>
		</tr>
	</table>
	
	<h1>sinsa</h1>
	<table>
		<tr>
			<th>id</th>
			<%-- property 속성값은 vo 객체의 getter에서 get을 제외하고 소문자로 시작하는 나머지 이름 --%>
			<td><jsp:getProperty property="id" name="sinsa"/></td>
		</tr>
		<tr>
			<th>name</th>
			<td><jsp:getProperty property="name" name="sinsa"/></td>
		</tr>
		<tr>
			<th>gender</th>
			<td><jsp:getProperty property="gender" name="sinsa"/></td>
		</tr>
		<tr>
			<th>age</th>
			<td><jsp:getProperty property="age" name="sinsa"/></td>
		</tr>
		<tr>
			<th>married</th>
			<td><jsp:getProperty property="married" name="sinsa"/></td>
		</tr>
	</table>
</body>
</html>