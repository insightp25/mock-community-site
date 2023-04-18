package haro.mockcommunitysite.app.v1.repository;

import haro.mockcommunitysite.app.v1.domain.Tweet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    Optional<Tweet> findById(Long id);
    List<Tweet> findAllByOrderByCreatedAtDesc();
    List<Tweet> findAllByMemberIdByOrderByCreatedAtDesc(String memberId);
}
