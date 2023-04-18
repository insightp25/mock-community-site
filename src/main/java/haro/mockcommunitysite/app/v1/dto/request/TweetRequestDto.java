package haro.mockcommunitysite.app.v1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TweetRequestDto {
    private Long tweetId;
    private String memberId;
    private String content;
}
