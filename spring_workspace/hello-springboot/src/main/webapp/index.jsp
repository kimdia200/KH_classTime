<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu - RestAPI</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- bootstrap js: jquery load 이후에 작성할것.-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<!-- 사용자작성 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css" />
<style>
div.menu-test{width:50%; margin:0 auto; text-align:center;}
div.result{width:70%; margin:0 auto;}
</style>
</head>
<body>
<div id="container">
	<header>
		<div id="header-container">
			<h2>Menu - RestAPI</h2>
		</div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">
				<img src="${pageContext.request.contextPath }/resources/images/logo-spring.png" alt="스프링로고" width="50px" />
			</a>
		  	<!-- 반응형으로 width 줄어들경우, collapse버튼관련 -->
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
		  	</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				
			</div>
		</nav>
	</header>
	<section id="content">
		<div id="menu-container" class="text-center">
			<!-- 1. GET /menus-->
	        <div class="menu-test">
	            <h4>전체메뉴조회(GET)</h4>
	            <input type="button" class="btn btn-block btn-outline-success btn-send" id="btn-menus" value="전송" />
	        </div>
	        <div class="result" id="menus-result"></div>
	        <script>
	        $("#btn-menus").click(() => {
				$.ajax({
					url: "${pageContext.request.contextPath}/menus",
					method: "GET",
					success(data){
						console.log(data);
						displayResultTable("menus-result",data);
					},
					error: console.log//인자가 몇개인지는 중요하지 않고 console.log( 모든인자) 와 같다
					//즉 error : (x, y) => console.log(x, y) 와같다
				})
			});

			function displayResultTable(id, data){
				const $container = $("#"+id);
				let html = "<table class='table'>";
				html += "<tr><th>번호</th><th>음식점</th><th>메뉴</th><th>가격</th><th>타입</th><th>맛</th></tr>";
				//mybatis session.selectList는 데이터가 없는경우 빈 리스트를 리턴한다. 절대 null이 아니다.
				//따라서 사이즈로 비교해줘야한다.
				if(data.length > 0){
					$(data).each((i,menu)=>{
						const {id, restaurant, name, price, type, taste} = menu;
						html += 
							`<tr>
								<td>\${id}</td>
								<td>\${restaurant}</td>
								<td>\${name}</td>
								<td>\${price}</td>
								<td>\${type}</td>
								<td><span class="badge badge-\${taste == 'hot' ? 'danger' : 'warning'}">\${taste}</span></td>
							</tr>`;
					});
				}else{
					html += "<tr><td colspan='6'>검색된 결과가 없습니다.</td></tr>"
				}
				html+="</table>";
				$container.html(html);
			}
	        </script>
			
			<!-- 
			
				2. Get /menus/kr , /menus/ch , /menus/jp
				경로함수를 사용하여 한번에 처리함
				
			 -->
			<div class="menu-test">
				<h4>추천메뉴(GET)</h4>
				<form id="menuRecommendationFrm">
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" name="type" id="get-no-type" value="all" checked>
						<label for="get-no-type" class="form-check-label">모두</label>&nbsp;
						<input type="radio" class="form-check-input" name="type" id="get-kr" value="kr">
						<label for="get-kr" class="form-check-label">한식</label>&nbsp;
						<input type="radio" class="form-check-input" name="type" id="get-ch" value="ch">
						<label for="get-ch" class="form-check-label">중식</label>&nbsp;
						<input type="radio" class="form-check-input" name="type" id="get-jp" value="jp">
						<label for="get-jp" class="form-check-label">일식</label>&nbsp;
					</div>
					<br />
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" name="taste" id="get-no-taste" value="all" checked>
						<label for="get-no-taste" class="form-check-label">모두</label>&nbsp;
						<input type="radio" class="form-check-input" name="taste" id="get-hot" value="hot">
						<label for="get-hot" class="form-check-label">매운맛</label>&nbsp;
						<input type="radio" class="form-check-input" name="taste" id="get-mild" value="mild">
						<label for="get-mild" class="form-check-label">순한맛</label>
					</div>
					<br />
					<input type="submit" class="btn btn-block btn-outline-success btn-send" value="전송" >
				</form>
			</div>
			<div class="result" id="menuRecommendation-result"></div>
			<script>
				$("#menuRecommendationFrm").submit(e=>{
					// 폼제출을 방지 : return false;
					e.preventDefault();

					// 현재폼
					const $frm = $(e.target);//e.target = form
					const type = $frm.find("[name=type]:checked").val();
					const taste = $frm.find("[name=taste]:checked").val();
					console.log(type," | ",taste);

					$.ajax({
						url: `${pageContext.request.contextPath}/menus/\${type}/\${taste}`,
						success(data){
							console.log(data)
							displayResultTable("menuRecommendation-result", data);
						},
						error : console.log
					});
				});
			</script>
		</div>
		
		
		<!-- 2.POST /menu -->
		<div class="menu-test">
			<h4>메뉴 등록하기(POST)</h4>
			<form id="menuEnrollFrm"> 
				<input type="text" name="restaurant" placeholder="음식점" class="form-control" />
				<br />
				<input type="text" name="name" placeholder="메뉴" class="form-control" />
				<br />
				<input type="number" name="price" placeholder="가격" class="form-control" />
				<br />
				<div class="form-check form-check-inline">
					<input type="radio" class="form-check-input" name="type" id="post-kr" value="kr" checked>
					<label for="post-kr" class="form-check-label">한식</label>&nbsp;
					<input type="radio" class="form-check-input" name="type" id="post-ch" value="ch">
					<label for="post-ch" class="form-check-label">중식</label>&nbsp;
					<input type="radio" class="form-check-input" name="type" id="post-jp" value="jp">
					<label for="post-jp" class="form-check-label">일식</label>&nbsp;
				</div>
				<br />
				<div class="form-check form-check-inline">
					<input type="radio" class="form-check-input" name="taste" id="post-hot" value="hot" checked>
					<label for="post-hot" class="form-check-label">매운맛</label>&nbsp;
					<input type="radio" class="form-check-input" name="taste" id="post-mild" value="mild">
					<label for="post-mild" class="form-check-label">순한맛</label>
				</div>
				<br />
				<input type="submit" class="btn btn-block btn-outline-success btn-send" value="등록" >
			</form>
		</div>
		<script>
		/**
		* POST    /menu
		*/
		
		$("#menuEnrollFrm").submit(e=>{
			// 폼제출을 방지 : return false;
			e.preventDefault();
			// 현재폼
			const $frm = $(e.target);//e.target = form
			const restaurant = $frm.find("[name=restaurant]").val();
			const name = $frm.find("[name=name]").val();
			const price = Number($frm.find("[name=price]").val());
			const type = $frm.find("[name=type]:checked").val();
			const taste = $frm.find("[name=taste]:checked").val();
			console.log(restaurant, " | ",name," | ",price," | ",type," | ",taste);

			const menu = {
					//변수와 값이 이름이 같으면 이렇게 줄여서 사용 할수있음
					restaurant,name,price,type,taste
					};
			console.log(menu);

			$.ajax({
				url: "${pageContext.request.contextPath}/menu",
				method:"post",
				//menu 객체를 JSON으로 변환하여 보냄
				data:JSON.stringify(menu),
				contentType:"application/json; charset=utf-8",
				success(data){
					console.log(data);
					const {msg} = data;
					alert(msg);
				},
				error : console.log,
				complete(){
					e.target.rest();
				}
			});
		});
		</script>
		
		<!-- #3.PUT /menu/123 -->
	<div class="menu-test">
		<h4>메뉴 수정하기(PUT)</h4>
		<p>메뉴번호를 사용해 해당메뉴정보를 수정함.</p>
		<form id="menuSearchFrm">
			<input type="text" name="id" placeholder="메뉴번호" class="form-control" /><br />
			<input type="submit" class="btn btn-block btn-outline-primary btn-send" value="검색" >
		</form>
		
		<script>
		//메뉴 조회
		$("#menuSearchFrm").submit(e=>{
			e.preventDefault();
			const $frm = $(e.target);
			const id = $frm.find("[name=id]").val();

			$.ajax({
				url: `${pageContext.request.contextPath}/menu/\${id}`,
				method:"get",
				//menu 객체를 JSON으로 변환하여 보냄
				success(data){
					console.log(data);
					const {msg} = data;
					const {menu} = data;

					const $updateFrm = $("#menuUpdateFrm");
					$updateFrm.find("[name=id]").val(menu.id);
					$updateFrm.find("[name=restaurant]").val(menu.restaurant);
					$updateFrm.find("[name=name]").val(menu.name);
					$updateFrm.find("[name=price]").val(menu.price);

					$updateFrm.find("#put-kr").removeAttr("checked");
					$updateFrm.find("#put-ch").removeAttr("checked");
					$updateFrm.find("#put-jp").removeAttr("checked");
					switch (menu.type) {
						case 'kr': $updateFrm.find("#put-kr").prop("checked",true); break;
						case 'ch': $updateFrm.find("#put-ch").prop("checked",true); break;
						case 'jp': $updateFrm.find("#put-jp").prop("checked",true); break;
					}

					$updateFrm.find("#put-hot").removeAttr("checked");
					$updateFrm.find("#put-mild").removeAttr("checked");
					switch (menu.taste) {
					case 'hot': $updateFrm.find("#put-hot").prop("checked",true); break;
					case 'mild': $updateFrm.find("#put-mild").prop("checked",true); break;
					}
					alert("조회 성공");
				},
				error : console.log
			});			
		});
		
		</script>
	
		<hr />
		<form id="menuUpdateFrm">
			<input type="hidden" name="id" value="" />
			<input type="text" name="restaurant" placeholder="음식점" class="form-control" />
			<br />
			<input type="text" name="name" placeholder="메뉴" class="form-control" />
			<br />
			<input type="number" name="price" placeholder="가격" step="1000" class="form-control" />
			<br />
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" name="type" id="put-kr" value="kr" checked>
				<label for="put-kr" class="form-check-label">한식</label>&nbsp;
				<input type="radio" class="form-check-input" name="type" id="put-ch" value="ch">
				<label for="put-ch" class="form-check-label">중식</label>&nbsp;
				<input type="radio" class="form-check-input" name="type" id="put-jp" value="jp">
				<label for="put-jp" class="form-check-label">일식</label>&nbsp;
			</div>
			<br />
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" name="taste" id="put-hot" value="hot" checked>
				<label for="put-hot" class="form-check-label">매운맛</label>&nbsp;
				<input type="radio" class="form-check-input" name="taste" id="put-mild" value="mild">
				<label for="put-mild" class="form-check-label">순한맛</label>
			</div>
			<br />
			<input type="submit" class="btn btn-block btn-outline-success btn-send" value="수정" >
		</form>
	</div>
	<script>
		$("#menuUpdateFrm").submit(e=>{
			e.preventDefault();

			const $frm = $(e.target);
			const id = $frm.find("[name=id]").val();
			const restaurant = $frm.find("[name=restaurant]").val();
			const name = $frm.find("[name=name]").val();
			const price = Number($frm.find("[name=price]").val());
			const type = $frm.find("[name=type]:checked").val();
			const taste = $frm.find("[name=taste]:checked").val();
			console.log(id," | ",restaurant, " | ",name," | ",price," | ",type," | ",taste);

			const menu = {
					//변수와 값이 이름이 같으면 이렇게 줄여서 사용 할수있음
					id,restaurant,name,price,type,taste
					};
			console.log(menu);
			$.ajax({
				url: `${pageContext.request.contextPath}/menu/\${id}`,
				method:"put",
				//menu 객체를 JSON으로 변환하여 보냄
				data:JSON.stringify(menu),
				contentType:"application/json; charset=utf-8",
				success(data){
					console.log(data);
					const {msg} = data;
					alert(msg);
				},
				error : console.log,
			});
		});
	</script>
	</section>
	<footer>
		<p>&lt;Copyright 2017. <strong>KH정보교육원</strong>. All rights reserved.&gt;</p>
	</footer>
	
</div>
</body>
</html>