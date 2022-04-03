package hello.demo.web;

import hello.demo.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    MyLogger myLogger = new MyLogger();
    //public final ObjectProvider<MyLogger> myLoggerProvider;
    //-> proxymode와 같은것이다

    public void logic(String id) {
        //MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
