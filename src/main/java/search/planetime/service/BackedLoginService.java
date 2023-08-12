package search.planetime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import search.planetime.domain.Member;
import search.planetime.repository.MemberRepository;

import java.util.Arrays;

@Service
public class BackedLoginService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        return new User(member.getMemberId(), member.getMemberPwd(), Arrays.asList(new SimpleGrantedAuthority(member.getMemberType())));
    }
}