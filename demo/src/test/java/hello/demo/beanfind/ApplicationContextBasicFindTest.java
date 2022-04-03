package hello.demo.beanfind;

import hello.demo.AppConfig;
import hello.demo.member.MemberService;
import hello.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("MemberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        //memberService가 memberServiceImpl에 있으면 참
        //alt + enter해서 static으로 둔다
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberType = ac.getBean(MemberService.class);
        Assertions.assertThat(memberType).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        //스프링에 등록되어있는 리턴타입을 확인한다 즉 instance타입을 확인하기에, 즉 함수에 설정되어있는 함수타입(interface)이아닌 return타입을본다
        //햇갈릴수가 있는것이 "함수 설정된 타입이 곧 리턴타입이냐"는 아니고 현재 모든 맴버는 MemserServiceImpl으로부터 종속이 되어있는 상황이다
        //그렇기에 함수에 지정한 타입이 MemberServiceImpl에 있으면 가장 instance파일인 Impl으로 확인하면 된다
        //하지만 구현에 의지하기에 그닥 좋은 코드라고 보기 어렵다
        MemberService memberService = ac.getBean("memberService",MemberServiceImpl.class);//Impl이기에 구현에 의존한것이다
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("빈 이름을 조회(일부로 실패)")
    void findBeanByNameX(){
        //ac.getBean("xxxx", MemberService.class);
        //MemberService xxxx = ac.getBean("xxxx", MemberService.class);


        //org.junit.jupiter.api.Assertions.assertThrows() <- 원래인데 alt+Enter함
        //아래는 오류가 발생되면 성공, 코드가 실행되는거면 실패다
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class)); //즉 성공
    }
}
