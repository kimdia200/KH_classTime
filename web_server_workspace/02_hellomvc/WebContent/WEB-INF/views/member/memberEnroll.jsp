<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<form name="checkIdDuplicateFrm">
	<input type="hidden" name="memberId" />
	
</form>
<section id=enroll-container>
	<h2>회원 가입 정보 입력</h2>
	<form name="memberEnrollFrm" action='<%= request.getContextPath() %>/member/memberEnroll' method="post">
		<table>
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" placeholder="4글자이상" name="memberId" id="memberId_" required>
					<input type="button" value="중복검사" onclick="checkIdDuplicate();" />
					<input type="hidden" id="idValid" value="0" />
					<%-- #idValid 1이면 사용가능한 아이디이고 중복검사함, 0이면 중복검사전. --%>
				</td>
			</tr>
			<tr>
				<th>패스워드<sup>*</sup></th>
				<td>
					<input type="password" name="password" id="password_" required><br>
				</td>
			</tr>
			<tr>
				<th>패스워드확인<sup>*</sup></th>
				<td>	
					<input type="password" id="password2" required><br>
				</td>
			</tr>  
			<tr>
				<th>이름<sup>*</sup></th>
				<td>	
				<input type="text"  name="memberName" id="memberName" required><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthDay" id="birthDay" ><br />
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M" checked>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동"><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산"><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임"><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" >
		<input type="reset" value="취소">
	</form>
</section>
<script>
/**
 * 아이디 중복 검사함수
 * 팝업창으로 [name=checkIdDuplicateFrm]을 제출한다.
 * 현재 페이지에 머물면서 서버통신하기 위함.
 */
function checkIdDuplicate(){
	var $memberId = $("#memberId_");
	if(/^[a-zA-Z0-9_]{4,}$/.test($memberId.val()) == false){
		alert("유효한 아이디를 입력해주세요.");
		$memberId.select();
		return;
	}
	
	//1.팝업생성
	//popup Window객체의 name속성 : checkIdDuplicatePopup
	var title = "checkIdDuplicatePopup";
	open(
			"", 
			title, 
			"width=300px, height=200px, left=200px, top=200px"
		);
	
	//2.폼제출
	$frm = $(document.checkIdDuplicateFrm);
	$frm.find("[name=memberId]").val($memberId.val());//사용자 입력값 세팅
	$frm.attr("action","<%= request.getContextPath()%>/member/checkIdDuplicate")
		.attr("method","POST")
		.attr("target", title)//popup과 form을 연결하는 속성
		.submit();
	
}

/**
 * 중복검사 이후 또 id가 변경되었다면 다시 중복검사 하도록 !
 */
$("#memberId_").change(function(){
	$("#idValid").val(0);
});

/**
 * 회원가입 유효성 검사
 */
$(document.memberEnrollFrm).submit(function(){
	//memberId
	var $memberId = $("#memberId_");
	//아이디는 영문자/숫자  4글자이상만 허용 
	if(/^[a-zA-Z0-9]{4,}$/.test($memberId.val()) == false){
		alert("아이디는 최소 4자리이상이어야 합니다.");
		$memberId.select();
		return false;
	}
	
	var $idValid = $("#idValid");
	if($idValid.val() == 0){
		alert("아이디 중복검사 해주세요.");
		$idValid.prev().focus();
		return false;
	}
	
	
	//password
	var $p1 = $("#password_");
	var $p2 = $("#password2");
	if(/^[a-zA-Z0-9!@#$$%^&*()]{4,}/.test($p1.val()) == false){
		alert("유효한 패스워드를 입력하세요.");
		$p1.select();
		return false;
	}
	
	if($p1.val() != $p2.val()){
		alert("패스워드가 일치하지 않습니다.");
		$p1.select();
		return false;
	}
	
	//memberName
	var $memberName = $("#memberName");
	if(/^[가-힣]{2,}$/.test($memberName.val()) == false){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		$memberName.select();
		return false;
	}
	
	//phone
	var $phone = $("#phone");
	//-제거하기
	$phone.val($phone.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
	
	if(/^010[0-9]{8}$/.test($phone.val()) == false){
		alert("유효한 전화번호가 아닙니다.");
		$phone.select();
		return false;
	}
	
	return true;
	
});

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
