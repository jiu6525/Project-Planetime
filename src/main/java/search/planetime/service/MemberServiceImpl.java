package search.planetime.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import search.planetime.domain.Member;
import search.planetime.domain.findId;
import search.planetime.memberDTO.MemberDTO;
import search.planetime.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public String findId(String name, String email) {
        findId member = memberRepository.findByNameAndEmail(name, email);
        return member.getMemberId();
    }
}
