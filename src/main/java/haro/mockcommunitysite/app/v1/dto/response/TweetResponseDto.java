package haro.mockcommunitysite.app.v1.dto.response;

import haro.mockcommunitysite.app.v1.domain.Tweet;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 'likes' 필드 및 관련 기능 이후 추가
 */
@Getter
public class TweetResponseDto<T> {
    private final Long tweetId;
    private final String memberId;
    private final String content;
    private final LocalDateTime createdAt;
    // private int likes;

    public TweetResponseDto(Tweet tweet) {
        this.tweetId = tweet.getTweetId();
        this.memberId = tweet.getMemberId();
        this.content = tweet.getContent();
        this.createdAt = tweet.getCreatedAt();
    }
}
