package hello.demo.web;

import hello.demo.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import hello.demo.web.LogDemoService;
@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService = new LogDemoService();
    private final MyLogger myLogger = new MyLogger();

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        //  MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.log("controller test");
        Thread.sleep(100);
        logDemoService.logic("testID");
        return "OK";
    }
}
