package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Controller 어노테이션이 있으면 스프링 컨테이너가 관리한다
@Controller
public class MemberController {

//     멤버컨트롤러에서 멤버서비스를 사용하기 위해 멤버서비스를 이런방식으로 객체를 생성해 놓으면 다른 컨트롤러에서도 멤버서비스를 사용해야
//     할텐데 이렇게 할 필요가 없다.
//    private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    @Autowired // 스프링 컨테이너가 뜰때 컨트롤러를 띄운다 -> 그때 생성자를 호출하는다 @Autowired가 있으면 스프링 컨테이너에 있는
                 // 멤버서비스를 가져다 연결시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService.getClass() = " + memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
