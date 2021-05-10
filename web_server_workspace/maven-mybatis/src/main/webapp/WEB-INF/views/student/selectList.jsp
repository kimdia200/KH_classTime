<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis실습 - selectList</title>
<style>
div#student-container{text-align:center;}
table.tbl-student{margin:0 auto;border:1px solid; border-collapse:collapse;}
table.tbl-student th,table.tbl-student td{
	border:1px solid;
	padding:5px;
}
</style>
</head>
<body>
<div id="student-container">
	<h2>selectList</h2>
	<p>SqlSession의 selectList메소드를 호출해서 List&lt;Student>를 리턴받음.</p>
	
	<hr />
	
	<h4>List테이블</h4>
	<table class="tbl-student">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>등록일</th>
		</tr>
		<c:if test="${empty list }">
			<tr>
				<td colspan="4">등록된 학생이 존재하지 않습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${list}" var="std">
			<tr>
				<td>${std.no}</td>
				<td>${std.name}</td>
				<td>${std.tel }</td>
				<td> <fmt:formatDate value="${std.regDate}" pattern="yy-MM-dd HH:mm"/> </td>
			</tr>
		</c:forEach>
	</table>
	<hr />
	
	<h4>List map테이블</h4>
	<table class="tbl-student">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>등록일</th>
		</tr>
		<c:if test="${empty mapList }">
			<tr>
				<td colspan="4">등록된 학생이 존재하지 않습니다.</td>
			</tr>
		</c:if>
		<c:forEach items="${mapList}" var="map">
			<tr>
				<td>${map.no}</td>
				<td>${map.name}</td>
				<td>${map.tel }</td>
				<td> <fmt:formatDate value="${map.regDate}" pattern="yy-MM-dd HH:mm"/> </td>
			</tr>
		</c:forEach>
	</table>
</div>
	
	
</body>
</html>
