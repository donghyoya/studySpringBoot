package jpabook.jpashop.domian;

import jpabook.jpashop.domian.Status.OrderStatus;
import jpabook.jpashop.exception.NotEoughStockException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
InheritanceType.TABLE_PER_CLASS = 각 종속하는 타입들을 각각 테이블을 생성한다
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한테이블에 다 때려박는다
@DiscriminatorColumn(name = "dtype") //각각 상속받은 값들을 타입으로 정한다
@Getter @Setter
public abstract class Item {
    /*
    데이터를 가지고있는 쪽에 비지니스 메서드가 있으면 좋다
    차라리 setter을 둬서 하는것보다 해장 비지니스 메서드를 직접 생성해서 해주는것이 좋다
     */
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //비즈니스 로직
    /*
    stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }


}
