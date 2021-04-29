<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/standard/header.jsp">
	<jsp:param value="ANOTHER" name="title"/>
</jsp:include>


		<article>
			<h2>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusamus consectetur consequatur non animi nisi totam eos optio exercitationem earum reprehenderit ipsa officia reiciendis maiores autem dolorum odit dolorem deserunt quos!</h2>
			<a href="${pageContext.request.contextPath }/standard/include.jsp">include</a>
		</article>
<jsp:include page="/standard/footer.jsp"></jsp:include>