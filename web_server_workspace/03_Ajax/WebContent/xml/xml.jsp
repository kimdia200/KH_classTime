<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax - xml</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
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
</head>
<body>
	<h1>XML</h1>
	<button type="button" id="btn">실행</button>
	<button type="button" id="btn-product-sample">상품xml 가져오기</button>

	<script>
	$(btn).click(function(){
		
		$.ajax({
			url: "<%= request.getContextPath() %>/xml",
			//dataType: "xml",
			success: function(data){
				console.log(data);
				var $root = $(data).find(":root");
				//console.log($root);
				var $members = $root.find("member");
				var $table = $("<table></table>");
				$members.each(function(index, member){
					console.log(index, member);
					
					var id = $(member).children("id").text();
					var name = $(member).children("name").text();
					var profile = $(member).children("profile").text();
					
					var $tr = $("<tr></tr>");
					$tr.append("<td>"+(index+1)+"</td>")
					$tr.append("<td><img src='<%= request.getContextPath()%>/images/"+profile+"' width='150px' </td>")
					$tr.append("<td>"+id+"</td>")
					$tr.append("<td>"+name+"</td>")
					$table.append($tr).appendTo($("body"));
				});
			},
			error: function(xhr, status, err){
				console.log(xhr, status, err);
			}
		});
	});
	$("#btn-product-sample").click(function(){
		$.ajax({
			url: "<%= request.getContextPath() %>/xml/sample-product.xml",
			success: function(data){
				console.log(data);
				
				var $root = $(data).find(":root");
				var $products = $root.find("Product");
				var $ul = $("<ul></ul>");
				
				$products.each(function(index, product){
					var name = $(product).children("Name").text();
					var price = $(product).children("Price").text();
					
					var $li = $("<li>"+name+"("+price+")</li>")
					$li.appendTo($ul).appendTo($("body"));
				});
			},
			error: function(xhr, status, err){	
				console.log(xhr, status, err);
			}
		});
	});
	</script>

</body>
</html>