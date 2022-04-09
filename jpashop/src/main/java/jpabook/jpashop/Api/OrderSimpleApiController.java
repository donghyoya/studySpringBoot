package jpabook.jpashop.Api;

import jpabook.jpashop.domian.Address;
import jpabook.jpashop.domian.Order;
import jpabook.jpashop.domian.OrderSearch;
import jpabook.jpashop.domian.Status.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.order_simplequery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order_simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * X to One 관계
 * Order
 * Order -> Member (Many to one) Order이 Many
 * Order -> Delivery (one to one)
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    //Orders 안에 Member있고, Member안에 Order있고 이 과정을 무한반복을하여 무한루프에 빠진다
    //이러한 문제를 해결할려면 각 Entity에서 서로 연관관계를 이어주는 컬럼값에다가 @JsonIgnore해야한다
    //둘중 하나만 하면된다. 즉 양방향 연관관계를 가진 모든 속성값에다가 @JsonIgnore 해야한다
    @RequestMapping(value = "/api/v1/simple-orders", method = RequestMethod.GET)
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for(Order order : all){
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress();
        }
        return all;
    }

    @RequestMapping(value = "/api/v2/simple-orders", method = RequestMethod.GET)
    public List<SimpleOrderDto> ordersV2(){
        //ORDER 2개
        //N + 1 -> 1 + 회원 N + 배송 N
        //Lazy로 두어야 성능적으로 좋다 왜냐하면 지연로딩은 영속성 컨텍스트에서 조회한다
        //이미 조회된 경우 쿼리를 생략한다
        //Member 2개 , 배달 2개 Order1개 총 5번 쿼리를 보낸다
        List<Order> allByString = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> returnValue = new ArrayList<>();
        for(Order order: allByString){
            SimpleOrderDto simpleOrder = new SimpleOrderDto(order);
            returnValue.add(simpleOrder);
        }

        return returnValue;
    }

    @RequestMapping(value = "/api/v3/simple-orders", method = RequestMethod.GET)
    public List<SimpleOrderDto> ordersV3(){
        //쿼리 1번만 나간다
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = new ArrayList<>();
        for(Order order: orders){
            SimpleOrderDto dto = new SimpleOrderDto(order);
            result.add(dto);
        }
        return result;
    }

    @RequestMapping(value = "/api/v4/simple-orders", method = RequestMethod.GET)
    public List<OrderSimpleQueryDto> ordersV4(){
        return orderSimpleQueryRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();//Lazy초기화
            address = order.getDelivery().getAddress();//Lazy초기화
            orderDate = order.getOrderDateTime();
            orderStatus = order.getStatus();
        }
    }
}
