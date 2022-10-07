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

    public List<Member> findByUsernameAndAgeGreaterThen(String username, int age){
        return em.createQuery("select m from Member m " +
                "where m.username = :username " +
                "and m.age > :age",Member.class)
                .setParameter("username",username)
                .setParameter("age",age)
                .getResultList();
    }

    public List<Member> namedQueryfindByUsername(String username){
        return em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Member> findByPage(int age, int offset, int limit){
        List<Member> returnAge = em.createQuery("select m from Member m where m.age = :age" +
                        " order by m.username desc", Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
        return returnAge;
    }

    public long totalCount(int age, int offset, int limit){
        Long age1 = em.createQuery("select count(m) from Member m where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
        return age1;
    }

    public int bulkAgePluse(int age){
        int resultCount = em.createQuery("update Member m set m.age = m.age + 1 " +
                        "where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();
        return resultCount;
    }
}
