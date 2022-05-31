package jpabook.jpashop.controller;

import jpabook.jpashop.domian.Address;
import jpabook.jpashop.domian.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping(value = "/members/new",method = RequestMethod.GET)
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @RequestMapping(value = "/members/new", method = RequestMethod.POST)
    public String  create(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors()){ //에러
            return "members/createMemberForm";
        }
        
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        member.setStatus(form.getStatus());

        memberService.createAccount(member);
        return "home";
    }
    
    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
