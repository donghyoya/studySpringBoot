package hello.demo.singleton;

import hello.demo.AppConfig;
import hello.demo.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleTonTest {

    @Test
    @DisplayName("스프링 없는 순수 자바 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1.조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할때 마다 객체를생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

    }
    @Test
    @DisplayName("싱글톤을 적용한 객체 사용")
    void singletonServiceTest(){
        //SingleTonService singletonService1 = new SingleTonService.getInstance(); 객체 생성이 안돼므로 new안됀다
        SingleTonService singletonService1 = SingleTonService.getInstance();
        SingleTonService singletonService2 = SingleTonService.getInstance();

        //둘다 출력시 같은 객체인스턴스를 반환한다
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

        // AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1.조회: 호출할 때마다 객체를 생성(위쪽은 순수 자바, 아래쪽은 스프링)
        //MemberService memberService1 = appConfig.memberService();
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);

        //2. 조회: 호출할때 마다 객체를생성(위쪽은 순수 자바, 아래쪽으 스프링
        //MemberService memberService2 = appConfig.memberService();
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        //참조값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
