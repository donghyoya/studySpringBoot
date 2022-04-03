package hello.demo.discount;

import hello.demo.member.Member;

public interface DiscountPolicy {

    /**
     *
     * @param member
     * @param price
     * @return 할인 대상의 금액액     */
    int discount(Member member, int price);


}
