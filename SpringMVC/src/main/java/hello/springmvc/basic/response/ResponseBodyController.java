package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    @RequestMapping(value = "/response-body-string-v1",method = RequestMethod.GET)
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok v1");
    }

    @RequestMapping(value = "/response-body-string-v2",method = RequestMethod.GET)
    public ResponseEntity<String> responseBodyV2( ) throws IOException {
        return new ResponseEntity<>("ok v2", HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/response-body-string-v3",method = RequestMethod.GET)
    public String responseBodyV3() throws IOException {
        return "ok v3";
    }

    @RequestMapping(value = "/response-body-json-v1",method = RequestMethod.GET)
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);//httpstatus의 상태코드를 바꿀수있다
    }

    @ResponseStatus(HttpStatus.OK) //이렇게 설정해야한다
    @ResponseBody
    @RequestMapping(value = "/response-body-json-v2",method = RequestMethod.GET)
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData; //httpstatus의 상태코드를 변경이 불가능
    }


}
