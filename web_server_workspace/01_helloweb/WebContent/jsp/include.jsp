<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="/jsp/header.jsp" %>
	<section>
		<h1>Content1</h1>
		<p><%=name %>님, 반갑습니다.</p>
		<a href="/web/jsp/another.jsp">another.jsp</a>
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Unde omnis nobis laudantium recusandae eligendi hic quisquam quod voluptatem adipisci excepturi cum sit architecto ipsum praesentium itaque. Pariatur voluptatum architecto sit!</p>
	</section>
	<script>
		$(function(){
			$("h1").css("color", "deeppink");
		})();
	</script>
<%@ include file="/jsp/footer.jsp" %>
