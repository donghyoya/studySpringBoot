package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller 이때 반환되는건 view이름이 반환 = 출력할 *.jsp 이름 예를 들면 new-form을 반환하면
// new-form.jsp를 출력한다
//근데 그냥 RestController 하면 그냥 String이 반환이 된다
@Slf4j
@RestController
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";


        log.debug("deubr log="+name); //이렇게 하면 안됀다 왜냐면 의미없는 연산이 발생하기때문이다
        log.trace("trace log={}",name); //
        log.debug("debug log={}",name); //디버그 알림
        log.info("info lo{}",name); //중요한 정보알림
        log.warn("warn log={}",name); //경고
        log.error("error log={}",name); //에러

        return "ok";
    }
}
