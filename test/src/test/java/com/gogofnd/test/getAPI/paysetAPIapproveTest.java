package com.gogofnd.test.getAPI;

import com.gogofnd.test.url.paysterAPI;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class paysetAPIapproveTest {

    @Autowired
    private paysterAPI paysterAPI;

    @Test
    void getAPI(){
        paysterAPI.getPost()
                .subscribe(
                        post -> System.out.println("Post: " + post),
                        error -> System.out.println("error = " + error.getMessage()),
                        () -> System.out.println("Completed")
                );
    }

    @Test
    void getAPI2(){
        String s = paysterAPI.fetchDataFromApi();
        System.out.println("s = " + s);
    }

    @Test
    void testSHA256() throws NoSuchAlgorithmException {
        String mid = "AMGC00001m";
        String ediDate = "20230325151115";
        String goodsAmt = "100000";
        String merchantKey = "NwgMwuqBhY2qgFKDwAgKnt3axUKmbY/le+Yh6SFw0f+1rBx3pU0N116FOSw/9/LSfqiRH5bBDSXGdG7ZPkpoXg==";
        String encData = mid+ediDate+goodsAmt+merchantKey;

        String mid2 = "demotest0m";
        String ediDate2 = "20210713151115";
        String goodsAmt2 = "1004";
        String merchantKey2 = "BoBwBC4hRuMxAztw9p85L7K+SB7Iswd1tdRwca7xQ2sFftC5nYAFgYkOctQ1ubHzACV0YzaWHJdqWAGZW34kPw==";
        String encData2 = mid2+ediDate2+goodsAmt2+merchantKey2;

        String answer = encrypt(encData);

        String answer2 = encrypt(encData2);

        System.out.println("answer = " + answer);

        System.out.println("answer2 = " + answer2);
    }

    private String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }
    private String bytesToHex(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for(byte b : bytes){
            builder.append(String.format("%02x",b));
        }
        return builder.toString();
    }

}
