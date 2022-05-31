package jpabook.jpashop.Api;

import jpabook.jpashop.domian.Address;
import jpabook.jpashop.domian.Order;
import jpabook.jpashop.domian.OrderItem;
import jpabook.jpashop.domian.OrderSearch;
import jpabook.jpashop.domian.Status.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.query.OrderFlatDto;
import jpabook.jpashop.repository.query.OrderQueryDto;
import jpabook.jpashop.repository.query.OrderQueryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @RequestMapping(value = "/api/v1/orders", method = RequestMethod.GET)
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        //연관관계 있는 속성들을 강제 초기화 시킨거다
        for(Order order : all){
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            for(OrderItem orderItem : orderItems){
                orderItem.getItem().getName();
            }
        }
        return all;
    }
    @RequestMapping(value = "/api/v2/orders", method = RequestMethod.GET)
    public List<OrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> orderDtos = new ArrayList<>();
        for(Order order : orders){
            OrderDto orderDto = new OrderDto(order);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @RequestMapping(value = "/api/v3/orders", method = RequestMethod.GET)
    public List<OrderDto> ordersV3(){
        //findAllWithItem의 쿼리처럼하면 데이터가 뻥튀기된다
        //일대 다에서 DB에서는 다에 조건에 맞춰서 DB를 뻥튀기 시킨다
        List<Order> orders = orderRepository.findAllWithItem();
        List<OrderDto> orderDtos = new ArrayList<>();
        for(Order order : orders){
            OrderDto orderDto = new OrderDto(order);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @RequestMapping(value = "/api/v3.1/orders", method = RequestMethod.GET)
    public List<OrderDto> ordersV3_Page(@RequestParam(value = "offset", defaultValue = "0")int offset,
                                        @RequestParam(value = "limit",defaultValue = "100") int limit){

        //이부분은 ToOne 관계이기에 이대로 내버려둬야한다
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);

        List<OrderDto> orderDtos = new ArrayList<>();
        for(Order order : orders){
            OrderDto orderDto = new OrderDto(order);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @RequestMapping(value = "/api/v4/orders", method = RequestMethod.GET)
    public List<OrderQueryDto> ordersV4(){
        List<OrderQueryDto> orderQueryDtos = orderQueryRepository.findOrderQueryDtos();
        return orderQueryDtos;
    }

    @RequestMapping(value = "/api/v5/orders", method = RequestMethod.GET)
    public List<OrderQueryDto> ordersV5(){
        List<OrderQueryDto> orderQueryDtos = orderQueryRepository.findAllByDto_optimization();

        return orderQueryDtos;
    }

    @RequestMapping(value = "/api/v6/orders", method = RequestMethod.GET)
    public List<OrderFlatDto> ordersV6(){
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();

        //사용자가 직접 거르는 Loop를 해야한다

        return flats;
    }

    @Getter
    static class OrderDto{

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus status;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDateTime();
            status = order.getStatus();
            address = order.getDelivery().getAddress();
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(toList());
        }
    }

    @Getter
    static class OrderItemDto{
        //여기서 원하는 컬럼값만 선택하여 리턴가능하다
        private String itemName;//상품명
        private int orderPrice;//상품가격
        private int count;//상품갯수

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }
}
