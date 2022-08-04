package study.datajpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

    public void delect(Member member){
        em.remove(member);
    }

    public List<Member> findAll(){
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return members;
    }

    public Optional<Member> findbyId(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public Optional<Member> findbyName(String username){
        List<Member> findNameList = em.createQuery("select m from Member m" +
                        " where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
        Member findMember = null;
        if(findNameList.size() < 2){
            findMember = findNameList.get(0);
        }
        return Optional.ofNullable(findMember);
    }

    public Long count(){
        Long cnt = em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
        return cnt;
    }
}
