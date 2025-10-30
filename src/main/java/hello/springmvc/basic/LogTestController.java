package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController :
 * - @Controller는 반환 값이 String 뷰 이름으로 인식
 * - 그래서 뷰를 찾고 렌더링 됨
 * - @RestController는 반환 값으로 뷰를 찾지 않음
 * - HTTP 메시지 바디에 바로 입력, 따라서 실행 결과로 문자열 그 자체를 받음
 */
@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        /**
         * 등급 순 :
         * - 운영상황에서는 info 이상
         * - 개발상황에서는 debug
         */
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name);
        log.info("log info = {}", name);
        log.warn("warn log = {}", name);
        log.error("error log = {}", name);

        return "OK";
    }
}
