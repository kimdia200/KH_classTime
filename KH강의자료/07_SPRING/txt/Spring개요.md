# 스프링 개요

[공식 API: https://docs.spring.io/spring/docs/5.1.5.RELEASE/spring-framework-reference/](https://docs.spring.io/spring/docs/5.1.5.RELEASE/spring-framework-reference/)

![Java framework history diagram](https://d.pr/i/9XZUsp+)

## 특징
**스프링의 가장 큰 특징은 IOC, DI, POJO, PSA, AOP 를 꼽을 수 있음.**

1. 제어 반전, IoC (Inversion of Control)를 지원함.
	* 컨트롤의 제어권이 개발자가 아니라 프레임워크에 있음
	* 객체의 생성부터 모든 생명주기의 관리까지 객체의 제어권이 바뀐 것을 의미합니다.
	* 객체를 생성하고, 직접 호출하는 자바프로그램이 아니라, 만들어둔 자원을 프레임워크에서 호출해서 사용함.

2. 의존성 주입, DI (Dependency Injection) 를 지원함.
	* 이는 객체간의 의존관계를 관리하는 기술
	* 어떤 객체가 필요로 하는 객체를 자기 자신이 직접 생성하는 것이 아니라, 외부에 있는 다른곳에서 자신이 필요로 하는 객체를 주입받는 것

3. POJO (Plain Old Java Object) 방식의 프레임워크
	* 일반적인 J2EE 프레임워크에 비해 특정 라이브러리를 사용할 필요가 없어서 개발이 쉬우며, 기존 라이브러리의 지원이 용이함.

4. PSA (Portable Service Abstraction) 
	* 스프링은 다른 여러 모듈을 사용함에 있어서 별도의 추상화 레이어 제공 
	* 예를 들어 JPA를 사용할 때에서 Spring JPA를 사용하여 추상화 하므로 실제 구현에 있어서 Hibernate를 사용하든 EclipseLink를 사용하든 사용자는 이 모듈의 의존 없이 프로그램에 집중가능.

5. 관점지향프로그래밍, AOP(Aspect Oriented Programming)를 지원함.
	* 트랜잭션, 로깅, 보안 등 여러 모듈, 여러 계층에서 적용되는데, 이런 코드들을 실제 비지니스 로직과 분리
	* 한때, AOP가 OOP(Object Oriented Programming)를 대체하는 기술로 생각되기도 했지만, 실제로 AOP는 OOP를 더욱 OOP스럽게 보완해 주는 기술임.


## 스프링 주요 모듈
![Spring module diagram](https://d.pr/i/zHOZcm+)

`Core`
DI 기능을 비록한 프레임 워크의 기반을 제공

`Beans`
BeanFactory 인터페이스를 통해 구현

`expression`
객체에 접근하고 객체를 조작하기 위한 언어를 제공

`context`
spring-core와 spring-beans 모듈을 확장해, 국제화, 이벤트 처리, 리소스 로딩,
	서블릿 컨테이너를 위한 컨텍스트 생성 등의 기능을 추가로 제공ApplicationContext 인터페이스를 통해 구현

`
context.support`
Ehcache, 메일, 스케줄링, UI의 Velocity 기능 지원

`aop`
AOP Alliance에 호환되는 AOP 구현

`aspects`
AspectJ와 통합을 제공

`web`
파일 업로드, locale 처리 등 웹 통합 기능 제공원격 지원 기능중 웹 관련 기능 제공

`web.servlet`
스프링 MVC 제공jsp,Velocity에 대한 뷰 연동 지원

`web.portlet`
포틀릿 환경에서의 MVC 구현

`jdbc`
jdbc 프로그래밍을 위한 추상 레이어 제공jdbc 템플릿을 제공합으로써 간결한 jdbc프로그래밍 가능

`orm`
하이버네이트, JPA, iBatios, JDO 등 API를 위한 통합 레이어 제공. 스프링이 제공하는 트랜잭션 관리와의 연동을 지원

`oxm`
객체와 xml 사이의 매핑을 처리하기 위한 추상 레이어 제공. JAXB, Castor, XmlBeans, JiBX, XStream과의 연동을 지원한다

`jms`
JMS 메시지를 생성하고 수신하는 기능

`test`
JUnit, TestNG를 이용한 스프링 컴포넌트 테스트 지원

`instrument`
Instrumentation 지원 클래스 제공

`instrument.tomcat`
톰캣 서버를 위한 instrumentation 지원 클래스 제공.

`asm`
asm 라이브러리를 재패킹한 모듈





