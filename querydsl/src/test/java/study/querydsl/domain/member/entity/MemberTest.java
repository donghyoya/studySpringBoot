package study.querydsl.domain.member.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import study.querydsl.domain.team.entity.Team;
import study.querydsl.domain.member.entity.Member;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = true)
    public void testEntity(){
        Team teamC = new Team("teamC");
        Team teamD = new Team("teamD");

        Member member = new Member();

        em.persist(teamC);
        em.persist(teamD);

        Member member1 = new Member("member1",10,teamC);
        Member member2 = new Member("member2",20,teamC);

        Member member3 = new Member("member3",30,teamD);
        Member member4 = new Member("member4",40,teamD);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        //초기화
        em.flush();
        em.clear();

        List<Member> selectMFromMemberM = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        System.out.println("selectMFromMemberM = " + selectMFromMemberM);
    }

    @Test
    public void startJPQL() {
        String jpql = "select m from Member m where m.username = :username";
        Member member1 = em.createQuery(jpql, Member.class)
                .setParameter("username", "member3")
                .getSingleResult();

        System.out.println("member1 = " + member1);

        assertThat(member1.getUsername()).isEqualTo("member3");
    }

    @Test
    public void startQueryDSL(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMember m = new QMember("m");

        Member member1 = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1"))
                .fetchOne();

        System.out.println("member1 = " + member1);

        assertThat(member1.getUsername()).isEqualTo("member1");
    }

}
