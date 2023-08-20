package study.querydsl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

	@Autowired
	EntityManager em;

//	@Test
//	void contextLoads() {
//
//		Hello hello = new Hello();
//		em.persist(hello);
//
//		JPAQueryFactory query = new JPAQueryFactory(em);
//
//		QHello qHello = QHello.hello;
//
//		Hello result = query
//				.selectFrom(qHello)
//				.fetchOne();
//
//		Assertions.assertThat(result).isEqualTo(hello);
//		Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
//	}


}
