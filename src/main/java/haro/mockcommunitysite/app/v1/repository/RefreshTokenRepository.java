package haro.mockcommunitysite.app.v1.repository;


import haro.mockcommunitysite.app.v1.domain.Member;
import haro.mockcommunitysite.app.v1.domain.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByMember(Member member);
}
