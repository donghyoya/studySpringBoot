package hello.demo.discount;

import hello.demo.member.Grade;
import hello.demo.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discoutFixAmount = 1000; //1000원 할인
    /**고민: 현재는 매개변수 값은 MEMBER, PRICE로 받는걸로 되어있지만,
     * 사실은 GRAD, PRICE으로 해도된다
     * MEMBER자체를 넘겨도 틀리지 않는다
    **/
     @Override
    public int discount(Member member, int price) {
        //고객이 VIP면 1000원 할인 아니면 할인x
        if(member.getGrade() == Grade.VIP){
            return discoutFixAmount;
        }else{
            return 0;
        }
    }
}
