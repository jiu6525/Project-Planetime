package search.planetime.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import search.planetime.domain.Gender;
import search.planetime.domain.Member;
import search.planetime.domain.findId;
import search.planetime.memberDTO.MemberDTO;
import search.planetime.service.MemberService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    MemberService memberService;

//  사용자 로그인
    @RequestMapping("/loginForm")
    public String login(){
        return "member/loginForm";
    }

//  사용자 회원가입폼 이동
    @GetMapping("/joinForm")
    public String createForm(Model model){
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/joinForm";
    }

//  회원가입에 필요한 성별 enum 타입 리스트로 출력
    @ModelAttribute("genders")
    public Gender[] gender() {
        return Gender.values();
    }

//  JpaRepository save db에 회원의 정보를 저장
//  BindingResult 회원가입 폼에서 잘못된 타입,정보를 체크해서 회원가입 폼으로 리턴
    @PostMapping("/joinForm")
    public String join(MemberDTO dto, BindingResult result){

        if (result.hasErrors()){
            return "member/joinForm";
        }

        memberService.join(dto);
        return "redirect:/home1";
    }

//  사용자 아이디 찿기폼으로 이동
    @GetMapping("/findIdForm")
    public String findIdForm(Model model){
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/findIdForm";
    }

//  Ajax로 얻어온 값을 JpaRepository findByNameAndPhone 을 재정의 하여
//  memberService 에서 findId 메서드를 통해 값 리턴
    @RequestMapping(value = "/findId", method = RequestMethod.POST)
    @ResponseBody
    public String findId(@RequestParam("name") String name,@RequestParam("phone") String phone){
        String member = "찾으려는 아이디는 "+memberService.findId(name, phone) + " 입니다.";
        return member;
    }
}
