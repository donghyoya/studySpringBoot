package study.querydsl.domain.Hello.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.querydsl.domain.member.dto.MemberSearchCondition;
import study.querydsl.domain.member.dto.MemberTeamDto;
import study.querydsl.domain.member.repository.MemberRepositorySupport;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepositorySupport memberSupport;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberv1(MemberSearchCondition condition){
        return memberSupport.search(condition);
    }
}
