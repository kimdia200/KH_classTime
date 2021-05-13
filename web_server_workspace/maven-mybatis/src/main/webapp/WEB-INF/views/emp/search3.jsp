<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	//사용자 요청값(String[])을 contains메소드사용을 위해 List로 변환.
	String[] jobCodeArr = request.getParameterValues("jobCode");
	List<String> jobCodeList = 
				jobCodeArr != null ? 
						Arrays.asList(jobCodeArr) : 
							null;
	pageContext.setAttribute("jobCodeList", jobCodeList);
	
	List<String> deptIdList = (List<String>)request.getAttribute("deptIdList");
				
%>
<fmt:setLocale value="ko_kr"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis실습</title>
<style>
div#emp-container{text-align:center;}
table.tbl-emp{
	margin:0 auto;
	border:1px solid; 
	border-collapse:collapse;
}
table.tbl-emp th, table.tbl-emp td{
	border:1px solid;
	padding:5px;
	background:lightseagreen;
}
div#search-container{
	padding:15px 0;
}
input#btn-search { width: 350px; background: lightslategray; color: white; box-shadow: 0px 3px 15px grey; }
table#tbl-search { margin:0 auto; }
table#tbl-search th,table#tbl-search td {padding:5px 15px;}
table#tbl-search td {text-align:left;}
</style>
</head>
<body>
<div id="emp-container">
	<h2>사원정보 </h2>
	
	<div id="search-container">
		<form name="empSearchFrm" >
			<h3>검색</h3>
			<input type="button" value="초기화" />
			<table id="tbl-search">
				<!-- 직급조회 -->
				<tr>
					<th>직급</th>
					<td>
						<c:forEach items="${jobList}" var="map">
						<input 
							type="checkbox" name="jobCode" 
							id="${map.jobCode}" value="${map.jobCode}" 
							${jobCodeList.contains(map.jobCode) ? 'checked' : ''}/>
						<label for="${map.jobCode}">${map.jobName}</label>					
						</c:forEach>
					</td>
				</tr>
					
				<!-- @실습문제 : 부서 조회(직급조회와 모두 일치하는 사원) 
					input:checkbox+label 는 3개마다 개행할 것.
					(심화)인턴사원(D0)도 조회될 수 있도록 할것.
				-->
				<tr>
					<th>부서</th>
					<td>
					<c:forEach items="${deptList}" var="dept" varStatus="vs">
						<input type="checkbox" name="deptCode" id="${dept.deptId }" value="${dept.deptId }" ${deptIdList.contains(dept.deptId) ? 'checked' : '' }/>
						<label for="${dept.deptId }">${dept.deptTitle }</label>
						<c:if test="${vs.count %3 eq 0}"></br></c:if>
					</c:forEach>
						<input type="checkbox" name="deptCode" id="D0" value="D0" />
						<label for="D0">인턴</label>
					</td>
				</tr>
						
				<tr>
					<th colspan="2">
						<input type="submit" id="btn-search" value="검색"  />
					</th>
				</tr>
			</table>
		</form>
	</div>
	
	
	
	<table class="tbl-emp">
		<tr>
			<th></th><!-- 1부터 넘버링 처리 -->
			<th>사번</th>
			<th>사원명</th>
			<th>주민번호</th><!--뒷6자리는 ******처리-->
			<th>성별</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>부서명</th>
			<th>직급명</th>
			<th>급여레벨</th>
			<th>급여</th><!--원화기호, 세자리마다 콤마표시-->
			<th>보너스율</th><!--percent로 표시-->
			<th>매니져 사번</th>
			<th>입사일</th><!--날짜형식 yyyy/MM/dd-->
			<th>퇴사여부</th>
		</tr>
		<!-- 조회된 데이터가 있는 경우와 없는 경우를 분기처리 하세요 -->
		<c:if test="${empty list }">
		<%-- 조회된 데이터가 없는 경우 --%>
		<tr>
			<th colspan="15" style="text-align:center;">조회된 데이터가 없습니다.</th>
		</tr>
		</c:if>
		
		<c:if test="${not empty list }">
		<%-- 조회된 데이터가 있는 경우 --%>
		<c:forEach items="${list}" var="emp" varStatus="vs">
		<tr>
			<td>${vs.count}</td>
			<td><a href="${pageContext.request.contextPath}/emp/updateEmp.do?empId=${emp['EMP_ID']}">${emp['EMP_ID']}</a></td>
			<td>${emp['EMP_NAME']}</td>
			<td>${fn:substring(emp['EMP_NO'], 0, 8)}******</td>
			<td>${emp['GENDER']}</td>
			<td>${emp['EMAIL']}</td>
			<td>${emp['PHONE']}</td>
			<td>${emp['DEPT_TITLE']}</td>
			<td>${emp['JOB_NAME']}</td>
			<td>${emp['SAL_LEVEL']}</td>
			<td><fmt:formatNumber value="${emp['SALARY']}" type="currency"/></td>
			<td><fmt:formatNumber value="${emp['BONUS']}" type="percent"/> </td>
			<td>${emp['MANAGER_ID']}</td>
			<td><fmt:formatDate value="${emp['HIRE_DATE']}" pattern="yyyy/MM/dd"/></td>
			<td>${emp['QUIT_YN']}</td>
		</tr>
		</c:forEach>
		</c:if>
		
	</table>
</div>

</body>
</html>