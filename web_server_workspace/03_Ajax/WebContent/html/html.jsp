<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<h1>html</h1>
	<input type="button" value="실행" id="btn1"/>
	<div class="wrapper"></div>
	
<script>
	$(btn1).click(function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/html",
			method:"GET",
			dataType:"html",
			success:function(data){
				console.log(data);
				$(".wrapper").html(data);
			},
			error:function(xhr, status, err){
				console.log(xhr, status, err);
			}
		});
	});
</script>
<style>
	table{
		board-collapse: collapse;
		board:1px solid black;
		margin:5px;
	}
	th, td{
		border:1px solid black;
	}
	table img{
		width:150px;
	}
</style>
</body>
</html>