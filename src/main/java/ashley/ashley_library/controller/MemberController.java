package ashley.ashley_library.controller;

import ashley.ashley_library.domain.Member;
import ashley.ashley_library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    //생성자로 연결해줌
    @Autowired//생성자에 autowired 스프링컨테이너에 있는 memberServcie랑 연결해줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String addMember() {
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String memberCreate(MemberForm form) {
//        System.out.println("name: "+ form.getName() +" "+ form.getPhone());
        System.out.println(form.getName());
        Member member = new Member();
        member.setName(form.getName());
        member.setPhone(form.getPhone());


        memberService.join(member);

        return "redirect:/";//메인으로 돌려보냄
    }

    @GetMapping("/members/memberList")
    public String memberList(Model model, /* @PageableDefault(size = 10)*/Pageable pageable) {
        List<Member> members = memberService.findMembers();
//        Page<Member> memberList = memberService.findAll(pageable);
//        int nowPage = memberList.getPageable().getPageNumber() +1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, memberList.getTotalPages());

//        model.addAttribute("memberList",memberList.getPageable());
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
        model.addAttribute("members", members);
//        System.out.println(memberService.findAll(pageable).getTotalElements());
//        System.out.println(memberService.findAll(pageable).getTotalPages());


        return "/members/memberList";
    }

    @GetMapping("/members/monthlyCheckOut")
    public String monthlyCheckOut() {
        return "/members/monthlyCheckOut";
    }

}
