<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jQuery UI Autocomplete - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

	<div class="ui-widget">
		<label for="name">우리반 이름 : </label> <input id="name">
	</div>

	<h1 class="selected"></h1>

</body>
<script>
    $( "#name" ).autocomplete({
      source: function(request, response){
    	  console.log(request)
    	  
    	  $.ajax({
    		url:"<%= request.getContextPath()%>/autocomplete",
    		data: {
    			search: request.term
    		},
    		success:function(data){
    			console.log("data = ",data);
    			var arr = data.split("\n");
    			console.log("arr = ",arr);
    			//arr = arr.slice(0, arr.length-1);
    			arr.splice(arr.length-1,1);
    			console.log(arr);
    			
    			arr = $.map(arr,function(name){
    				return {
    					label:name, //노출될 값
    					value:name //내부적처리 텍스트
    				}
    			});
    			console.log(arr);
    			
    			response(arr);
    		},
    		error:function(xhr, status, err){
    			console.log(xhr, status, err);
    		}
    	  });
    	  
      },
      select:function(event, selected){
    	  console.log(event);
    	  console.log(selected);
    	  $('.selected').text(selected.item.label);
      },
      focus: function(event, focused){
    	return false;
    	}
    });
</script>
</html>