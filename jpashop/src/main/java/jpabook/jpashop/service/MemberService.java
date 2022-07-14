package jpabook.jpashop.service;

import jpabook.jpashop.domian.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional//데이터 변경할때 필요하다
//@AllArgsConstructor //생성자를 생성한다 예: MemberService(){}
@RequiredArgsConstructor //생성자를 생성한다. 단. final있는 필드만 끌고와서 생성자를 만든다
public class MemberService {

    private final MemberRepository memberRepository;
    //final로 해두면

    //즉 밑의 부분을 @RequiredArgsConstructor 이다
//    @Autowired //생성자 인잭션
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    public Long createAccount(Member member){

        boolean cheackDupleMember = validateDuplicateMember(member);
        if(cheackDupleMember == false){
            throw new IllegalStateException("중복 존재");
        }
        memberRepository.save(member);
        return member.getId();
    }

    private boolean validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getName());
        if(members.isEmpty() == false){//중복 존재
            return false;
        }
        return true; //중복 없음
    }


    //회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 이름 조회
    //해도 그만 안해도 그만이지만 이렇게 보기만 하는것은 성능 최적화할때 좋다
    @Transactional(readOnly = true)
    public Member findId(Long userid){
        return memberRepository.findOne(userid);
    }

    @Transactional
    public void update(Long id, String name) {
        //영속성 상태이기에 update문을 작성안해도 작동이 가능하다
        //이때 member을 반환하면 영속성이 끊긴 상태에서 반환이된다
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
