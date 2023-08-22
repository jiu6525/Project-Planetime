package search.planetime.config.auth;

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

// 시큐리티 설정에서 loginProcessingUrl("/login");
// login 요청이 오면 자동으로 UserDetailService 타입으로 IoC 되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

//  시큐리티 session(내부Authentication(내부UserDetails))
//  함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
@Override
public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
    Member member = memberRepository.findByMemberId(memberId);
    return new User(member.getMemberId(), member.getMemberPwd(), Arrays.asList(new SimpleGrantedAuthority(member.getMemberType())));
}
}
