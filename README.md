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

> **MultiValueMap** 
> - MAP과 유사한데, 하나의 키에 여러 값을 받을 수 있다.
> - HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용한다.
>   - keyA=value1&keyA=value2

> **클라이언트에서 서버로 요청 데이터를 전달할 때는 주로 3가지 방법을 사용**
> - GET - 쿼리 파라미터
>   - /url?username=hello&age=20
>   - 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
>   - 예) 검색, 필터, 페이징등에서 많이 사용하는 방식
> - POST - HTML Form
>   - content-type: application/x-www-form-urlencoded
>   - 메시지 바디에 쿼리 파라미터 형식으로 전달 username=hello&age=20
>   - 예) 회원 가입, 상품 주문, HTML Form 사용
> - HTTP message body에 데이터를 직접 담아서 요청
>   - HTTP API에서 주로 사용, JSON, XML, TEXT
>   - 데이터 형식은 주로 JSON 사용
>   - POST, PUT, PATCH


> **@RequestBody**
> - @RequestBody를 사용하면 HTTP 메시지 바디 정보를 편리하게 조회 가능
> - 헤더 정보가 필요하다면 HttpEntity를 사용하거나 @RequestHeader를 사용
> - 해당 메시지 바디를 직접 조회하는 기능은 요청 파라미터를 조회하는 @RequestParam, @ModelAttribute와 관련 X
 
> **요청 파라미터 vs HTTP 메시지 바디**
> - 요청 파라미터를 조회하는 기능
>   - @RequestParam, @ModelAttribute
> - HTTP 메시지 바디를 직접 조회하는 기능
>   - @RequestBody
> - @ResponseBody
>   - HTTP 메시지 바디에 직접 담아서 전달 (= View 사용 X)

> **HTTP 응답 - 정적 리소스, 뷰 템플릿**
> - 정적 리소스
>   - 웹 브라우저에 정적인 HTML, CSS, JS를 제공할 떄는 **정적 리소스**를 사용
> - 뷰 템플릿 사용
>   - 웹 브라우저에 동적인 HTML을 제공할 떄는 뷰 템플릿을 사용한다
> - HTTP 메시지 사용
>   - HTTP API를 제공하는 경우에는 HTML이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON같은 형식으로 데이터를 실어 보낸다

> **정적 리소스**
> - 스프링 부트는 클래스패스의 다음 디렉토리에 있는 정적  리소스를 제공한다
>   - /static, /public, /resources, /META-INF/resources
>   - src/main/resources는 리소스를 보관하는 곳이고, 클래스패스의 시작 경로임
>   - 따라서 다음 디렉토리에 리소스를 넣어두면 스프링 부트가 정적 리소스로 서비스를 제공
> - 정적 리소스 경로
>   - src/main/resources/static
>   - 다음 경로에 파일이 존재하면 src/main/resources/static/basic/hello-form.html
>   - 웹 브라우저에서 다음과 같이 실행하면 된다
>   - http://localhost:8080/basic/hello-form.html
>   - 정적 리소스는 해당 파일을 변경 없이 그대로 서비스하는 것

> **뷰 템플릿** 
> - 뷰 템플릿을 거쳐서 HTML이 생성되고, 뷰가 응답을 만들어서 전달한다
> - 일반적으로 HTML을 동적으로 생성하는 용도로 사용하지만, 다른 것들도 가능, 뷰 템플릿이 만들 수 있는 것이라면 뭐든 가능
> - 뷰 템플릿 경로
>   - src/main/resources/templates

> **HTTP 메시지**
> - @ResponseBody, HttpEntity를 사용하면 뷰 템플릿을 사용하는 것이 아니라, HTTP 메시지 바디에 직접 응답 데이터를 출력할 수 있다.

> **Thymeleaf 스프링 부트 설정**
> - 라이브러리 의존성 추가
>   - org.springframework.boot:spring-boot-starter-thymeleaf
> - 스프링 부트가 자동으로 ThymeleafViewResolver와 필요한 빈들을 등록
> - 기본 설정
>   - application.properties
>     - thymeleaf.prefix=classpath:/templates/
>     - thymeleaf.suffix=.html

> **ArgumentResolver**
> - HandlerMethodArgumentResolver는 컨트롤러 메서드의 각 파라미터 (= 매개변수)를 분석해서 적절한 값을 생성하거나 주입해주는 역할을 하는 인터페이스
> - 내부 흐름 :
>   - DispatcherServlet -> 
>   - HandlerAdapter -> 
>   - InvocableHandlerMethod -> 
>   - HandlerMethodArgumentResolverComposite -> 
>   - 각 매개변수를 지원하는 Resolver 탐색 및 실행
>   - 컨트롤러 메서드 호출
> - 모든 ArgumentResolver 목록을 순서대로 돌리면서 supportsParameter()로 "해당 파라미터 처리 여부" 확인
> - resolveArgument()를 호출해서 실제 값을 생성 -> 해당 값을 컨트롤러 메서드의 인자에 넣어줌

> **HandlerMethodReturnValueHandler**
> - 컨트롤러가 리턴 (=return)한 값을 "어떻게 응답으로 변환할지 처리"하는 컴포넌트
> - (= 컨트롤러 메서드의 반환값(= return value)을 분석해서 그것을 HTTP응답, Model, View 등으로 변환하는 역할을 하는 인터페이스)
> - 응답 흐름 :
>   - DispatcherServlet -> 
>   - HandlerMapping ->
>   - HandlerAdapter ->
>   - ArgumentResolver ->
>   - 컨트롤러 실행 (= retrun Value 생성)
>   - ReturnValueHandler -> 반환값을 ModelAndView 또는 HTTP 응답으로 변환
>   - ViewResolver -> 뷰 찾아서 렌더링

> **HTTP 메시지 컨버터 위치 :**
> - 요청의 경우 :
>   - '@RequestBody'를 처리하는 'ArgumentResolver'가 있고 'HttpEntity'를 처리하는 'ArgumentResolver'가 있다.
>   - 해당 'ArgumentResolver'들이 HTTP 메시지 컨버터를 사용해서 필요한 객체를 생성하는 것
> - 응답의 경우 :
>   - '@ResponseBody'와 'HttpEntity'를 처맇는 'ReturnValueHandler'가 있음.
>   - 여기서에서 HTTP 메시지 컨버터를 호출해서 응답 결과를 만든다
> - 스프링 MVC는 '@RequestBody' '@ResponseBody'가 있다면 'RequestResponseBodyMethodProcessor' (= ArgumentResolver)
> - 'HttpEntity'가 있으면 'HttpEntityMethodProcessor (= ArgumentResolver)'를 사 
