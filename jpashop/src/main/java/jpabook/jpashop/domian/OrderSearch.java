package jpabook.jpashop.domian;

import jpabook.jpashop.domian.Status.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderSearch {

    private String memberName;//회원 이름름
    private OrderStatus orderStatus;//주문 상태[ORDER, CANCEL]


}