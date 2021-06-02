<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Menu RestAPI" name="title" 	/>
</jsp:include>

<style>
div.menu-test{width:50%; margin:0 auto; text-align:center;}
div.result{width:70%; margin:0 auto;}
</style>
<script>
const menuRestOrigin = "http://localhost:10001";
const menuRestContextPath = "/springboot";
const url = menuRestOrigin + menuRestContextPath;
</script>
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
					//url: `\${url}/menus`, // 타 REST server로 직접요청
					url:"${pageContext.request.contextPath}/menu/selectMenuList.do",
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
						url: `\${url}/menus/\${type}/\${taste}`,
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
				url: `\${url}/menu`,
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
				url: `\${url}/menu/\${id}`,
				method:"get",
				//menu 객체를 JSON으로 변환하여 보냄
				success(data){
					console.log(data);
					const {msg} = data;
					const {menu} = data;

					//ResponseEntity를 활용해서 404를 보낼때는 이 if절은 의미가 없다.
					if(!menu){
						alert(msg);
						return;
					}

					const $updateFrm = $("#menuUpdateFrm");
					$updateFrm.find("[name=id]").val(menu.id);
					$updateFrm.find("[name=restaurant]").val(menu.restaurant);
					$updateFrm.find("[name=name]").val(menu.name);
					$updateFrm.find("[name=price]").val(menu.price);

					/* prop속성으로 checked를 조정하는 경우 removeAttr을 할 필요가 없다.
					$updateFrm.find("#put-kr").removeAttr("checked");
					$updateFrm.find("#put-ch").removeAttr("checked");
					$updateFrm.find("#put-jp").removeAttr("checked"); */
					
					/* switch (menu.type) {
						case 'kr': $updateFrm.find("#put-kr").prop("checked",true); break;
						case 'ch': $updateFrm.find("#put-ch").prop("checked",true); break;
						case 'jp': $updateFrm.find("#put-jp").prop("checked",true); break;
					} */
					$updateFrm.find(`[name=type][value=\${menu.type}]`).prop("checked",true);

					/* prop속성으로 checked를 조정하는 경우 removeAttr을 할 필요가 없다.
					$updateFrm.find("#put-hot").removeAttr("checked");
					$updateFrm.find("#put-mild").removeAttr("checked"); */
					/*switch (menu.taste) {
					case 'hot': $updateFrm.find("#put-hot").prop("checked",true); break;
					case 'mild': $updateFrm.find("#put-mild").prop("checked",true); break;
					} */
					$updateFrm.find(`[name=taste][value=\${menu.taste}]`).prop("checked",true);
					alert("조회 성공");
				},
				//ResponseEntity에서 404를 리턴할 경우 실행가능
				error(xhr, statusText, err){
					console.log(xhr, statusText, err);

					const {status} = xhr;
					status == 404 && alert("해당 메뉴가 존재하지 않습니다.");
					$("[name=id]", e.target).focus();
				}
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

			//formData를 활용해서 객체만들기
			const frmData = new FormData(e.target);
			const menu1 = {};
			frmData.forEach((value, key)=>{
				menu1[key] = value;
			});
			console.log("menu1 = ");
			console.log(menu1);
			
			$.ajax({
				url: `\${url}/menu/\${id}`,
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
				complete(){
					//reset()은 순수 자바스크립트의 form에 속해있는 메서드이기때문에 아래와 같이 사용해야한다.
					//$("#menuUpdateFrm")[0] == javascript 객체
					//$("#menuUpdateFrm") == jquery 객체
					$("#menuSearchFrm")[0].reset();
					$("#menuUpdateFrm")[0].reset();
				}
			});
		});
	</script>
	
	<!-- 4. 삭제 DELETE /menu/123 -->    
	<div class="menu-test">
    	<h4>메뉴 삭제하기(DELETE)</h4>
    	<p>메뉴번호를 사용해 해당메뉴정보를 삭제함.</p>
    	<form id="menuDeleteFrm">
    		<input type="text" name="id" placeholder="메뉴번호" class="form-control" /><br />
    		<input type="submit" class="btn btn-block btn-outline-danger btn-send" value="삭제" >
    	</form>
    </div>
    <script>
    $("#menuDeleteFrm").submit(e=>{
		e.preventDefault();

		//const id = $(e.target).find("[name=id]").val();
		const id = $("[name=id]",e.target).val();
		if(!id) return;

		$.ajax({
			url : `\${url}/menu/\${id}`,
			method:"delete",
			//success : function(data){~~~} 의 간략형
			success(data){
				console.log(data);
				const {msg} = data;
				alert(msg);
			},
			//javaScript가 제공하는 함수를 error에 가져다 쓴것.
			//responseEntity를 사용하여 0행 삭제시 404리턴하게함
			error(xhr, statusText, err){
				console.log(xhr, statusText, err);

				const {status} = xhr;
				status == 404 && alert("해당 메뉴가 존재하지 않습니다.");
				$("[name=id]", e.target).focus();
			},
			complete(){
				$(e.target)[0].reset();
			}
		});
    });
    </script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
