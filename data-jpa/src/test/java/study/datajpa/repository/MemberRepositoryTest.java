package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    EntityManager em;

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

        List<MemberDto> memaberDto = memberRepository.findMemberDto();
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

    @Test
    @Rollback(value = true)
    public void returnType(){

        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> find1 = memberRepository.findListByUsername("AAA");
        //자바에서 리스트는 빈값도 제공하기에 null이 존재하지 않는다
        Member find2 = memberRepository.findMemberByUsername("AAA");
        //원래라면 exception 터져야하는데 스프링부트에서 trycatch에서 NoResultException 잡아준다
        Optional<Member> find3 = memberRepository.findObtionalByUsername("AAA");
        //자바8이상부터 Optional이용해서 처리하는게 더 용이하다 그냥해도 괜찮지만 Optional에용하자
        //근데 중복데이터가 2개이상이면 바로 NonUniqeResultException 터진다
        //(스프링에선 이 에러를 IncorrectResultSizeDataAccessException으로 변환해준다)
        //

        for(Member find : find1){
            System.out.println("find1 = " + find);
        }
        System.out.println("find2 = " + find2);
        System.out.println("find3 = " + find3.get());
    }

    @Test
    public void paging(){
        //given
        memberRepository.save(new Member("testMember1",1));
        memberRepository.save(new Member("testMember2",2));
        memberRepository.save(new Member("testMember3",3));
        memberRepository.save(new Member("testMember4",4));
        memberRepository.save(new Member("testMember5",5));

        int age = 1;
        int offset = 0;
        int limit = 3;

        //when
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
//        Page<Member> page = memberRepository.findbyAge(age, pageRequest);

        //then

//        List<Member> content = page.getContent();
//        long totalElements = page.getTotalElements();
//
//        for(Member data : content){
//            System.out.println("data = " + data);
//        }
//        System.out.println("totalElements = " + totalElements);
    }

    @Test
    @Rollback(value = true)
    public void bulkAgeTest(){

        memberRepository.save(new Member("memberTest1",10));
        memberRepository.save(new Member("memberTest2",19));
        memberRepository.save(new Member("memberTest3",21));
        memberRepository.save(new Member("memberTest4",40));
        memberRepository.save(new Member("memberTest5",45));
        memberRepository.save(new Member("memberTest6",60));
        memberRepository.save(new Member("memberTest7",71));
        memberRepository.save(new Member("memberTest8",98));

        int age = 20;
        int resultage = memberRepository.bulkAgePlus(age);
        System.out.println("resultage = " + resultage);
    }

    @Test
    public void findMemberLazy(){
        //given
        /*
        member1 -> TeamA
        member2 -> TeamB
         */

        Team teamA = teamRepository.findById(1L).get();
        Team teamB = teamRepository.findById(2L).get();

        Member member1 = new Member("testMember1", 10, teamA);
        Member member3 = new Member("testMember1", 10, teamA);
        Member member2 = new Member("testMember2", 20, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        em.flush();
        em.clear();

        //when
        List<Member> members = memberRepository.findAll();
        List<Member> memberParam = memberRepository.findEntityGraphByUsername("testMember1");
//        List<Member> memberParam2 = memberRepository.findEntityGrapByUsername2("testMember1");

        for(Member member : members){
            System.out.println("member = " + member.getUsername());
            System.out.println("member.getTeam Name = " + member.getTeam().getName());
        }

        for(Member member : memberParam){
            System.out.println("member = " + member);
        }

//        for(Member member : memberParam2){
//            System.out.println("member = " + member);
//        }
    }

    @Test
    public void queryHint(){
        Member member = new Member("memberTest1", 10);
        memberRepository.save(member);
        em.flush();
        em.clear();

        //when
        //이때 원본 데이터와, 변경후 데이터 2개를 관리하고 있으므로 비효율이다
//        Member findMember = memberRepository.findById(member.getId()).get();

        //아래처럼 QueryHint를 사용하면 따로 관리를 하지 않는다 = 즉 데이터 변경이 불가능하다
        Member findMember = memberRepository.findReadOnlyByUsername(member.getUsername());
        findMember.setUsername("memberTest2");

        em.flush();//변경 감지한다
    }
    @Test
    public void lock(){
        Member member = new Member("memberTest1", 10);
        memberRepository.save(member);
        em.flush();
        em.clear();

        //실시간 트래픽일경우 락을 보지 않아야한다
        //패스매스틱 락 걸어버리면 해당 관련된 것들이 모두 락이 된다
        List<Member> lockByUsername = memberRepository.findLockByUsername(member.getUsername());

        for(Member m : lockByUsername){
            System.out.println("member = " + m);
        }
    }

    @Test
    public void callCustom(){
        List<Member> memberCustom = memberRepository.findMemberCustom();
        for(Member member : memberCustom){
            System.out.println("member = " + member);
        }
    }


}