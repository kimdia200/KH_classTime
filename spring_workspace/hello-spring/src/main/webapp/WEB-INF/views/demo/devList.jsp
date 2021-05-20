<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev 목록" name="title"/>
</jsp:include>
<table class="table w-75 mx-auto">
    <tr>
      <th scope="col">번호</th>
      <th scope="col">이름</th>
      <th scope="col">경력</th>
      <th scope="col">이메일</th>
      <th scope="col">성별</th>
      <th scope="col">개발가능언어</th>
      <th scope="col">수정 | 삭제</th>
    </tr>
    <c:forEach items="${list}" var="dev">
    <tr>
      <td scope="row">${dev.no}</td>
      <td>${dev.name}</td>
      <td>${dev.career}</td>
      <td>${dev.email}</td>
      <td>${dev.gender}</td>
      <td>
      	<c:forEach items="${dev.lang}" var="lang" varStatus="vs">
      		<%-- ${lang}<c:if test="${!vs.last}">, </c:if> --%>
      		${lang}${vs.last ? "": ", "}
      	</c:forEach>
      </td>
      <td>
	      <button name="up" class="btn btn-outline-secondary" onclick="updateDev(this);" data-no="${dev.no}">수정</button>
	      <button name="de" class="btn btn-outline-danger" onclick="deleteDev(this);" data-no="${dev.no}">삭제</button>
      </td>
    </tr>
    </c:forEach>
</table>
<form name="hiddenFrm">
	<input id="hi" type="hidden" name="no" />
</form>
<script>
	function updateDev(btn){
	//GET /demo/updateDev.do?no=123 ---> devUpdateForm.jsp
	//Post /demo/updateDev.do ---> redirect:/demo/devList.do
		var no = $(btn).data("no");
		$("#hi").val(no);
		$(document.hiddenFrm)
		.attr("action", "${pageContext.request.contextPath}/demo/updateDev.do")
		.attr("method","GET")
		.submit();
	}
	function deleteDev(btn){
	//POST /demo/deleteDev.do --->redirect:/demo/devList.do
		var no = $(btn).data("no");
		$("#hi").val(no);
		$(document.hiddenFrm)
		.attr("action", "${pageContext.request.contextPath}/demo/deleteDev.do")
		.attr("method","POST")
		.submit();
	}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
