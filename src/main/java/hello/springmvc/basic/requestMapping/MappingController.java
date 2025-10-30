package hello.springmvc.basic.requestMapping;

import hello.springmvc.basic.requestMapping.data.JsonData;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 대부분의 속성을 '배열[]'로 제공하므로 다중 설정이 가능
     */
    @GetMapping({"/hello-basic", "hello-go"})
    public String helloBasic() {
        log.info("helloBasic");
        return "OK";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "OK";
    }

    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "OK";
    }

    /**
     * PathVariable 사용 :
     * 변수명이 같으면 생략 가능 :
     * - @PathVariable("userId") String userId -> @PathVariable String userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String userId) {
        log.info("mappingPath userId={}", userId);
        return "OK";
    }

    /**
     * PathVariable 다중 사용 :
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId,
                              @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "OK";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "OK";
    }

    /**
     * 특정 헤더로 추가 매핑 :
     * headers="mode",
     * headers="!mode",
     * headers="mode=debug"
     * headers="mode!=debug"
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "OK";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type :
     * consumes = "application/json"
     * consumes = "!application/json"
     * consumes = "application/*"
     * consumes = "*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     * 객체 매핑 (= 역직렬화)
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes(@RequestBody JsonData jsonData) {

        log.info("mappingConsumes = {}", jsonData.getData());

        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type :
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
