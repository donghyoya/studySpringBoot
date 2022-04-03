package jpabook.jpashop.domian;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //1:n 에서 n에 해당하다 FK이다
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    //생성 메서드
    //item안에 가격이 있지만 할인같은 경우가 있으니 따로 추가할때 가격도 받는다
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item); //주문한 아이템
        orderItem.setOrderPrice(orderPrice);// 아이템의 가격
        orderItem.setCount(count);//주문한 아이템의 갯수

        item.removeStock(count);//해당 아이템 제고 줄어들기
        return orderItem;
    }

    //비즈니스 로직
    public void cancel() {//취소되었으니 제고 수량 증가
        getItem().addStock(this.count);
    }

    //조회 로직

    /**
     * 주문상품 전체 가격조회
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
