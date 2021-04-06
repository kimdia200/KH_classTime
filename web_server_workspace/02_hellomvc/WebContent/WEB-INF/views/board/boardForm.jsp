<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<script>
$(document).ready(function(){
	$(document.boardEnrollFrm).submit(boardValidate);
	/**
	* boardEnrollFrm 유효성 검사
	*/
	function boardValidate(){
		//제목을 작성하지 않은 경우 폼제출할 수 없음.
		if($("[name=title]").html==null){
			alert("제목 미작성");
			return false;
		}   
		//내용을 작성하지 않은 경우 폼제출할 수 없음.
		if(/^.{1,}$/.test($("[name=content]").val())==false){
			alert("내용 미작성");
			return false;
		}   
		return true;
	}
})
</script>

<section id="board-container">
<h2>게시판 작성</h2>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/board/boardEnroll" 
	method="post"
	enctype="multipart/form-data">
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" required></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="writer" value="<%= loginMember.getMemberId() %>" readonly/>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>			
			<input type="file" name="upFile">
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="40" name="content"></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="등록하기">
		</th>
	</tr>
</table>
</form>

</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
