package com.gogofnd.test.url;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class paysterAPI {

    private final WebClient webClient;

    @Autowired
    public paysterAPI(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<String> getPost(){
        return webClient.get()
                .uri("https://api.payster.co.kr/r")
                .retrieve()
                .bodyToFlux(String.class);
    }

    public String fetchDataFromApi(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.payster.co.kr/";
        return restTemplate.getForObject(url,String.class);
    }
}
