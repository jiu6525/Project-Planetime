package search.planetime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.planetime.domain.Member;
import search.planetime.domain.findId;
import search.planetime.memberDTO.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<MemberDTO> findByMemberId(String memberId);

    findId findByNameAndEmail(String name, String email);
}

