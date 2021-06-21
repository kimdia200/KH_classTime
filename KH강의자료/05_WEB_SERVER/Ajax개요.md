# Ajax
* Asynchronous JavaScript and XML 이란 용어로써 2005년 2월 제시 제임스 카렛이 처음 사용하면서 알려지게 되었음
* 서버로부터 데이터를 가져와 전체 페이지를 새로 고치지 않고 일부만 로드할 수 있게 하는 기법으로 비동기식 요청을 보내는데 필요한 기술을 말함

## 동기식/비동기식이란?
동기식은 서버와 클라이언트가 동시에 통신하여 프로세스를 수행 및 종료까지 같이함. 
만약 서버에서 호출된 결과까지의 시간이 지연이 생길 경우 웹에서는 아무런 작동 없이 기다려야함.
새로운 콘텐츠를 추가, 불러오기 위해서는 페이지를 리로드 하거나 이동해야함.

이에 반해 비동기식은 페이지 리로딩 없이, 동기식의 서버요청 사이사이 추가적인 요청과 처리가 가능함.

* 실시간 검색어 로딩
* 검색어자동 완성
* 아이디 중복체크 등...

단점

* 한 페이지에 지속적으로 사용시 리소스가 계속 쌓여 페이지가 느려질 수 있다.
* 페이지내 복잡도가 기하급수적으로 증가, 에러 발생시 디버깅이 어려움



## ajax 비동기식과 동기식 처리

1. 비동기식 처리 모델
먼저 서버에 데이터를 요청 script 문을 실행함. 
브라우저는 요청한 데이터를 기다리지 않고, 이후 스크립트를 계속 처리함.
그리고 요청한 데이터가 도착을 하면 그때 이벤트가 발생하면서 지정된 함수가 호출되어 실행되는 방식 

2. 동기식 처리 모델 
페이지가 로드 되는 동안 브라우저는 script 문을 실행하게 되면 
그 실행이 종료될 때까지 이후 스크립트는 처리하지 않고 기다리고 있다가 
서버요청처리가 종료되면 이후 스크립트를 실행함.


## ajax의 구현
JavaScript 방식과 jQuery 방식이 있음
그 중 실제로 많이 사용하는 방식은 jQuery 방식 (코드가 간결하고 사용하기 쉬움)



## 처리 절차
1. script문에 요청을 위한 XMLHttpRequest 객체 생성
2. 서버에 응답을 처리할 함수 생성 및 지정
	* onreadystatechange에 함수 지정
3. open메소드로 요청할 방법 및 요청할 대상(Server) 선정
	* 요청메소드, 요청주소, 동기/비동기 설정, 아이디, 패스워드 설정
4. send메소드로 대상(Server)에 전송
	* post일때 파라미터 값 설정 / get 일때는 매개변수 없음 
5. 응답상태에 따라 상태 확인
	* readyState(데이터응답) / status(처리결과) 값을 이용
6. 응답완료 responseText / responseXML 이용 응답처리






# XMLHttpRequest
백그라운드에서 비동기적으로 웹서버와 통신을 담당하는 객체로서 사용자의 요청을 웹서버에 전송해주고, 웹서버로 부터 받은 결과를 사용자의 웹브라우저에 전달해줌.
Ajax에서 통신을 담당하고 데이터를 송수신할 수 있는 핵심 객체가 XMLHttpRequest 임

	var xhr;
	function createHttpRequest(){
		xhr = new XMLHttpRequest();
	}


## 1. XMLHttpRequest 속성(필드)

* onreadystatechange : readyState 속성이 변경될 때 호출되는 메소드를 저장하는 변수
* readyState : 객체의 상태를 저장하는 변수
* responseText : 응답(response) 결과를 문자열로 저장하는 변수
* responseXML : 응답(response) 결과를 XML data로 저장하는 변수
* status : 전송/응답 결과를 저장하는 변수(코드값)
* statusText : 전송/응답 결과를 저장하는 변수(문자열)

### readyState 속성 값
* 0 (uninitialized): 요청이 시작되지 않은 상태 / open 메소드가 호출되지 않은 상태 
* 1 (loading) : 서버와 접속된 상태 / send 메소드가 호출되지 않은 상태
* 2 (loaded) : send 메소드가 호출되고 헤더는 도착하지 않은 상태
* 3 (interactive) : 일부 데이터를 받은 상태
* 4 (completed) : 요청을 완료하고 응답하는 상태

### status 속성 값
* 200 (OK) : 요청 성공
* 404 (Not Found) : 페이지 없음
* 500 (Internal Server Error) : 서버 오류 발생 등...

