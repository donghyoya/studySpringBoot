package jpabook.jpashop.repository;

import jpabook.jpashop.domian.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    //em 인잭션한다, 단 스프링 부트 사용하면 Autowired도 사용가능하다
    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        Member member =  em.find(Member.class, id);//이때는 PK만 가능하다(중복이 존재하지 않기때문이다

        return member;
    }

    public List<Member> findAll(){
        //맴버의 대한 Entity 객체의 대해 조회를 한다
        List<Member> members = em.createQuery("select m from Member m",Member.class)
                .getResultList();
        return members;
    }

    public List<Member> findByName(String name){
        List<Member> members = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
        return members;
    }
}
