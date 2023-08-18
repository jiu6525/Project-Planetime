package search.planetime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.planetime.domain.Member;
import search.planetime.domain.findId;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
    findId findByNameAndEmail(String name, String email);
    Optional<Member> findByEmailAndMemberIdAndPhone(String Email, String MemberId, String Phone);
}

