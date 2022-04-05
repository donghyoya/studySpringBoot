package jpabook.jpashop.Api;


import jpabook.jpashop.domian.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @RequestMapping(value = "/api/v1/members", method = RequestMethod.POST)
    public CreateMemberResponse saveMember(@RequestBody
                                           @Valid Member member){
        Long account = memberService.createAccount(member);
        return new CreateMemberResponse(account);
    }

    @RequestMapping(value = "/api/v2/members", method = RequestMethod.POST)
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.createAccount(member);

        return new CreateMemberResponse(id);
    }

    @RequestMapping(value = "/api/v2/members/{id}", method = RequestMethod.PUT)
    public UpdateMemberResponse updateMemberResponse(
            @PathVariable("id") Long id,
            @RequestBody @Valid updateMemberRequest request){
        memberService.update(id, request.getName());
        Member member_id = memberService.findId(id);

        return new UpdateMemberResponse(member_id.getId(), member_id.getName());
    }

    @RequestMapping(value = "/api/v1/members", method = RequestMethod.GET)
    public List<Member> membersV1(){
        return memberService.findMembers();
    }

    @RequestMapping(value = "/api/v2/members", method = RequestMethod.GET)
    public Result membersV2(){
        List<Member> findmembers = memberService.findMembers();

        List<MemberDto> collect = new ArrayList<>();
        for(Member member : findmembers){
            MemberDto m = new MemberDto(member.getName());
            collect.add(m);
        }
//        List<MemberDto> collect = findmembers.stream()
//                .map(m -> new MemberDto(m.getName()))
//                .collect(Collectors.toList());
        return new Result(collect.size(),collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private int count;
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
    }


    @Data
    static class updateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor //자동 생성자 생성
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest{
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long account) {
            this.id = account;
        }
    }
}
