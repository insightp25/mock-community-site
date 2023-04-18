package haro.mockcommunitysite.app.v1.controller;

import haro.mockcommunitysite.app.v1.dto.response.ResponseDto;
import haro.mockcommunitysite.app.v1.dto.request.TweetRequestDto;
import haro.mockcommunitysite.app.v1.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/tweets")
public class TweetController {

    private final TweetService tweetService;

    @GetMapping
    public ResponseDto<?> getAllTweets() {
        return tweetService.getAllTweets();
    }


    @GetMapping("/{memberId}")
    public ResponseDto<?> getAllTweetsByMemberId(@PathVariable String memberId) {
        return tweetService.getAllTweetsByMemberId(memberId);
    }


    @GetMapping("/{tweetId}")
    public ResponseDto<?> getTweet(@PathVariable Long tweetId) {
        return tweetService.getTweet(tweetId);
    }


    @PostMapping
    public ResponseDto<?> createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.createTweet(tweetRequestDto);
    }


    @DeleteMapping("/{tweetId}")
    public ResponseDto<?> deleteTweet(@PathVariable Long tweetId) {
        return tweetService.deleteTweet(tweetId);
    }
}
