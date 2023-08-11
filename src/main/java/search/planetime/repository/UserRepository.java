package search.planetime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import search.planetime.domain.Member;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String name);
}