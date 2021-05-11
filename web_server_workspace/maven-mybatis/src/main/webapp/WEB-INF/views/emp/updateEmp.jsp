<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원정보 수정 :: 동적쿼리 set | trim</title>
<!-- 최신 jquery cdn 사용하기 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
div.wrapper{
	text-align: center;
}
div.update-wrapper{
	background: lightgray;
	width: 500px;
	padding: 20px;
	margin: 0 auto;
}

table#emp{
	margin: 5px auto;
	border-collapse: collapse;
	border: 1px solid;
}
table#emp th, table#emp td{
	border: 1px solid;
}

</style>
</head>
<body>
<div class="wrapper">
<h2>사원정보 수정 :: 동적쿼리 set | trim</h2>

<%-- 조회한 사번이 존재하는 경우 --%>
	<table id="emp">
		<tr>
			<th>사번</th>
			<td>${emp['EMP_ID']}</td>
		</tr>
		<tr>
			<th>사원명</th>
			<td>${emp['EMP_NAME']}</td>
		</tr>
		<tr>
			<th>직급</th>
			<td>${emp['JOB_NAME']}</td>
		</tr>
		<tr>
			<th>부서</th>
			<td>${emp['DEPT_TITLE']}</td>
		</tr>
	</table>
	<hr />
	<div class="update-wrapper">
		<form action="${pageContext.request.contextPath }/emp/updateEmp.do" method="post">
			<input type="hidden" name="empId" value="${emp['EMP_ID']}" />
		   	직급: 
		    <select name="jobCode">
		    	<option value="">선택</option>
		    <c:forEach items="${jobList}" var="job">
		    	<option value="${job.jobCode}">${job.jobName}</option>
		    </c:forEach>
			</select>
		   	부서: 
		    <select name="deptCode">
		    	<option value="">선택</option>
		    <c:forEach items="${deptList}" var="dept">
		    	<option value="${dept.deptId}">${dept.deptTitle}</option>
		    </c:forEach>
			</select>

		    <input type="submit" value="수정" />
		</form>
	</div>

	
</div>
<script>
function empUpdateValidate(){
	//아무것도 입력하지 않은 경우, 전송하지 않는다.

	return true;
}
</script>
</body>
</html>
