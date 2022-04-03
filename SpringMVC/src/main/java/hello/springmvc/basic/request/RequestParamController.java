package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}",username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ){
        /**
         * 결국 request.getParameter("username"), request.getParameter("age");
         * 와 같은 효과를 일으킨다
         */
        log.info("username = {}, age = {}",memberName, memberAge);

        return "ok v2";
    }
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ){
        /**
         * 결국 request.getParameter("username"), request.getParameter("age");
         * 와 같은 효과를 일으킨다
         * 위처럼 간단하게 하는건 가능하지만 변수명을 해당 값과 같은 명으로 해야한다
         */
        log.info("username = {}, age = {}",username, age);

        return "ok v3";
    }
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age){
        /**
         * 위처럼 가능하다,
         * 대신 같은 조건으로 요청 파라미터와 같은 이름으로 변수명을 해야한다
         */
        log.info("username = {}, age = {}",username, age);

        return "ok v4";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer age
    ){
        /**
         * required가 false면 들어오지 않아도 된다
         * 기본값은 true이므로 무족곤 작성해야한다
         * 하지만 int로 두면 null이 들어가지 않기때문에 Integer로 두자
         * 추가로 null이랑 ""은 다르다
         */
        log.info("username = {}, age = {}",username, age);
        return "ok required";
    }
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ){
        /**
         * defaultValue는 값이 안들어올때 기본 값으로 설정하는 값들이다
         * 즉 위는 required = false 둘때 null이 자동으로 들어오지만
         * default는 null대신 사용자가 정한 값으로 둔다
         * 즉 default하면 required는 필요가 없어진다
         * 추가로 ""도 가능하다, 빈문자도 가능하다
         */
        log.info("username = {}, age = {}",username, age);
        return "ok default";
    }
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap
    ){
        /**
         * 파라미터의 값이 1개가 확실하다면 Map
         * 즉 Object값이 key당 1개씩 각각 있으면 Map
         * 그 이상일경우
         * 예: username=userA&username=userB 은 2개이기에 Map으로 안됀다
         */
        log.info("username = {}, age = {}",paramMap.get("username"), paramMap.get("age"));
        return "ok paramMap";
    }

    @ResponseBody
    @RequestMapping(value = "/model-attribute-v1")
    public String modelAttributeV1(
//            @RequestParam(defaultValue = "guest") String username,
//            @RequestParam(defaultValue = "-1") int age
            @ModelAttribute HelloData data
    ){
//        HelloData data = new HelloData();
//        data.setUsername(username);
//        data.setAge(age);

        log.info("data = {}",data.toString());
        return "ok attribute v1";
    }
    @ResponseBody
    @RequestMapping(value="/model-attribute-v2")
    public String modelAttributeV2 (HelloData helloData){
        log.info("data = {}", helloData.toString());
        return "ok attribute v2";
    }
}