[http status 값 참조 ](https://www.w3schools.com/tags/ref_httpmessages.asp)


-> 기본적으로 Ajax를 구현할 때는 readyState(값:4) 와 status(값:200)를 비교하여 요청이 모두 
정상적으로 완료된 것을 확인한 후에 다음 작업을 진행 해야 함

	if(xhr.readyState==4){
		if(xhr.status==200){
			//정상 처리 작업
		}else{
			//에러 처리 작업
		}
	}


## 2. XMLHttpRequest의 메소드
* abort()	: 요청을 취소하는 메소드
* getAllResponseHeaders() : 응답헤더의 정보를 가져오는 메소드
* getResponseHeader("헤더명") : 특정 헤더정보만 가져오는 메소드
* open(method,url,async,user,pw) : 요청정보를 설정하는 메소드
* send() : 서버로 요청을 보내는 메소드 (get방식)
* send(String) : 서버로 요청을 보내는 메소드 (post방식)
* setRequestHeader() : 헤더에 이름/값 방식으로 정보를 추가하는 메소드




## 3. responseText와 responseXML 
서버로부터 데이터의 내용을 응답받는 방법으로는 2가지를 제공
* responseText에 의해 일반적인 텍스트 형태로 받는 방법
* responseXML에 의해 XML 형식으로 받는 방법
	* responseXML로 반환받으면 자동으로 DOM의 Document객체가 생성되어 직접 XML 파싱 처리를 할 수 있음

ex) 객체 생성후 속성값으로 응답처리
var httpRequest = getHttpRequest();

var value = httpRequest.responseText;
또는
document.getElementById("test").innerHTML = httpRequest.responseText;

<script>
  var httpRequest= getHttpRequest();
  httpRequest.onreadystatechange=test;
  
  function test()
  {
      if(httpRequest.readyState===4)
      {
          if(httpRequest.status===200)
          {
			doucument.getElementById(“ex”).innerHTML
                   =httpRequest.responseText; //특정 태그에 응답 받은 데이터 처리
          }
      }
   }
</script>


(심화) CSV형식의 예제
CSV(Comma Separated Values)는 여러 데이터를 쉼표로 구분해서 표현하는 방법

CSV 데이터

	홍길동,20,서울
	김말똥,30,경기

	var responseData = xhttp.responseText;
	var rows = responseData.split("\n");  	// 개행문자로 행을 구분
	var cols = rows[0].split(",");		// 쉼표(,)로 열을 구분
	var rowData = cols[0] + "\t" + cols[1] +"\t\ + cols[2]";
	alert(rowData);




(심화) XML형식의 예제
-> XML은 데이터를 표현하는 방식으로 사용자가 태그를 정의함 (HTML과 비슷하나 기능적인 태그가 정의된것은 아님)

XML 형식의 데이터
	
	<?xml version="1.0" encoding="UTF-8"?>
	<student>
		<name>홍길동</name>
		<age>20</age>
		<addr>경기</addr>
	</student>



	var responseData = xhr.responseXML;
	var names = responseData.getElementsByTagName("name");
	var name = names[0].firstChild.nodeValue;
	alert(name);






## jQuery Ajax
* ajax 통신은 jQuery로 하게 되면 훨씬 간단하게 할 수 있음
* 또한 크로스브라우징이슈를 jQuery가 알아서 해결해 줌
* jQuery Ajax에서 가장 중요한 API는 "$.ajax" 임
	
$.ajax의 기본 문법

	jQuery.ajax( [settings ] );

	$.ajax({
		설정 값1,
		설정 값2,
		설정 값3,
		설정 값4……
	});

[참고 API](http://api.jquery.com/jquery.ajax/)


Ajax 주요 속성
* url - 요청 할 server의 url
* accept - 파라미터의 타입을 설정(사용자 특화 된 파라미터 타입 설정 가능)
* async	- 서버와의 비동기 처리 방식 설정 여부(기본값 true)
* beforeSend - ajax 요청을 하기 전 실행 되는 이벤트 callback 함수(데이터 가공 및 header 관련 설정)
* cache	- 요청 및 결과값을 scope에서 갖고 있지 않도록 하는것(기본값 true)
* complete - 요청 완료 후 실행되는 메소드(성공, 에러 이 후에 무조건 실행)
* contents - jquery 에서 response의 데이터를 파싱하는 방식 정의
* contentType - request의 데이터 인코딩 방식 정의,(보내는 측의 데이터 인코딩)
* context	- ajax 메소드 내 모든 영역에서 사용 가능한 값
* converters - object가 포함되었을때 파싱 방식 정의
* crossDomain - 타 도메인 호출 가능 여부 설정 (기본값 false)
* data	- 요청 parameter 설정
* dataFilter - response를 받았을때 정상적인 값을 return 할수 있도록 데이터와 데이터 타입 설정
* dataType - 서버에서 response 오는 데이터의 데이터 형 설정, 값이 없다면 스마트하게 판단함.
	* xml - 트리 형태의 데이터 구조
	* json - 맵 형식의 데이터 구조(일반적인 데이터 구조)
	* script - javascript 및 일반 string 형태 데이터 
	* html - html태그 자체를 return 하는 방식
	* text - string 데이터
* error	- ajax통신에 실패했을 때 호출될 이벤트 핸들러
* global	- 기본 이벤트 사용 여부(ajaxStart, ajaxStop)(버퍼링 같이 시작과 끝을 나타낼때, 선처리 작업)
* method | type	- 서버 요청 방식(GET, POST, PUT) 기본값 GET
* password - 서버에 접속 권한(비밀번호)가 필요한 경우
* processData - 서버로 보내는 값에 대한 형태 설정 여부(기본데이터를 원하는 경우 false 설정)
* success - ajax통신에 성공했을 때 호출될 이벤트 핸들러
* timeout - 서버 요청 시 응답 대기 시간(milisecond)

[참고 API:http://api.jquery.com/jquery.ajax/](http://api.jquery.com/jquery.ajax/)


























