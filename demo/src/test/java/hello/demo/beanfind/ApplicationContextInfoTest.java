package hello.demo.beanfind;

import hello.demo.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllbean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter 하고 탭
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            //soutv하고 탭
            System.out.println("Name = " + beanDefinitionName + " object = " + bean);
        }
    }
    @Test
    @DisplayName("Application 빈 출력하기")
    void findApplicationbean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //iter 하고 탭
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //스프링 내부에서 등록한것이 아니라 작성자가 만든 class출력
            //Role Role_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링 내부에서 사용 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                //sotv
                System.out.println("Name = " + beanDefinitionName +" object = "+bean);
            }
        }
    }
}
