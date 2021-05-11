<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>maven - mybatis</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
</head>
<script>
$(()=>{
	$("#btn-gson-test").click((e) => {
		$.ajax({
			url:"${pageContext.request.contextPath}/gson.do",
			method:"GET",
			dataType:"json",
			success:(data)=>{
				console.log(data);
			},
			error:(xhr, statusText, err)=>{
				console.log(xhr, statusText, err);
			}
		});
	});
});
</script>
<body>
	<h1>Hello Maven</h1>
	<button id="btn-gson-test">gson test</button>
	<h1>Hello MyBatis</h1>
	<h2>student</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/student/insertStudent.do">/student/insertStudent.do</a></li>
		<li><a href="${pageContext.request.contextPath}/student/selectOne.do">/student/selectOne.do</a></li>
		<li><a href="${pageContext.request.contextPath}/student/selectList.do">/student/selectList.do</a></li>
	</ul>
	<h2>employee</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/emp/search1.do">/emp/search1.do</a></li>
		<li><a href="${pageContext.request.contextPath}/emp/search2.do">/emp/search2.do</a></li>
		<li><a href="${pageContext.request.contextPath}/emp/search3.do">/emp/search3.do</a></li>
	</ul>
</body>
</html>