package search.planetime.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import search.planetime.domain.Member;
import search.planetime.memberDTO.MemberDTO;
import search.planetime.repository.MemberRepository;

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
                        .build();

        memberRepository.save(member);
    }
}
