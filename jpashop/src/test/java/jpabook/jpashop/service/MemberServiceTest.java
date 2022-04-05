package jpabook.jpashop.service;

import jpabook.jpashop.domian.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    @Rollback
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim2");
        //when
        Long saveid = memberService.createAccount(member);

        //then
        assertEquals(member, memberRepository.findOne(saveid));
    }

    @Test()
    public void 중복회원확인() throws Exception{
        Member member1 = new Member();
        member1.setName("hwang");

        Member member2 = new Member();
        member2.setName("hwang");

        memberService.createAccount(member1);
        try {
            memberService.createAccount(member2);
        }catch (IllegalStateException e){
            return;
        }
        fail("에외 발생");

    }
}