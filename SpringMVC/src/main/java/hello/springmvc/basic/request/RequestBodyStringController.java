package hello.springmvc.basic.request;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @RequestMapping(value = "/request-body-string-v1", method = RequestMethod.POST)
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody);

        response.getWriter().write("ok request body v1");
    }

    @RequestMapping(value = "/request-body-string-v2", method = RequestMethod.POST)
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        //ServletInputStream inputStream = request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody);

        responseWriter.write("ok request body v2");
    }

    @RequestMapping(value = "/request-body-string-v3", method = RequestMethod.POST)
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

//        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        String body = httpEntity.getBody(); //http의 body있는것을 꺼내온다
        HttpHeaders header = httpEntity.getHeaders();
        log.info("messageBody={}",body);
        log.info("messageHeader={}",header);

        return new HttpEntity<>("ok request body httpEntity v3");
    }

    @ResponseBody
    @RequestMapping(value = "/request-body-string-v4", method = RequestMethod.POST)
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {


        log.info("messageBody={}",messageBody);

      return "ok request body v4";
    }
}
