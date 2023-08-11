package search.planetime.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import search.planetime.domain.Member;
import search.planetime.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final EntityManager em;

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){

//        validateDuplicateMember(member);    //중복 회원 검사
        memberRepository.save(member);
        System.out.println("start join!!");
        return member.getNo();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getMemberId());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
