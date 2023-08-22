package search.planetime.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import search.planetime.DTO.MailDTO;
import search.planetime.config.NewPassWord;
import search.planetime.domain.Member;
import search.planetime.domain.findId;
import search.planetime.DTO.MemberDTO;
import search.planetime.repository.MemberRepository;

import java.io.PrintWriter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender emailSender;

//  회원가입
    @Override
    public void join(MemberDTO memberDTO) {
        Member member = Member.builder()
                        .memberId(memberDTO.getMemberId())
                        .memberPwd(passwordEncoder.encode(memberDTO.getMemberPwd()))
                        .name(memberDTO.getName())
                        .birth(memberDTO.getBirth())
                        .gender(memberDTO.getGender())
                        .email(memberDTO.getEmail())
                        .phone(memberDTO.getPhone())
                        .memberType("user")
                        .build();

        memberRepository.save(member);
    }

//   아이디 찾기
    @Override
    public String findId(String name, String email) {
        findId member = memberRepository.findByNameAndEmail(name, email);
        return member.getMemberId();
    }

//    이메일 전송
    public String sendSimpleMessage(MailDTO mailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        String newPwd = NewPassWord.getRamdomPassword(10);
        message.setFrom("ajtwlswldn789@naver.com");
        message.setTo(mailDTO.getEmail());
        message.setSubject("비밀번호 재설정 메일");
        message.setText(mailDTO.getMemberId() + " 님의 새로운 비밀번호는 " + newPwd + "입니다.");
        emailSender.send(message);

        return newPwd;
    }

    public int findPwd(MailDTO mailDTO){
        String newPwd = sendSimpleMessage(mailDTO);
        Optional<Member> member = memberRepository.findByEmailAndMemberIdAndPhone(mailDTO.getEmail(),mailDTO.getMemberId(),mailDTO.getPhone());

        if (!member.isEmpty()) {
            Member newPwdMember = member.get();
            newPwdMember.setMemberPwd(passwordEncoder.encode(newPwd));
            memberRepository.save(member.get());
            return 1;
        }
        return 0;
    }
}
