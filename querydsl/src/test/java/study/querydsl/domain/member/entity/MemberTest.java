package study.querydsl.domain.member.entity;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import study.querydsl.domain.team.entity.QTeam;
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

    JPAQueryFactory jpaQueryFactory;

    QMember qMember;
    QTeam qTeam;

    @BeforeEach
    public void befor(){
        jpaQueryFactory = new JPAQueryFactory(em);

        qMember = new QMember("qMember");
        qTeam = new QTeam("qTeam");
    }

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

    @Test
    public void querydslSearch(){
        QMember m = new QMember("m");
        Member findMember = jpaQueryFactory
                .selectFrom(m)
                .where(m.username.eq("member1")
                        .and(m.age.eq(10)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");

    }

    @Test
    public void querydslSearch2(){
        QMember m = new QMember("m");
        Member findMember = jpaQueryFactory
                .selectFrom(m)
                .where(m.username.eq("member1")
                        .and(m.age.between(9, 15)))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void querydslSearch3(){
        //and 대신 , 으로 가능
        QMember m = new QMember("m");
        Member findMember = jpaQueryFactory
                .selectFrom(m)
                .where(m.username.eq("member1"),
                        m.age.between(9, 15))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void querydslSearchList(){
        QMember m = new QMember("m");

        //쿼리 동작 1
        List<Member> member1 = jpaQueryFactory
                .selectFrom(m)
                .fetch();

        System.out.println("member = " + member1);

//        Member member1 = jpaQueryFactory
//                .selectFrom(m)
//                .fetchOne();
//        System.out.println("member1 = " + member1);
        //쿼리 동작 2
        Member member2 = jpaQueryFactory
                .selectFrom(m)
                .fetchFirst();
        System.out.println("member2 = " + member2);

        //쿼리 동작 3
        QueryResults<Member> memberResults = jpaQueryFactory
                .selectFrom(m)
                .fetchResults();

        System.out.println("memberResults = " + memberResults);
        //쿼리 동작 4
        System.out.println("memberResults.getResults() = " + memberResults.getResults());
        System.out.println("memberResults.getTotal() = " + memberResults.getTotal());


        long count = jpaQueryFactory
                .selectFrom(m)
                .fetchCount();
        System.out.println("count = " + count);
    }

    /**
     * 회원 정렬 순서
     * 1. 회원 나이 내림차순(desc)
     * 2. 회원 이름 올림차순(asc)
     * 단 2 에서 회원 이름이 없으면 마지막에 출력(nulls last)
     */
    @Test
    public void querydslSort(){
        QMember m = new QMember("m");

        em.persist(new Member(null,100));
        em.persist(new Member("member10",100));
        em.persist(new Member("member11",100));

        List<Member> members = jpaQueryFactory
                .selectFrom(m)
                .where(m.age.eq(100))
                .orderBy(m.age.desc(),
                        m.username.asc().nullsLast())
                .fetch();
        for(Member member2 : members){
            System.out.println("member = " + member2);
        }
    }

    @Test
    public void querydslPaging(){
        QMember m = new QMember("m");
        List<Member> members = jpaQueryFactory
                .selectFrom(m)
                .orderBy(m.username.desc())
                .offset(2)  //2번째 부터 시작한다
                .limit(4)   //4번째까지만 가져오겠다
                .fetch();

        for(Member member2: members){
            System.out.println("member = " + member2);
        }
    }

    @Test
    public void querydslPaging2(){
        QMember m = new QMember("m");

        QueryResults<Member> results = jpaQueryFactory
                .selectFrom(m)
                .orderBy(m.username.desc())
                .offset(1)
                .limit(2)
                .fetchResults();

//        assertThat(results.getTotal()).isEqualTo(4);
//        assertThat(results.getLimit()).isEqualTo(2);
//        assertThat(results.getOffset()).isEqualTo(1);
//        assertThat(results.getResults().size()).isEqualTo(2);

    }

    @Test
    public void querydslGroupby(){
        List<Tuple> result = jpaQueryFactory
                .select(
                        qMember.count(),
                        qMember.age.sum(),
                        qMember.age.max(),
                        qMember.age.avg(),
                        qMember.age.min()
                )
                .from(qMember)
                .fetch();

        Tuple get0 = result.get(0);
        assertThat(get0.get(qMember.count())).isEqualTo(5);
        assertThat(get0.get(qMember.age.sum())).isEqualTo(125);
    }

    @Test
    public void querydslGroupby2(){
        List<Tuple> tuples = jpaQueryFactory
                .select(qTeam.name, qMember.age.avg())
                .from(qMember)
                .join(qMember.team, qTeam)
                .groupBy(qTeam.name)
                .fetch();

        Tuple teamA = tuples.get(0);
        Tuple teamB = tuples.get(1);

        System.out.println("teamC = " + teamA);
        System.out.println("teamD = " + teamB);

    }

    @Test
    public void querydslJoin(){
        List<Member> members = jpaQueryFactory
                .selectFrom(qMember)
                .join(qMember.team, qTeam)
                .where(qTeam.name.eq("teamC"))
                .fetch();

        for(Member member : members){
            System.out.println("member = " + member);
        }
    }

    /**
     * theta 조인
     * 회원의 이름이 팀 이름과 같은 회원 조회
     */
    @Test
    public void querydsl_theta_Join2(){
        em.persist(new Member("teamC"));
        em.persist(new Member("teamD"));

        //outer 조인 불가능(inner 조인반대)
        List<Member> members = jpaQueryFactory
                .select(qMember)
                .from(qMember, qTeam)
                .where(qMember.username.eq(qTeam.name)) //회원 이름과 팀 명 같은것을 출력
                .fetch();



        for(Member member: members){
            System.out.println("member = " + member);
        }
    }
}
