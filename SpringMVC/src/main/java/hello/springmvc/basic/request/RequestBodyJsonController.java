package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":hello, "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/request-body-json-v1",method = RequestMethod.POST)
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}",messagebody);
        HelloData helloData = objectMapper.readValue(messagebody, HelloData.class);

        log.info("hello data= {}",helloData.toString());

        response.getWriter().write("json ok");
    }
    @ResponseBody
    @RequestMapping(value = "/request-body-json-v2",method = RequestMethod.POST)
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody = {}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        log.info("hello data= {}",helloData.toString());

        return "json ok V2";
    }

    @ResponseBody
    @RequestMapping(value = "/request-body-json-v3",method = RequestMethod.POST)
    public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException {

        log.info("hello data= {}",helloData.toString());

        return "json ok V3";
    }

    @ResponseBody
    @RequestMapping(value = "/request-body-json-v4",method = RequestMethod.POST)
    public String requestBodyJsonV4(HttpEntity<HelloData> Data) throws IOException {
        HelloData helloData = Data.getBody();
        log.info("username = {} age = {}",helloData.getUsername(), helloData.getAge());
        log.info("hello data= {}",helloData.toString());

        return "json ok V4";
    }

    @ResponseBody
    @RequestMapping(value = "/request-body-json-v5",method = RequestMethod.POST)
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {

        log.info("username = {} age = {}",helloData.getUsername(), helloData.getAge());
        log.info("hello data= {}",helloData.toString());

        return helloData;
    }
}
