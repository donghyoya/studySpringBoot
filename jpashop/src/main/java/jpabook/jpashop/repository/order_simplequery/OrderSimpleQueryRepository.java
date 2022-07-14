package jpabook.jpashop.repository.order_simplequery;

import jpabook.jpashop.domian.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {
    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrderDtos(){
        //JPA는 식별자로 넣는다 o
        //이렇게하면 사용자가 원하는 컬럼만 가져온다
        //fetch 조인은 모든 컬럼값을 가져오는 문제가 발생한다
        List<OrderSimpleQueryDto> resultList = em.createQuery(
                        "select new jpabook.jpashop.repository.order_simplequery." +
                                "OrderSimpleQueryDto(o.id, m.name, o.orderDateTime, d.address, o.status)" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
        return resultList;
    }
}
