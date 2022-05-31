//package jpabook.jpashop;
//
//
//import jpabook.jpashop.domian.*;
//import jpabook.jpashop.domian.Status.MemberStatus;
//import jpabook.jpashop.domian.item.Book;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//
//@Component
//@RequiredArgsConstructor
//public class InitDb {
//
//    private final InitService initService;
//
//    @PostConstruct
//    public void init(){
//        initService.dbInit1();
//        initService.dbInit2();
//    }
//
//    //별도의 빈을 두어서 해야한다
//    //이렇게 해야 작동이 잘된다
//    @Component
//    @Transactional
//    @RequiredArgsConstructor
//    static class InitService{
//        private final EntityManager em;
//
//        public void dbInit1(){
//            Member member = new Member();
//            member.setName("userA");
//            member.setAddress(new Address("서울", "1", "111-1"));
//            member.setStatus(MemberStatus.Buyer);
//            em.persist(member);
//
//            Book book1 = new Book();
//            book1.setName("JPA1 book");
//            book1.setPrice(10000);
//            book1.setStockQuantity(100);
//
//            Book book2 = new Book();
//            book2.setName("JPA2 book");
//            book2.setPrice(20000);
//            book2.setStockQuantity(100);
//
//            em.persist(book1);em.persist(book2);
//            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
//            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
//
//            Delivery delivery = new Delivery();
//            delivery.setAddress(member.getAddress());
//            Order order = Order.creatOrder(member, delivery, orderItem1, orderItem2);
//            em.persist(order);
//        }
//
//        public void dbInit2(){
//            Member member = new Member();
//            member.setName("userB");
//            member.setAddress(new Address("부산", "2", "222-2"));
//            member.setStatus(MemberStatus.Seller);
//            em.persist(member);
//
//            Book book1 = new Book();
//            book1.setName("SPRING1 book");
//            book1.setPrice(20000);
//            book1.setStockQuantity(200);
//
//            Book book2 = new Book();
//            book2.setName("SPRING2 book");
//            book2.setPrice(40000);
//            book2.setStockQuantity(400);
//
//            em.persist(book1);em.persist(book2);
//            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
//            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
//
//            Delivery delivery = new Delivery();
//            delivery.setAddress(member.getAddress());
//            Order order = Order.creatOrder(member, delivery, orderItem1, orderItem2);
//            em.persist(order);
//        }
//    }
//}
