package search.planetime.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import search.planetime.DTO.MailDTO;
import search.planetime.domain.Gender;
import search.planetime.DTO.MemberDTO;
import search.planetime.service.MemberService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        return "redirect:/main";
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

//  비밀번호 찾기 폼으로 이동
    @GetMapping("/findPwdForm")
    public String findPwdForm(Model model){
        model.addAttribute("MailDTO", new MailDTO());
        return "member/findPwdForm";
    }

//  비밀번호 재설정
    @PostMapping("/findPwdForm")
    public String sendMail(MailDTO mailDTO,Model model) throws IOException {
        int changePwdCk = memberService.findPwd(mailDTO);
        System.out.println("메일 전송 완료");

        if (changePwdCk == 1){
            model.addAttribute("msg", "메일로 재설정 비밀번호가 전송되었습니다 확인해주세요");
            model.addAttribute("url", "/main");
        }else{
            model.addAttribute("msg", "재설정 실패.");
            model.addAttribute("url", "/findPwdForm");
        }
        return "/alert";

    }
}
