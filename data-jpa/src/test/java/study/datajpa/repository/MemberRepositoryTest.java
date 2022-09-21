package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    @Rollback(value = true)
    public void testMember(){
        Member member = new Member("member22",20);

        Member savedMember = memberRepository.save(member);

        Optional<Member> byId = memberRepository.findById(savedMember.getId());

        Member member1 = byId.get();

        Assertions.assertThat(savedMember.getId()).isEqualTo(member1.getId());
    }

    @Test
    @Rollback(value = true)
    public void testQuery(){
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findUser("AAA", 10);

        assertThat(member1).isEqualTo(result.get(0));
    }

    @Test
    @Rollback(value = true)
    public void findMemberDto(){
        Team team = new Team("TestTeam1");
        teamRepository.save(team);

        Member member1 = new Member("testname1", 10);
        member1.setTeam(team);
        memberRepository.save(member1);

        List<MemberDto> memaberDto = memberRepository.findMemaberDto();
        for(MemberDto dto : memaberDto){
            System.out.println("dto = " + dto);
        }
    }

    @Test
    @Rollback(value = true)
    public void testfindByNames(){
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<String> names = new ArrayList<String>(Arrays.asList("AAA","BBB"));
        List<Member> byNames = memberRepository.findByNames(names);

        for(Member member : byNames){
            System.out.println("name = " + member);
        }


    }
}