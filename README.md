### 프로젝트 개요
    - 프로젝트 선택
        - Project: Gradle Groovy
        - Language: Java
        - Framework: Spring Boot 3.5.7
    - Project Metadata
        - Group: hello
        - Artifact: springmvc
        - Packaging: Jar
        - Java: 21
    - Dependencies
        - Spring Web
        - Spring Boot Starter Thymeleaf
        - Lombok
### Jar & War
    - jar: 
        - 항상 내장 서버 (= 톰캣)를 사용하고, webapp 경로 사용 X, 내장 서버 사용에 최적화
    - war:
        - JSP 환경에서 자주 사용, 주 목적은 외부 서버에 배포하는 목적

> **로그 라이브러리:**
> - Logback, Log4J, Log4J2 등등 라이브러리 존재
> - 해당 라이브러리를 통해서 인터페이스로 제공하는 것이 **SLF4J** (= Simple Logging Facade for Java)
> - 결론 SLF4J는 인터페이스이고, 해당 인터페이스 구현체로 Logback 같은 로그 라이브러리를 선택

> **로그 선언**
> - private Logger log = LoggerFactory.getLogger(getClass());
> - @Slf4j: 롬복 사용가능

> **로그 호출** 
> - log.info("test");

> **로그 레벨 설정**
> - application.properties :
>   - 전체 로그 레벨 설정 (= 기본 INFO)
>     - logging.level.root=INFO
>   - hello.springmvc 패키지와 그 하위 로그 레벨 설정
>     - logging.level.hello.springmvc=DEBUG

> **올바른 로그 사용법** 
> - log.debug("data=" + data);
>   - 로그 출력 레벨을 INFO로 설정해도 해당 코드에 있는 "data" + data가 실제 실행되어 버린다
>   - 결과적으로 문자 더하기 연산이 발생
> - log.debug("data={}", data);
>   - 로그 출력 레벨을 INFO로 설정하면 아무런 호출도 발생하지 않는다
>   - 따라서 앞 예제와 같은 의미없는 연산이 발생 X

> **HTTP 메서드 매핑**
> - @RequestMapping에 method 속성으로 HTTP 메서드를 지정하지 않으면 HTTP 메서드와 무관하게 호출

