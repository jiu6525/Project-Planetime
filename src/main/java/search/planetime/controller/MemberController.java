package search.planetime.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import search.planetime.domain.Gender;
import search.planetime.domain.Member;
import search.planetime.service.MemberService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/joinForm")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "login/joinForm";
    }

    @ModelAttribute("genders")
    public Gender[] gender() {
        return Gender.values();
    }


    @PostMapping("/joinForm")
    public String create(@Valid MemberForm form, BindingResult result, HttpServletResponse response) throws IOException {

        if (result.hasErrors()){
            return "login/joinForm";
        }

        Member member = Member.builder()
                        .memberId(form.getMemberId())
                        .memberPwd(passwordEncoder.encode(form.getMemberPwd()))
                        .name(form.getName())
                        .birth(form.getBirth())
                        .gender(form.getGender())
                        .email(form.getEmail())
                        .phone(form.getPhone())
                        .build();

        memberService.join(member);

        return "redirect:/home1";
    }

    @RequestMapping("/loginForm")
    public String login(){
        return "login/loginForm";
    }
}
