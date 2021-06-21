## JSP를 이루는 구성인자(element)
1. 지시어(directive) : 
    1. page `<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>`, `<% page import="java.sql.*, java.util.*" %>`
    2. include `<%@include file="today.jsp" %>`
    3. taglib `<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`
2. 스크립팅 원소(scripting element) : JSP 페이지에서 자바 코드를 직접 기술할 수 있게 하는 기능.
    1. 선언문(declaration) `<%! 자바코드 %>`
    2. 스크립틀릿(scriptlet) `<% 자바코드 %>`
    3. 출력식, 표현식(expression) `<%= 자바코드 %>`
3. JSP 액션(표준액션, 커스텀액션)


### 액션(action)이란?
XML 기술을 이용해서 기존의 JSP 문법을 확장해준 매커니즘으로 JSP 규격서에 정의되어 있는 기술이다.  
이것은 XML 태그 형태를 띄기 때문에 액션태그(action tag)라고도 부르며, 간단히 액션(action)이라고 한다.
왜냐하면 이런 태그가 어떤 동작(action)을 수행하는 역할을 하기 때문이다.


#### 액션(action)의 2가지 종류

1. 표준액션(Standard action) : JSP 페이지에서 막바로 사용이 가능함.
    * 표준액션(Standard action)은 모든 태그의 이름 앞에 jsp 라는 접두어가 붙음.
    * 예) `<jsp:include page="abc.jsp" />`
2. 커스텀액션(Custom action) : 별도의 라이브러리를 설치해야만 사용가능함. 라이브러리는 인터넷에서 다운로드 받을수도 있고(JSTL), 사용자가 직접 만들어 사용할수도 있음.
    * 커스텀액션(Custom action)은 모든 태그의 이름 앞에 jsp 이외의 접두어가 붙음.
    * 예) `<c:set var="cnt" value="0" />`



#### 액션(action)은 클라이언트에 전달되지 않는다.
HTML 태그처럼 클라이언트의 웹브라우저로 직접 전달되는 것이 아니라, 웹컨테이너(톰캣서버)쪽에서 실행되고, 그 결과만 클라이언트의 웹브라우저에서 출력된다는 것이다.

### 표준액션(Standard action) 구현
* `<jsp:include>` 와 `<jsp:forward>` 표준액션을 이용하면 자바에서 했던 include()메소드,표준액션을 forward()메소드와 똑같은 기능을 구현할수 있다.
* `<jsp:useBean>, <jsp:setProperty>`등의 표준액션을 이용하면 자바코드를 작성하지 않고도 자바빈(JavaBean, 자바빈 규격서에 따라 만든 자바클래스)을 만들어 사용할 수 있다.



## EL
Expression Language 표현식 언어
(Scripting Expression 스크립팅표현식과 헷갈리지 말것)

JSP 2.0 버전에서 추가된 것으로 <%= %>, out.print()와 같이 JSP에 쓰이는 Java
코드를 간결하게 사용하는 방법으로, 화면에 표현하고자 하는 코드를 `${value}`의 형식으로 표현하여 작성하는 것을 말한다.

### EL내장객체

`${requestScope.person.name}`
* 첫번째 변수는 다음 스코프별 내장객체(map)이다.(각 스코프는 생략가능))
    * pageScope
    * requestScope
    * sessionScope
    * applicationScope
	* 스코프가 생략되었다면, page/request/session/application생존범위의 속성이 바로 올수 있다.

* 사용자입력값
    * param
    * paramValues
    
* 요청 header정보
    * header
    * headerValues

* 쿠키관련
    * cookie

* 초기화 파라미터
    * initParam : 컨텍스트파라미터(애플리케이션 전체에 공유)를 가져옴. 이름이 직관적이지 않으므로 주의할 것.

* 포인터객체
    * pageContext 내장객체 주 유일하게 맵이 아님(pageContext는 자바빈). 참조형객체

