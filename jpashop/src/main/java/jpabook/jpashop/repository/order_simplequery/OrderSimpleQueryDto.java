package jpabook.jpashop.repository.order_simplequery;

import jpabook.jpashop.domian.Address;
import jpabook.jpashop.domian.Order;
import jpabook.jpashop.domian.Status.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDateTime;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name,
    LocalDateTime orderDateTime, Address address, OrderStatus orderStatus){
        this.orderId = orderId;
        this.name = name;
        this.address = address;
        this.orderDateTime = orderDateTime;
        this.orderStatus = orderStatus;
    }
}