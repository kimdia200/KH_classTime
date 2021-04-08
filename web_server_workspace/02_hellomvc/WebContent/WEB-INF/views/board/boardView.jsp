<%@page import="board.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% Board board = (Board)request.getAttribute("board");
   List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");
%>
<% boolean editable = loginMember != null && (loginMember.getMemberId().equals(board.getWriter()) || "A".equals(loginMember.getMemberRole())); %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<h2>게시판</h2>
	<table id="tbl-board-view">
		<tr>
			<th>글번호</th>
			<td><%= board.getNo() %></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%= board.getTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= board.getWriter() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%= board.getReadCount() %></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<% if(board.getAttach() != null) {%>
				<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
				<a href="<%= request.getContextPath()%>/board/fileDownload?no=<%=board.getNo()%>"><%= board.getAttach().getOriginalFileName() %></a>
				<% } %>
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td> <%= board.getContent() %></td>
		</tr>
			<% if(editable) {%>
		<tr>
			<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
			<th colspan="2">
				<input type="button" value="수정하기" onclick="updateBoard()">
				<input type="button" value="삭제하기" onclick="deleteBoard()">
			</th>
		</tr>
			<% } %>
	</table>
		<hr style="margin-top:30px;" />	
	<div class="comment-container">
        <div class="comment-editor"> 
            <form action="<%=request.getContextPath()%>/board/boardCommentInsert" method="post" name="boardCommentFrm">
                <input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
                <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "공란" %>" />
                <input type="hidden" name="commentLevel" value="1" />
                <input type="hidden" name="commentRef" value="0" />    
				<textarea name="content" cols="60" rows="3"></textarea>
                <button type="submit" id="btn-insert">등록</button>
            </form>
        </div>
		<!--table#tbl-comment-->
		<% if(!commentList.isEmpty() && commentList!=null){ %>
		<table id="tbl-comment">
			<% for(BoardComment bc : commentList){ 
				boolean removable = loginMember != null && (loginMember.getMemberId().equals(bc.getWriter()) || "A".equals(loginMember.getMemberRole()));
			   		if(bc.getCommentLeve() ==1){
			%>  	<tr class="level1">
						<td>
							<sub class="comment-writer"><%= bc.getWriter() %></sub>
							<sub class="comment-date"><%= bc.getRegDate() %></sub>
							<br />
							<%= bc.getContent() %>
						</td>
						<td>
							<button class="btn-reply" value="<%= bc.getNo() %>">답글</button>
							<% if(removable){ %>
							<button class="btn-delete" value="<%= bc.getNo() %>">삭제</button>
							<%} %>
						</td>
					</tr>
			   		<%}else { %>
			   			<tr class="level2">
						<td>
							<sub class="comment-writer"><%= bc.getWriter() %></sub>
							<sub class="comment-date"><%= bc.getRegDate() %></sub>
							<br />
							<%= bc.getContent() %>
						</td>
						<% if(removable){ %>
						<td>
							<button class="btn-delete" value="<%= bc.getNo() %>">삭제</button>
						</td>
						<% } %>
					</tr>
			<% 		}
			  } %>
		</table>
		<% } %>
	</div>
</section>
<% if(editable){ %>
	<form action="<%= request.getContextPath()%>/board/boardDelete" name = "boardDelFrm" method="post">
		<input type="hidden" name="no" value="<%= board.getNo()%>"/>
	</form>
	<script>
		//바로 삭제 할 수도 있지만 삭제같은경우는 사용자한테 한번 더 물어봐주자!
		function deleteBoard(){
			if(confirm("게시글을 정말 삭제 하시겠습니까?")){
				$(document.boardDelFrm).submit();
			}
		}
		
		function updateBoard(){
			location.href = "<%= request.getContextPath() %>/board/boardUpdate?no=<%= board.getNo() %>";
		}
	</script>
	
	
<% } %>
<form action="<%= request.getContextPath() %>/board/boardCommentDelete" name="boardCommentDelFrm">
	<input type="hidden" name="no" />
	<input type="hidden" name="boardNo" value="<%= board.getNo() %>"/>
</form>
<script>
	$(".btn-delete").click(function(){
		if(confirm("해당 댓글을 삭제 하시겠습니까?")){
			var $frm = $(document.boardCommentDelFrm);
			var boardCommentNo = $(this).val();
			$frm.find("[name=no]").val(boardCommentNo);
			$frm.submit();
		}
	});

	$("[name=content]").focus(function(){
		//로그인 여부 검사
		<% if(loginMember == null){%>
			loginAlert();
		<% } %>
	});
	
	function loginAlert(){
		alert("로그인후 이용 하실수 있습니다.");
		//헤더에 있는 로그인 부분의 input:text부분
		$("#memberId").focus();
	}
	
	$(document.boardCommentFrm).submit(function(){
		//로그인 여부 검사
		<% if(loginMember == null){%>
			loginAlert();
			return false;
		<% } %>
		
		//유효성 검사
		var $content = $("[name=content]");
		if(/^(.|\n){1,}$/.test($content.val())==false){
			alert("댓글 내용을 작성하세요.");
			$content.focus();
			return false;
		}
	});
	
	$(".btn-reply").click(function(){
		<% if(loginMember == null){ %>
		loginAlert();
		return;
		<% } %>
		
		//대댓글 작성폼 동적 생성
		var html = "<tr>";
		html += "<td colspan='2' style='display:none; text-align:left;'>";
		html += '<form action="<%=request.getContextPath()%>/board/boardCommentInsert" method="post" name="boardCommentFrm">';
		html += '<input type="hidden" name="boardNo" value="<%= board.getNo() %>" />';
		html += '<input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />';
		html += '<input type="hidden" name="commentLevel" value="2" />';
		html += '<input type="hidden" name="commentRef" value="' + $(this).val() + '" />';    
		html += '<textarea name="content" cols="60" rows="2"></textarea>';
		html += '<button type="submit" class="btn-insert-reply">등록</button>';
		html += '</form>';
		html += "</td>";
		html += "</tr>";
		
		var $trOfBtn = $(this).parent().parent();
		$(html)
			.insertAfter($trOfBtn)
			.children("td")
			.slideDown(800);
		$(this).off("click");
	});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>