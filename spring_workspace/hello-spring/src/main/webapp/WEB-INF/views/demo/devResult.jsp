<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev 등록결과" name="title" />
</jsp:include>

<table class="table w-50 mx-auto" >
	<tr>
		<th scope="col">이름</th>
		<td>${dev.name}</td>
	</tr>
	<tr>
		<th scope="col">경력</th>
		<td>${dev.career}</td>
	</tr>
	<tr>
		<th scope="col">이메일</th>
		<td>${dev.email}</td>
	</tr>
	<tr>
		<th scope="col">성별</th>
		<td>${dev.gender}</td>
	</tr>
	<tr>
		<th scope="col">개발언어</th>
		<td>
			<c:forEach items="${dev.lang}" var="lang" varStatus="vs">
				${lang}${vs.last ? "" : ","}
			</c:forEach>
		</td>
	</tr>
</table>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
