package study.querydsl.domain.member.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.querydsl.domain.member.dto.MemberSearchCondition;
import study.querydsl.domain.member.dto.MemberTeamDto;
import study.querydsl.domain.member.dto.QMemberTeamDto;
import study.querydsl.domain.member.entity.Member;
import study.querydsl.domain.member.entity.QMember;
import study.querydsl.domain.team.entity.QTeam;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
//@RequiredArgsConstructor
public class MemberRepositorySupport {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    QMember qMember = QMember.member;
    QTeam qTeam = QTeam.team;

    public MemberRepositorySupport(EntityManager em){
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(Member member){
        em.persist(member);
    }

    public Optional<Member> findById(Long id){
        Member findMember = em.find(Member.class,id);
        return Optional.ofNullable(findMember);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findAll_Querydsl(){
        return queryFactory
                .select(qMember)
                .from(qMember)
                .fetch();
    }

    public List<Member> findByUsername(String username){
        return em.createQuery("select m from Member m where m.username = :username",Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Member> findByUsername_Querydsl(String username){
        return queryFactory
                .select(qMember)
                .from(qMember)
                .where(qMember.username.eq((username)))
                .fetch();
    }

    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition){

        BooleanBuilder builder = new BooleanBuilder();

        if(StringUtils.hasText(condition.getUsername())){
            builder.and(qMember.username.eq(condition.getUsername()));
        }
        if (StringUtils.hasText(condition.getTeamName())){
            builder.and(qTeam.name.eq(condition.getTeamName()));
        }
        if(condition.getAgeGoe() != null){
            builder.and(qMember.age.goe(condition.getAgeGoe()));
        }
        if(condition.getAgeLoe() != null){
            builder.and(qMember.age.loe(condition.getAgeLoe()));
        }

        return queryFactory
                .select(new QMemberTeamDto(
                        qMember.id.as("memberId"),
                        qMember.username,
                        qMember.age,
                        qTeam.id.as("teamId"),
                        qTeam.name.as("teamName")
                ))
                .from(qMember)
                .leftJoin(qMember.team, qTeam)
                .where(builder)
                .fetch();
    }
}
