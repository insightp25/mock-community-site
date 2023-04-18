package haro.mockcommunitysite.app.v1.domain;

import haro.mockcommunitysite.app.v1.dto.request.TweetRequestDto;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Entity
//public class Tweet extends Timestamped {
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tweetId;

    @Column(nullable = false)
    private final String memberId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private final String content;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    public Tweet(TweetRequestDto tweetRequestDto) {
        this.memberId = tweetRequestDto.getMemberId();
        this.content = tweetRequestDto.getContent();
    }
}
