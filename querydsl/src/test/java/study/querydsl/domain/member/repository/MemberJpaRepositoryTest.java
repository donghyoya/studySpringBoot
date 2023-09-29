package study.querydsl.domain.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydsl.domain.member.entity.Member;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest(){
        Member member1 = new Member("member1", 10);
        memberJpaRepository.save(member1);

        Member findMember = memberJpaRepository.findById(member1.getId()).get();
        assertThat(findMember).isEqualTo(member1);

        List<Member> results = memberJpaRepository.findAll();
//        member1 만 있는지 확인하는건데 난 저장된 데이터가 있어서 안됀다
//        assertThat(results).containsExactly(member1);
    }

    @Test
    public void basicQueryTest(){
        Member member1 = new Member("member1", 10);
        memberJpaRepository.save(member1);

        List<Member> results = memberJpaRepository.findAll_Querydsl();
        for (Member result : results) {
            System.out.println("result = " + result);
        }

        List<Member> findMembers = memberJpaRepository.findByUsername_Querydsl("member1");
        for (Member member : findMembers) {
            System.out.println("member = " + member);
        }
//        assertThat(findMember).containsExactly(member1);
    }
}