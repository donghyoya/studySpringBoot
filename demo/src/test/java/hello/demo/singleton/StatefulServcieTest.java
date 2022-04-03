package hello.demo.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;


class StatefulServcieTest {
    @Test
    @DisplayName(" ")
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulServcie statfulServic1 = ac.getBean(StatefulServcie.class);
        StatefulServcie statfulServic2 = ac.getBean(StatefulServcie.class);

        //ThreadA: A사용자가 10000원 주문
        statfulServic1.order("userA",10000);
        //ThreadB: B사용자가 5000원 주문
        statfulServic2.order("userB", 5000);

        //ThreadA: 사용자A가 주문 금액 조회
//        int price = statfulServic1.getPrice();
//        System.out.println("price = " + price);
//
//        assertThat(statfulServic1.getPrice()).isEqualTo(5000);
    }
    static class TestConfig{
        @Bean
        public StatefulServcie statefulServcie(){
            return new StatefulServcie();
        }
    }

}