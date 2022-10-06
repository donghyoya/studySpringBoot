package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamJpaRepository teamJpaRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    @Rollback(value = false)
    public void testMember(){
        Member member = new Member("member1",20);

        Member save = memberJpaRepository.save(member);

        Member member1 = memberJpaRepository.find(save.getId());

        assertThat(save.getId()).isEqualTo(member1.getId());
        assertThat(member.getUsername()).isEqualTo(member1.getUsername());
    }

    @Test
    @Rollback(value = true)
    public void basicCRUD(){

        Optional<Team> team_sub = teamRepository.findById(1L);
        Team teamA = team_sub.get();

        Optional<Member> byId1 = memberRepository.findById(3L);
        Member member1 = byId1.get();
        Optional<Member> byId2 = memberRepository.findById(4L);
        Member member2 = byId2.get();
        Member member3 = memberJpaRepository.find(5L);

        Member Submember = new Member("SubMember",10,teamA);

        memberJpaRepository.save(Submember);

//        Optional<Member> findMember1 = memberJpaRepository.findbyId(member1.getId());
//        Optional<Member> findMember2 = memberJpaRepository.findbyId(member2.getId());
//
//        assertThat(member1).isEqualTo(findMember1);
//        assertThat(member2).isEqualTo(findMember2);

        List<Member> members = memberJpaRepository.findAll();

        members.forEach((member)->{
            System.out.println("member = " + member);
        });

        Long count = memberJpaRepository.count();
        System.out.println("count = " + count);

        memberJpaRepository.delect(Submember);

        Long count2 = memberJpaRepository.count();
        System.out.println("count2 = " + count2);
    }

    @Test
    @Rollback(value = true)
    public void findByUsernameAndAgeGreaterThen(){
        Member member1 = new Member("asb", 10);
        Member member2 = new Member("asb", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("asb", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("asb");
        assertThat(result.get(0).getAge()).isEqualTo(20);
    }

    @Test
    @Rollback(value = true)
    public void testNamedQuery(){
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberJpaRepository.namedQueryfindByUsername("AAA");

        Member findMember = result.get(0);

        assertThat(findMember).isEqualTo(member1);
    }

    @Test
    public void testNamedQuery2(){
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findByUsername("AAA");

        Member findMember = result.get(0);

        assertThat(findMember).isEqualTo(member1);
    }

    @Test
    public void findUsernameList(){
        Member member1 = new Member("AAA", 10);
        Member member2 = new Member("BBB", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<String> usernameList = memberRepository.findUsernameList();

        for(String s : usernameList){
            System.out.println("username = " + s);
        }
    }

    @Test
    public void paging(){
        memberJpaRepository.save(new Member("memberTest1",20));
        memberJpaRepository.save(new Member("memberTest2",20));
        memberJpaRepository.save(new Member("memberTest3",20));
        memberJpaRepository.save(new Member("memberTest4",20));
        memberJpaRepository.save(new Member("memberTest5",20));
        memberJpaRepository.save(new Member("memberTest6",20));
        memberJpaRepository.save(new Member("memberTest7",20));
        memberJpaRepository.save(new Member("memberTest8",20));

        int age = 20;
        int offset = 0;
        int limit = 4;

        List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
        long totalCount = memberJpaRepository.totalCount(age, offset, limit);
        for(Member data : members){
            System.out.println("member = " + data);
        }
        System.out.println("totalCount = " + totalCount);


    }
}