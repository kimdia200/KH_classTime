# RestAPI
[https://poiemaweb.com/js-rest-api](https://poiemaweb.com/js-rest-api)
[http://meetup.toast.com/posts/92](http://meetup.toast.com/posts/92)
[https://searchmicroservices.techtarget.com/definition/RESTful-API](https://searchmicroservices.techtarget.com/definition/RESTful-API)

[@RequestBody에 관한 정리글](http://bluesky-devstudy.blogspot.com/2016/07/spring-mvc-requestbody.html)

> REST(Representational State Transfer)는 HTTP/1.0과 1.1의 스펙 작성에 참여하였고 아파치 HTTP 서버 프로젝트의 공동설립자인 로이 필딩 (Roy Fielding)의 2000년 논문에서 처음 소개되었다. 
> 
> 발표 당시의 웹이 HTTP의 설계 상 우수성을 제대로 사용하지 못하고 있는 상황을 보고 웹의 장점을 최대한 활용할 수 있는 아키텍쳐로서 REST를 소개하였고 이는 HTTP 프로토콜을 의도에 맞게 디자인하도록 유도하고 있다. 
> **REST의 기본 원칙을 성실히 지킨 서비스 디자인을 “RESTful”이라고 표현한다.**

구성요소
1. uri : 자원을 표현함
    * 동사보다는 명사를 사용할 것
    * 계층구조로 표현
    * 파일확장자는 생략함.
2. method : 기본 crud(행위)는 메소드로 표현
    * POST : Create
    * GET : Read
    * PUT : Update
    * DELETE : Delete


## 특징
`Uniform (유니폼 인터페이스)`
Uniform Interface는 URI로 지정한 리소스에 대한 조작을 통일되고 한정적인 인터페이스로 수행하는 아키텍처 스타일

`Stateless (무상태성)`
REST는 무상태성 성격임. 다시 말해 작업을 위한 상태정보를 따로 저장하고 관리하지 않음.
세션 정보나 쿠키정보를 별도로 저장하고 관리하지 않기 때문에 API 서버는 들어오는 요청만을 처리.
서비스의 자유도가 높아지고 서버에서 불필요한 정보를 관리하지 않음으로써 구현이 단순.

`Cacheable (캐시 가능)`
REST의 가장 큰 특징 중 하나는 HTTP라는 기존 웹표준을 그대로 사용하기 때문에, 웹에서 사용하는 기존 인프라를 그대로 활용함. 따라서 HTTP가 가진 캐싱 기능이 적용 가능. 
HTTP 프로토콜 표준에서 사용하는 Last-Modified태그나 E-Tag를 이용하면 캐싱 구현이 가능함.

`Self-descriptiveness (자체 표현 구조)`
REST의 또 다른 큰 특징 중 하나는 REST API 메시지만 보고도 이를 쉽게 이해 할 수 있는 자체 표현 구조로 되어 있음.

`Client - Server 구조`
REST 서버는 API 제공, 클라이언트는 사용자 인증이나 컨텍스트(세션, 로그인 정보)등을 직접 관리하는 구조로 각각의 역할이 확실히 구분되기 때문에 클라이언트와 서버에서 개발해야 할 내용이 명확해지고 서로간 의존성이 줄어듬.

`계층형 구조`
REST 서버는 다중 계층으로 구성될 수 있으며 보안, 로드 밸런싱, 암호화 계층을 추가해 구조상의 유연성을 둘 수 있고 PROXY, 게이트웨이 같은 네트워크 기반의 중간매체를 사용할 수 있음.


## Rest작성시 유의사항
* URI는 정보의 자원을 표현해야 한다.
    * 리소스명은 동사보다 명사를 사용할 것
    * /(슬래쉬)로 계층관계 표현
    * URI 마지막은 /(슬래쉬)를 사용하지 않음.
    * 파일확장자는 사용하지 않음 - Accept header를 이용해서 파일종표현
    * _(언더스코어)대신 가독성을 위해 -(대쉬)를 사용함.
* 자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE)로 표현한다.