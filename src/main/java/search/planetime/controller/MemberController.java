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
import search.planetime.memberDTO.MemberDTO;
import search.planetime.service.MemberService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/joinForm")
    public String createForm(Model model){
        model.addAttribute("memberDTO", new MemberDTO());
        return "login/joinForm";
    }

    @ModelAttribute("genders")
    public Gender[] gender() {
        return Gender.values();
    }


    @PostMapping("/joinForm")
    public String join(MemberDTO dto, BindingResult result){

        if (result.hasErrors()){
            return "login/joinForm";
        }

        memberService.join(dto);
        return "redirect:/home1";
    }

    @RequestMapping("/loginForm")
    public String login(){
        return "login/loginForm";
    }
}
