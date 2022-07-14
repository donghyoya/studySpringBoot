package jpabook.jpashop.domian;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpabook.jpashop.domian.Status.DeliveryStatus;
import jpabook.jpashop.domian.Status.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) //1:n에서 n부분이 orders 이기에 FK
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    //이쪽을 FK로 잡았다(1:1 매핑이기에 둘중 하나는 FK로 잡아야하는데 여기에다가 잡은것이다)
    //둘중 하나만 선택하면된다
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //1:1 매핑
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDateTime; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문 상태 [ORDER, CANCEL]

    //연관관계 메서드
    //@OneToMany 에서 Many부분
    public void setMember(Member member){
        this.member = member;
        member.getOrder().add(this);
    }
    //@OneToMany에서 One 부분
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    //OnetoOne에서 One부분
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //생성 메서드
    //실무에서 더 복잡하다
    public static Order creatOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order(); //Order 생성
        order.setMember(member); //주문자내용 추가(연관관계있기에 Member테이블에도 자동적으로 생성된다)
        order.setDelivery(delivery); //배달 내용추가 (연관관계에 있기에 자동적으로 delivery에도 생성된다)
        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);//연관관계 있기에 OrderItem테이블에도 추가된다
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDateTime(LocalDateTime.now());
        return order;
    }

    //비즈니스 로직
    /**
     * 주문취소
     */
    public void cencel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다");
        }
        this.setStatus(OrderStatus.CANCEL);//주문 상태 변경
        for(OrderItem orderItem : this.orderItems){
            orderItem.cancel();//캔슬되면 제고 수량이 증가해야한다
        }
    }

    //==조회 로직

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
       int totalPrice = 0;
       for(OrderItem orderItem : this.orderItems){
           totalPrice += orderItem.getTotalPrice();//각 제품의 갯수 * 가격을 가져온다
       }
        return totalPrice;
    }
}
