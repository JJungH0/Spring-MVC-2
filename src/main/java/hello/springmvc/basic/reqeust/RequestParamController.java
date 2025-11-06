package hello.springmvc.basic.reqeust;

import hello.springmvc.basic.requestMapping.data.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * HTTP 요청 메서드에 상관없이 모든 요청 메서드를 처리할 수 있다. (= GET, POST, PUT)
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().println("ok");

    }

    /**
     * @RequestParam 사용 :
     * - 파라미터 이름으로 바인딩 (= 인자명이 같으면 생략가능)
     * @ResponseBody 추가 :
     * - ViewResolver를 무시하고, HTTP message Body에 직접 해당 내용 입력
     */
    @RequestMapping("/request-param-v2")
    @ResponseBody
    public String requestParamV2(@RequestParam("username") String name,
                                 @RequestParam("age") int age) {

        log.info("username = {}, age = {}", name, age);
        return "ok";
     }

    /**
     * @RequestParam 사용 :
     * String, int 등의 단순 타입이면 @RequestParam도 생략 가능
     * @return
     */
     @RequestMapping("/request-param-v3")
     @ResponseBody
    public String requestParamV3(String username,
                                 int age) {
         log.info("username = {}, age = {}", username, age);

         return "ok";
    }


    /**
     * @RequestParam(required = ?) :
     * - 기본값은 true
     * - 필수적으로 입력값을 받아야함.
     * - 선택적이라면 false로 설정
     *
     * /request-param-required?username=
     * - 빈문자로 통과
     *
     * /request-param-required
     * - int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함
     * - (또는 다음에 나오는 defaultValue 사용)
     */
    @RequestMapping("/request-param-required")
    @ResponseBody
    public String requestParamRequired(@RequestParam String username,
                                       @RequestParam(required = false) Integer age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    /**
     * @RequestParam
     * - defaultValue 사용
     * - defaultValue는 빈 문자의 경우에도 사용
     * - /request-param-default?username=
     * - 대신 명시적으로 빈 문자 ""를 보낼시 적용 안됨
     */
    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                      @RequestParam(required = false, defaultValue = "-1") Integer age) {
        log.info("username = {}, age = {}", username, age);
        return "OK";
    }

    /**
     * @RequestParam Map, MultiValueMap :
     * - Map(key=value)
     * - MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @RequestMapping("/request-param-map")
    @ResponseBody
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }


    /**
     * @ModelAttribute 사용 :
     * - model.addAttribute(helloData) 코드도 함께 자동 적용됨
     * 동작 과정 :
     * - HelloData 객체를 생성한다
     * - 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다
     * - 그리고 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력 (= 바인딩) 한다
     * - ex) 파라미터 이름이 username이면 setUsername() 메서드를 찾아서 호출하면서 값을 입력한다.
     * 프로퍼티 (= Property)
     * - getXxx(), setXxx()
     * - 객체에 getUsername(), setUsername() 메서드가 존재 시
     * - 해당 객체는 username이라는 프로퍼티를 가지고 있다.
     * - username 프로퍼티의 값을 변경하면 setUsername()이 호출되고,
     * - username 포로퍼티의 값을 조회하면 getUsername()이 호출된다.
     * 바인딩 오류
     * - age=abc 처럼 숫자가 들어가야 할 곳에 문자열을 넣으면 BindException이 발생한다.
     */
    @RequestMapping("/model-attribute-v1")
    @ResponseBody
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "OK";
    }

    /**
     * @ModelAttribute 생략 가능 :
     * - String, int 같은 단순 타입 = @RequestParam
     * - argument resolver로 지정해둔 타입 외 = @ModelAttribute
     * 스프링은 해댱 생략시 다음과 같은 규칙을 적용 :
     * - 'String', 'int', 'Integer' 같은 단순 타입 = @RequestParam
     * - 나머지 = @ModelAttribute (= argument resolver로 지정해둔 타입 외)
     * @return
     */
    @RequestMapping("/model-attribute-v2")
    @ResponseBody
    public String modelAttributeV2(HelloData helloData) {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}",helloData);
        return "OK@";
    }
}
