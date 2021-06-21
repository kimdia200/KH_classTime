# Servlet

1. 서블릿(Servlet)이란? 
웹서비스를 위한 자바클래스를  서블릿(Servlet)이라고 함.



### 1.1 서블릿의 상속관계
서블릿(Servlet) 클래스는 반드시  javax.servlet.http.HttpServlet 클래스를 부모 클래스로 상속을 받아야 한다.

    javax.servlet.Servlet 인터페이스
    javax.servlet.GenericServlet 추상클래스
    javax.servlet.http.HttpServlet 추상클래스



### 1.2 메소드별 오버라이딩

웹클라이언트의 요청 방식이 GET 방식으로 요청을 해오면 doGet() 메소드로 응답

javax.servlet.http.HttpServlet에 각 요청메소드별 메소드가 정의 되어 있다.
* doDelete
* doGet
* doHead
* doOptions
* doPost
* doPut
* doTrace

웹클라이언트의 요청 방식이 POST 방식으로 요청을 해오면 doPost() 메소드로 응답
그러므로 반드시 doGet() 메소드와 doPost() 메소드는 Overriding(재정의)를  해주어야 한다.

* 첫번째 파라미터는 HttpServletRequest 타입.
* 두번째 파라미터는 HttpServletResponse 타입.



### 1.3 Request, Response

Request
* 클라이언트 플랫폼정보 및 브라우져정보 : String request.getHeader("User-Agent")
* Request 관련 쿠키 : `Cookie[] cookies = request.getCookies();`
* 클라이언트 세션정보 : `HttpSession session = request.getSession();`
* HTTP 메소드  : `String method = reques.getMethod();`
* Request 입력스트림 : `InputStream input = request.getInputstream();`

Response
* 출력스트림을 이용하여(PrintWriter, ServletOutputStream) HTML 등을 작성함.
* 클라이언트에게 돌려보낼 setContentType()을 정한다.
* 그밖에 헤더정보 설정, 오류를 발생시키거나, 쿠키를 추가함.
