package hello.demo.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출 url = " + url);

    }
    public void setUrl(String url){
        this.url = url;
    }
    //서비스 시작시 호출
    public void connect(){
        System.out.println("Connect = " + url);
    }
    public void call(String messege){
        System.out.println("url = "+url+"messege = " + messege);
    }
    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: " + url);
    }
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }
    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }


//    @Override
//    public void afterPropertiesSet() throws Exception { //빈 소멸전 콜백이다
//        connect();
//        call("초기화 연결 메시지 = afterPropertiesSet");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("Network.disconnect");
//        disconnect();
//    }


}
