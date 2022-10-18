package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = true)
    public void testEntity(){
        Team teamA = new Team("teamC");
        Team teamB = new Team("teamD");
        Team teamC = new Team();

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("memberp", 10, teamA);
        Member member2 = new Member("membero", 20, teamA);
        Member member3 = new Member("memberi", 30, teamB);
        Member member4 = new Member("memberu", 40, teamB);
        Member member5 = new Member("membery", 50, teamA);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        //초기화
        em.flush();//영속성 공간에 있는 데이터를 강제로 날린다 DB로
        em.clear();//영속성 공간에 있는 데이터를 강제로 지운다(JPA에 있는 캐쉬들을 날려버린다)

        //확인
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        for (Member member : members) {
            System.out.println("member = " + member.toString());
            System.out.println("member.getTeam = " + member.getTeam());
        }
    }

    @Test
    public void JpaEventBaseEntity() throws Exception{
        //given
        Member memberTest1 = new Member("memberTest1",20);

        memberRepository.save(memberTest1);//@PrePersist 발생

        Thread.sleep(100);
        memberTest1.setUsername("memberTest2");

        em.flush();//@PreUpdate
        em.clear();

        Member findmember = memberRepository.findById(memberTest1.getId()).get();
        System.out.println("findmember.create = " + findmember.getCreatedDate());
        System.out.println("findmember.Update = " + findmember.getUpdatedDate());
        System.out.println("findmember = " + findmember);

    }
}