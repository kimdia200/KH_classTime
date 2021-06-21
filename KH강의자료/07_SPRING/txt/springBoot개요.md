# Springboot
Springframework를 기반으로 한 개발플랫폼

Main Contributor : [Phill Webb](https://spring.io/team/pwebb)

* 1.0 : 2014
* 2.0 : 2018.3.1


기능정의
* 단독실행 가능한 스프링 어플리케이션 생성
* 내장 컨테이너로 톰캣, 제티, 언더토우 중에 선택
	* [Use Jetty instead of Tomcat](https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/html/howto-embedded-servlet-containers.html#howto-use-jetty-instead-of-tomcat)
* Starter를 통해 가능한 간결한 의존성 구성지원
* 스프링에 대한 자동구성 Auto configuration제공
* 더이상 xml구성 필요 없음
* 제품출시(release) 후 운영에 필요한 다양한 기능(상태점검, 모니터링) 제공 
    * health-check : 주기적으로 어플리케이션 다운여부 확인

구성요소
* build도구 : maven | gradle
* 스프링프레임워크 : 4.x | 5.x
* 스프링부트 1.x | 2.x  
	* *1.x는 지원종료*
* springboot-starter : 의존성 


## Spring Initializer
http://start.spring.io

언어
* Java
* Kotlin
* Groovy

빌드도구
* gradle : build.gradle
* maven : pom.xml

Dependencies
* web
* lombok
* jpa : 자바측 orm 지원 hibernate
* h2

network에서 get방식의 요청이 이루어지는 것 확인.

실행파일
* war : 기존재하는 was서버에 배포
* jar : executable jar(혹은 war)
    * docker, 쿠버네티스 등에 배포

springboot starter
의존성과 설정을 자동화해주는 모듈. 
* springboot-autoconfigure
* springboot-dependencies

개발자가 신경써야할 것.
* springboot 버젼
* 사용하려는 라이브러리의 starter지원여부
* starter미지원시 라이브러리 등록방법

1. Autoconfiguration
2. External Configration : 적용우선순위에 따라 적용
    * 실행인자
    * spring_applicaiton_json
    * 환경변수
    * 기타
    * application.yml, application.properties
    * application-{defaultprofiles}.yml, application={defaultprofiles}.properties



gradle
sts에는 기본gradle플러그인을 별도로 설치해야한다.
현재 marketplace에는 3.x버젼만 설치가능. starter project에서 선택할 수 있는건 2.x까지이므로, 사용자 수동설치 요망
[Eclipse Buildship: Eclipse Plug-ins for Gradle 2.2.2](https://projects.eclipse.org/projects/tools.buildship/releases/2.2.2)

설치 Update Site : http://download.eclipse.org/buildship/updates/e48/releases/2.x/2.2.2.v20181003-1024

브라우져에서 다음 url을 넣으면 zip파일 다운로드 가능

    http://start.spring.io/starter.zip?
        name=springboot&
        groupId=com.kh&
        artifactId=springboot&version=0.0.1-SNAPSHOT&
        description=Demo+project+for+Spring+Boot&
        packageName=com.kh.springboot&
        type=gradle-project&
        packaging=jar&
        javaVersion=1.8&
        language=java&
        bootVersion=2.1.2.RELEASE&dependencies=lombok&
        dependencies=h2&
        dependencies=data-jpa&
        dependencies=web

인터넷이 가능한 환경에서만 프로젝트 생성가능

@appliction.yml
application.properties파일을 사용하지 않음. 확장자 yml로 변경
`Ctrl+space`통해서 사용가능한 명령어 확인가능. 

yml파일 특징
[yml? yaml?](http://kangmyounghun.blogspot.com/2018/11/yml-yaml.html)
* key-value : `: `로 key-value구분. 공백반드시 필요
* 들여쓰기로 부모-자식을 구분
`spring.main.web-environment`은 deprecated

		spring:
		  main:
			web-application-type: SERVLET 
		# OpenType - WebApplicatoinType클래스에 enum중에 선택가능.

		server:
		  port: 9999
    

프로젝트 root directory에서 build하기
(gradlewrapper.jar파일에 의해서 build됨)
* 윈도우 : `gradlew.bat build`
* 리눅스 : `gradlew build`

`build/libs/springboot-0.0.1-SNAPSHOT.jar`파일 생성
이때, 결과물은 실행관련라이브러리 모두 포함된 fat.jar이다.

이를 압축해제해서 내용물 확인
`unzip springboot-0.0.1-SNAPSHOT.jar`

@META-INF/MANIFEST.MF
* 버젼
* 실행클래스 및 실행메소드 정의

    Manifest-Version: 1.0
    Start-Class: com.kh.springboot.SpringbootApplication
    Main-Class: org.springframework.boot.loader.JarLauncher


배너변경하기
커스텀배너를 사용할 수 있다.
[Using Custom Banners in Spring Boot](https://www.baeldung.com/spring-boot-custom-banners)
[커스텀배너 제작 사이트](https://devops.datenkollektiv.de/banner.txt/index.html)

`src/main/resources/banner.txt`파일로 저장할 것. build시에 자동으로 파일을 참조.


빈객체
* @Configuration + @Bean : 외부라이브러리등을 빈등록시.
* @Component : 개발자가 직접 작성한 클래스 빈등록시

DI 방법
* 생성자 주입(권장) : 생성자 하나일 것. @Autowired 불필요
* setter주입 @Autowired
* 필드 선언 : @Autowired


Lombok 사용하기
최초 설치 필요함. lombook.jar를 실행해야함: cmd `C:\dev\sts\sts-3.9.5.RELEASE\sts-3.9.5.RELEASE>java -jar lombok.jar`
최초실행시 STS.exe에 대해 바인딩함.(STS.ini와 실행파일 경로에 lombok.jar복사됨.)
[Lombok is not generating getter and setter](https://stackoverflow.com/questions/11803948/lombok-is-not-generating-getter-and-setter)

restart하지 말고, 아예 껏다 켜야함. ㅠㅜ



배포
* AWS 
* MS Azure
* GCP Google Cloud Platform
* 비즈팩