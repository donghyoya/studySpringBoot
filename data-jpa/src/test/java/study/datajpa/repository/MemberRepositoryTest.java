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
}