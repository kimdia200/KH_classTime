# JSP

Java Server Page의 약자

Java의 웹서버 프로그램 스펙(서블릿)으로 변환되어 서비스 된다. 서블릿(Servlet)이라함은 **웹서비스 기능을 해주는 자바 클래스**를 말한다. 서블릿의 복잡함을 좀더 간단히 사용할 수 있게 하는 것이 JSP이다.

## 1. JSP로 생성된 클래스 파일이 위치하는 곳(WAS - tomcat인 경우)

    01_sum.jsp ==> _01_sum_jsp.java ==> _01_sum_jsp.class
    (server의 work Directory에 존재함)

## 2. JSP 의 장점

1. 서블릿(Servlet)보다 쉽고, 작성하기가 빠르다.
2. view단부분(html)과 로직부분(java)으로 이루어져 있다. 그리고 정보, view단부분(html)과 로직부분(java)을 분리시킬수도 있다.
3. 프로그래머가 직접 코딩한 서블릿(Servlet)보다 최적화된 서블릿(Servlet)으로 생성시켜 주므로 효율적인 코드가 만들어진다.
4. JavaBean 의 사용이 쉽다.
5. 웹애플리케이션 상에서 변수의 사용가능한 범위(scope)설정이 쉽다.

## 3. JSP를 이루는 구성인자(element)

1. 지시어(directive) :
   1. page `<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>`, `<% page import="java.sql.*, java.util.*" %>`
   2. include `<%@include file="today.jsp" %>`
   3. taglib `<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>`
2. 스크립팅 원소(scripting element) : JSP 페이지에서 자바 코드를 직접 기술할 수 있게 하는 기능.
   1. 선언문(declaration) `<%! 자바코드 %>`
   2. 스크립틀릿(scriptlet) `<% 자바코드 %>`
   3. 출력식, 표현식(expression) `<%= 자바코드 %>`
3. JSP 액션(표준액션, 커스텀액션)

## 4. JSP 페이지에서 사용할 수 있는 내장객체변수의 종류

일반적인 JAVA 프로그램에서는 변수를 사용하기 전에 반드시 선언을 해야 하지만,
JSP 페이지에서는 선언을 하지 않고도 사용할 수 있는 변수가 있다.
이러한 변수를 JSP 페이지의 **내장객체변수(implicit variable)**라고 부른다.
왜냐하면 웹컨테이너(=WAS, 톰캣서버)가 JSP 페이지를 Servlet 클래스로 변환해줄때
자동적으로 변수선언을 해주기 때문에 우리는 변수선언 없이 그냥 사용만 하면 된다.

- pageContext
  - 현재 JSP 페이지의 컨텍스트(Context)
- request
  - doGet, doPost 메소드의 첫번째 파라미터와 동일한 역할.
  - 웹클라이언트에서 보내온 데이터값을 받아서 처리해주는 변수.
  - 저장소 역할을 할 수 있다.
- response
  - doGet, doPost 메소드의 두번째 파라미터와 동일한 역할.
  - 웹클라이언트의 요청에 대해 응답결과 처리를 위한 변수
- out
  - 웹브라우저로 HTML 코드를 출력해주는 기능.
  - Servlet 클래스 안에서 response.getWriter() 메소드를 호출해서 얻은 PrintWriter 객체와 동일한 기능을 함.
- session
  - 세션이라함은 여러 웹 컴포넌트들이 협력작업을 시작해서 끝내기까지의 기간을 의미한다.
  - session 내장객체 속에는 세션을 시작하고 끝내는 기능과 **그 기간동안 웹 컴포넌트(웹서비스하고 있는 어디서나) 들이 데이터를 주고받을 수 있는 기능**이 들어있다.
- application
  - JSP 페이지가 속하는 웹어플리케이션에 관련된 기능을 함.
- config
  - JSP 페이지가 속하는 웹어플리케이션의 초기상태와 관련된 기능을 함.

