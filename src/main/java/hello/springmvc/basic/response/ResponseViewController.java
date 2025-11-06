package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseView1() {

        return new ModelAndView("/response/hello")
                .addObject("data", "hello");
    }

    /**
     * @ResponseBody가 존재하지 않을 시 :
     * - response/hello로 뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링 한다
     * - @ResponseBody가 있으면 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 response/hello라는 문자가 입력됨
     */
    @RequestMapping("/response-view-v2")
    public String responseView2(Model model) {
        model.addAttribute("data", "hello");
        return "/response/hello";
    }

    /**
     * Void를 반환하는 경우 :
     * - @Controller를 사용하고, HttpServletResponse, QutputStream(Writer)같은 HTTP 메시지 바디를
     *      처리하는 파라미터가 존재하지 않다면 요청 URL을 참고해서 논리 뷰 이름으로 사용
     * - 요청 URL: /response/hello
     * - 실행: templates/response/hello.html
     * - 참고로 해당 방법은 명시성이 너무 떨어져서 권장 X
     * @param model
     */
    @RequestMapping("/response/hello")
    public void responseV3(Model model) {
        model.addAttribute("data", "hello");
    }
}
