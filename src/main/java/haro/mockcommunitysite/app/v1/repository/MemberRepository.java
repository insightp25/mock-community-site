package haro.mockcommunitysite.app.v1.repository;

import haro.mockcommunitysite.app.v1.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByDisplayMemberId(String displayMemberId);
    Optional<Member> findByEmail(String email);
}