## 5. page 지시어

현재의 JSP 페이지를 컨테이너에서 처리하는 데 필요한 각종 속성을 기술하는 부분이다. 보통 소스의 맨 앞에 위치한다.

    <%@ page 속성1="속성값1" 속성2="속성값2" ... %>

page 지시어의 속성중에는
형식 지정을 위한 contentType,
자바 클래스 사용을 위한 import,
오류 페이지 관리를 위한 errorPage, isErrorPage
등을 가장 많이 사용한다.

- laguage : 스크립트 언어 유형 지정 (java)
- import : JSP 내에서 사용할 외부 자바 패키지나 클래스의 import 지정
- session : 세션의 사용유무 (true)
- buffer : 버퍼의 크기 (8KB)
- autoFlush : 버퍼의 내용 자동 비움 (true)
- isThreadSafe : 단일스레드 모델을 사용함으로써 true 동시성 제어 여부 지정
- info : JSP 페이지에 대한 설명
- errorPage : 현재 페이지에서 오류 발생 시 호출될 페이지 지정
- isErrorPage : 오류만을 처리하는 페이지로 지정 (false)
- contentType : MIME 형식 지정 및 캐릭터셋 설정 (text.html; charset=ISO-8891-1)
- pageEncoding : contentType 과 동일한 기능을 한다 (ISO-8891-1)
- extends : 현재 JSP 페이지를 특정 클래스를 상속한 클래스로 작성함

> 표준스팩 : javax.servlet.jsp.httpJspPage
> 톰캣구현 : org.apache.jsper.runtime, HttpJSPBase

### directive 상세내용

- language
  사용할 스크립트 언어를 지정하는 속성.
  languege = "java"
- import
  자바 import 와 동일한 기능을 한다.
  <% page import="java.sql._, java.util._" %>
- session
  세션은 웹 브라우저와 웹 서버가 지속적으로 상대를 인식하기 위해 필요한 정보를 임시로 저장해두는 방법이다
  <%@ page session="true" %>
- buffer
  jsp페이지 내용을 출력하려면 JspWriter 객체인 out변수를 사용한다. 이때 out객체로 사용할 버퍼 크기를 지정할 수 있다.
  <%@ page buffer="8KB" %>
  버퍼는 데이터를 좀 더 효율적이고 안전하게 전송하기 위한 프로그래밍 기법으로 전송할 데이터를 미리 확보한 다음조금씩 전송하는 방법이다.
- autoFush
  버퍼를 자동으로 비울것인지를 지정하는 속성이다.
  <%@ page autoFlush="true" %>
- isThreadSafe
  서블릿의 장점은 각 사용자 접속에 대한 요청을 프로세스가 아닌 스레드로 처리하는데 있다.
  스레드를 이용하면 좀 더 효율적으로 시스템을 운영할 수 있으나, 각 스레드가 자원을 공유하기 때문에
  이들 데이터에 대한 안정성을 보장할 수 없게 된다.
  isThreadSafe 옵션은 스레드 상태에서 데이터의 안정성을 보장하는 옵션으로, 기본값은 true이다.
  <% page isThreadSafe="true" %>
- info
  해당 JSP 설명 부분으로 일종의 주석이다.
  <% page info="JSP Example" %>
- errorPage, isErrorPage
  errorPage 속성은 현재 JSP 페이지에서 오류가 발생할 경우 호출할 페이지를 지정한다.
  isErrorPage는 errorPage 속성에 설정된 오류 처리 파일로, 다른 용도로는 사용할 수 없고 오류 처리에만 이용된다.
  <%@ page errorPage="error.jsp" %>
  <%@ page isErrorPage="true" %>
  <%@ page errorpage="ErrorPage.jsp" %>
- contentType
  현재JSP 내용에 대한 유형을 설정하는 옵션이다. 원하는 MIME Type을 기입하면 된다.
  <%@ page contentType="text/html" %>
