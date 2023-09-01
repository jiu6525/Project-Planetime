package search.planetime.repository;

import search.planetime.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(String MEMBER_ID);
    Optional<Member> findByName(String NAME);
    List<Member> findAll();
}
