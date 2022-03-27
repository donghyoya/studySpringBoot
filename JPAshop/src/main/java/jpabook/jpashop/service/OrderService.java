package jpabook.jpashop.service;

import jpabook.jpashop.domian.*;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문생성
        Order order = Order.creatOrder(member, delivery, orderItem);

        //주문 저장
        /**
         * 이미 Order내에서 CasecadeType.All로
         * 두어서 OrderItem와 Delivery는 동시에 persist되어서 추가가된다
         */
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 가져오기
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cencel();

        //JPA의 강점은 Entity안에서 바뀌는 함수(비즈니스 로직 함수) 짜면 JPA에서 알아서 해준다
        //변경되어 감지가 일어나서 변경된 로직을 감지하여 알아서 DB에 업데이트가 날라간다
    }

//    주문 검색
    @Transactional(readOnly = true)
    public List<Order> findOrders(OrderSearch orderSearch){
        List<Order> allByString = orderRepository.findAllByString(orderSearch);
        return allByString;
    }
}
