<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<h1>text</h1>
	<input type="button" value="실행" id="btn1" />
	<div class="wrapper"></div>
	<hr />
	<h2>csv</h2>
	<input type="button" value="실행" id="btn2" />
	<div class="container"></div>
<script>
$(btn1).click(function(){
	console.log("btn1 clicked!");
	$.ajax({
		url:"<%=request.getContextPath()%>/text",
		//data:"name=포도&num=12345", //직렬화 버전
		data:{ //jquery는 객체로 전달해도 쉽게 사용 가능
			name:"딸기",
			num:34.56,
			flag:false
		},
		method:"POST",//기본값
		dataType:"text",//응답데이터 형식 text | html | xml | json
		beforeSend:function(){
			//전송전 실행 콜백 함수
			console.log("beforeSend");
		},
		success:function(data){
			//실행요청 성공시 실행 콜백 함수, 매개인자로 응답 message리턴
			console.log("success call!");
			console.log(data);
			$(".wrapper").html(data);
		},
		error:function(xhr, status, error){
			//실행요청 오류시 오류 콜백 함수(http status 200이 아닌경우)
			console.log("error call!");
			console.log(xhr, status, error);
		},
		complete:function(){
			//요청 성공/오류 상관없이 마지막에 호출되는 콜백함수
			console.log("complete call!");
		}
	});
});


$(btn2).click(function(){
	$.ajax({
		url:"<%=request.getContextPath()%>/csv",
		//method:"get", //기본값 생략가능
		//dataType:"text",//응답메시지를 보고 알아서 처리가능
		//beforesend, complete는 주로 원할때만 작성
		success:function(data){
			console.log(data);
			var $table = $("<table></table>");
			var members = data.split("\n");
			members=members.slice(0, members.length-1);
			console.log(members);
			
			$.each(members, function(index, member){
				console.log(index,member);
				var arr=member.split(",");
				
				var tr = "<tr>";
				tr+="<td>"+arr[0]+"</td>";
				tr+="<td>"+arr[1]+"</td>";
				tr+="<td><img src='<%= request.getContextPath()%>/images/"+arr[2]+"'/></td>";
				tr+="</tr>";
				$table.append(tr);
			});
			$(".container").html($table);
		},
		error:function(xhr, status, err){
			console.log(xhr, status, err);
		}
	});
});
</script>
</body>
</html>