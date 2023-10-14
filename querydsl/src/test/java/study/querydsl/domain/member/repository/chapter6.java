package study.querydsl.domain.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.domain.member.entity.Member;

import java.util.List;

@SpringBootTest
@Transactional
public class chapter6 {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberRepositorySupport memberSupport;

    @Test
    public void basicTest(){

        List<Member> memberList = memberRepository.findByUsername("kang");
        Member member1 = memberList.get(0);

        System.out.println("member1 = " + member1);
    }
}

