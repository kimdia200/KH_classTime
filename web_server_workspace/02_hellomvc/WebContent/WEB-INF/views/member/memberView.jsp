<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id=enroll-container>
	<h2>회원 정보</h2>
	<form id="memberUpdateFrm" action="<%=request.getContextPath() %>/member/deleteMember" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="memberId" id="memberId_" value="" readonly>
				</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td>
					<input type="password" name="password" id="password_" value="" required>
				</td>
			</tr>
			<tr>
				<th>패스워드확인</th>
				<td>	
					<input type="password" id="password2" value="" required><br>
				</td>
			</tr> 
			<tr>
				<th>이름</th>
				<td>	
				<input type="text"  name="memberName" id="memberName" value=""  required><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthDay" id="birthDay" value=""><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value=""><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address" value=""><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
			       		 <input type="radio" name="gender" id="gender0" value="M">
						 <label for="gender0">남</label>
						 <input type="radio" name="gender" id="gender1" value="F">
						 <label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동" ><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산" ><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서" ><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임" ><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행" ><label for="hobby4">여행</label><br />
​
​
				</td>
			</tr>
		</table>
        <input type="button" onclick="updateMember();" value="정보수정"/>
        <input type="button" onclick="deleteMember();" value="탈퇴"/>
	</form>
	<script>
	function updateMember(){
		//password
		var $p1 = $("#password_");
		var $p2 = $("#password2");
		if(/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test($p1.val()) == false){
			alert("유효한 패스워드를 입력하세요.");
			$p1.select();
			return;
		}
		
		if($p1.val() != $p2.val()){
			alert("패스워드가 일치하지 않습니다.");
			$p1.select();
			return;
		}
		
		//memberName
		var $memberName = $("#memberName");
		if(/^[가-힣]{2,}$/.test($memberName.val()) == false){
			alert("이름은 한글 2글자 이상이어야 합니다.");
			$memberName.select();
			return;
		}
		
		//phone
		var $phone = $("#phone");
		//-제거하기
		$phone.val($phone.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
		
		if(/^010[0-9]{8}$/.test($phone.val()) == false){
			alert("유효한 전화번호가 아닙니다.");
			$phone.select();
			return;
		}
		$("#memberUpdateFrm").attr("method","GET").submit();
	}
	function deleteMember(){
		$("#memberUpdateFrm").submit();
	}
	
	$(function(){
		//값 채워주기
		$("#memberId_").val("<%= loginMember.getMemberId() %>");
		$("#password_").val("<%= loginMember.getPassword() %>");
		$("#password2").val("<%= loginMember.getPassword() %>");
		$("#memberName").val("<%= loginMember.getMemberName() != null ? loginMember.getMemberName() : ""%>");
		$("#birthDay").val("<%= loginMember.getBirthday() != null ? loginMember.getBirthday() : ""%>")
		$("#email").val("<%= loginMember.getEmail() != null ? loginMember.getEmail() : "" %>")
		$("#phone").val("<%= loginMember.getPhone() %>")
		$("#address").val("<%= loginMember.getAddress() != null ? loginMember.getAddress() : ""%>")
		<% if(loginMember.getGender().equals("M")){ %>
			$("#gender0").prop("checked", true);
		<%}else{%>
			$("#gender1").prop("checked", true);
		<%}%>
		<% 
		String[] hobbies = loginMember.getHobby().split("[,]");
		System.out.println(Arrays.toString(hobbies));
		if(hobbies.length!=0){
			for(int i=0; i<hobbies.length; i++){
				if(hobbies[i].equals("운동")){%>
					$("#hobby0").prop("checked", true);
				<%}
				else if(hobbies[i].equals("등산")){%>
					$("#hobby1").prop("checked", true);
				<%}
				else if(hobbies[i].equals("독서")){%>
					$("#hobby2").prop("checked", true);
				<%}
				else if(hobbies[i].equals("게임")){%>
					$("#hobby3").prop("checked", true);
				<%}
				else if(hobbies[i].equals("여행")){%>
					$("#hobby4").prop("checked", true);
				<%}
			}
		}
		%>
	});
	</script>
<%!
	//선언문안에 메서드를 작성 할 수 있다.
	//강사님은 인라인으로 작성하고 인라인안에서 이 함수를 사용했음
	// value = "<% hobbyChecked(list, "운동")  % >"
	//강사님은 스플릿으로 짤라서 list에 넣었음(contatins를 사용하기 위해서)
	public String hobbyChecked(List<String> hobbyList, String hobby){
		return hobbyList != null && hobbyList.contains(hobby) ? "checked" : "";
	}
%>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>