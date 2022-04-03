package jpabook.jpashop.domian;

import jpabook.jpashop.domian.Status.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    //EnumType.ORDINAL은 숫자 0,1,2,3,4 가 들어간다(즉 절때 쓰지말자)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP

}
