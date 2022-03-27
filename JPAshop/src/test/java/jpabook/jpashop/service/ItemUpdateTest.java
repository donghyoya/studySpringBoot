package jpabook.jpashop.service;

import jpabook.jpashop.domian.item.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void 업데이트테스트() throws Exception{
        Book book = em.find(Book.class, 1L);


        book.setName("asdfzc");

        //변경 감지 == dirty checking
    }
}
