package ashley.ashley_library.controller;

import ashley.ashley_library.domain.Borrow;
import ashley.ashley_library.domain.Member;
import ashley.ashley_library.service.BorrowService;
import ashley.ashley_library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        System.out.println("name: " + form.getName() + " " + form.getPhone());
        System.out.println(form.getName());
        Member member = new Member();
        member.setName(form.getName());
        member.setPhone(form.getPhone());


        memberService.join(member);

        return "redirect:/";//메인으로 돌려보냄
    }

    @GetMapping("/members/memberList")
    public String memberList(Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo, @PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        //클라이언트 페이지에서 받은 pageNo과 실제접근 페이지는 다름, page객체는 페이지가 0부터 시작
        pageNo = (pageNo == 0) ? 0 : (pageNo - 1);

        Page<Member> memberList = memberService.memberList(pageable, pageNo);
//        Page<Member> memberList = memberService.memberList(pageable);

//        int nowPage = memberList.getPageable().getPageNumber(); // pageable이 가지고 있는 페이지는 0부터 시작하기때문에 1을 더함
        int nowPage = memberList.getPageable().getPageNumber() + 1; // pageable이 가지고 있는 페이지는 0부터 시작하기때문에 1을 더함
        int startPage = Math.max(nowPage - 4, 1); // 1보다 작은 경우는 1을 반환
        int endPage = Math.min(nowPage + 9, memberList.getTotalPages()); // 전체 페이지보다 많은 경우는 전체 페이지를 반환

//        model.addAttribute("memberList",memberList.getContent());
        model.addAttribute("memberList", memberList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", memberList.getTotalPages());
        model.addAttribute("totalCnt", memberList.getTotalElements());
        System.out.println("memberList.getTotalElements()" + memberList.getTotalElements());
        model.addAttribute("pageSize", memberList.getSize());
        System.out.println("size?? " + memberList.getSize());
        return "/members/memberList";
    }


    @GetMapping("/members/memberDetail")
    public String memberDetail(Model model, Long id) {

        model.addAttribute("memberDetail", memberService.memberDetail(id));


        return "/members/memberDetail";
    }
//    @GetMapping("/members/memberDetail")
//    public String memberDetail(Model model, Long id) {
//        model.addAttribute("memberDetail", memberService.memberDetail(id));
//
//
//        return "/mebmers/memberDetail";
//    }


}
