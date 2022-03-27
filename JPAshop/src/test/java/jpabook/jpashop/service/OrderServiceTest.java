package jpabook.jpashop.service;

import jpabook.jpashop.domian.Address;
import jpabook.jpashop.domian.Item;
import jpabook.jpashop.domian.Member;
import jpabook.jpashop.domian.Order;
import jpabook.jpashop.domian.Status.OrderStatus;
import jpabook.jpashop.domian.item.Book;
import jpabook.jpashop.exception.NotEoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @Rollback
    public void 상품주문() throws Exception{
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "010-1234-2353"));
        em.persist(member);

        Item book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(),"상품 주문시 상태는 Order");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야한다");
        assertEquals(10000*orderCount, getOrder.getTotalPrice());
        assertEquals(8, book.getStockQuantity(), "주문 수량만큼 재고가 줄어야한다");
    }

    @Test
    public void 상품주문_재고초과수량(){
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "010-1234-2353"));
        em.persist(member);

        Item item = new Book();
        item.setName("시골 JPA");
        item.setPrice(10000);
        item.setStockQuantity(10);
        em.persist(item);

        int orderCount = 11;
        //when
        try {
            orderService.order(member.getId(),item.getId(),orderCount);
        }catch (NotEoughStockException e){
            return;
        }
        fail("에외 발생");


        //then
        fail("재고 수량 부족 예외가 발생해야한다");
    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "010-1234-2353"));
        em.persist(member);

        Item item = new Book();
        item.setName("시골 JPA");
        item.setPrice(10000);
        item.setStockQuantity(10);
        em.persist(item);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 Cancel이다");
        assertEquals(10,item.getStockQuantity(),"주문이 취소도니 상품은 그만큼 재고가 증가해야한다");
    }

}