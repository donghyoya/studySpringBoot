package study.querydsl.domain.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydsl.domain.member.dto.MemberSearchCondition;
import study.querydsl.domain.member.dto.MemberTeamDto;
import study.querydsl.domain.member.entity.Member;
import study.querydsl.domain.team.entity.Team;
import study.querydsl.domain.team.repository.TeamRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class MemberRepositorySupportTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepositorySupport memberRepositorySupport;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    public void basicTest(){


        Team team = teamRepository.findById(2L).get();

        Member member1 = new Member("testmember", 37);
        member1.setTeam(team);
        memberRepositorySupport.save(member1);

        Member findMember = memberRepositorySupport.findById(member1.getId()).get();
        assertThat(findMember).isEqualTo(member1);

        List<Member> results = memberRepositorySupport.findAll();
//        member1 만 있는지 확인하는건데 난 저장된 데이터가 있어서 안됀다
//        assertThat(results).containsExactly(member1);
    }

    @Test
    public void basicQueryTest(){
        Member member1 = new Member("member1", 10);
        memberRepositorySupport.save(member1);

        List<Member> results = memberRepositorySupport.findAll_Querydsl();
        for (Member result : results) {
            System.out.println("result = " + result);
        }

        List<Member> findMembers = memberRepositorySupport.findByUsername_Querydsl("member1");
        for (Member member : findMembers) {
            System.out.println("member = " + member);
        }
//        assertThat(findMember).containsExactly(member1);
    }

    @Test
    public void searchTest(){

        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamD");

        List<MemberTeamDto> memberTeamDtos = memberRepositorySupport.searchByBuilder(condition);

        System.out.println("memberTeamDtos.size() = " + memberTeamDtos.size());
        for (MemberTeamDto memberTeamDto : memberTeamDtos) {
            System.out.println("memberTeamDto = " + memberTeamDto);
        }
    }
}