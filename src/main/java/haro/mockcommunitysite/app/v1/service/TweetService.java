package haro.mockcommunitysite.app.v1.service;

import haro.mockcommunitysite.app.v1.domain.Tweet;
import haro.mockcommunitysite.app.v1.dto.response.ResponseDto;
import haro.mockcommunitysite.app.v1.dto.request.TweetRequestDto;
import haro.mockcommunitysite.app.v1.dto.response.TweetResponseDto;
import haro.mockcommunitysite.app.v1.repository.TweetRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TweetService {

    private final TweetRepository tweetRepository;

    @Transactional(readOnly = true)
    public ResponseDto<?> getAllTweets() {
        return ResponseDto.success(tweetRepository.findAllByOrderByCreatedAtDesc());
    }


    @Transactional(readOnly = true)
    public ResponseDto<?> getAllTweetsByMemberId(String memberId) {
        return ResponseDto.success(tweetRepository.findAllByMemberIdByOrderByCreatedAtDesc(memberId));
    }


    @Transactional(readOnly = true)
    public ResponseDto<?> getTweet(Long tweetId) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);

        if (optionalTweet.isEmpty()) {
            return ResponseDto.fail("TWEET_ID_NOT_FOUND", "tweet does not exist");
        }
        return ResponseDto.success(optionalTweet.get());
    }


    @Transactional
    public ResponseDto<?> createTweet(TweetRequestDto tweetRequestDto) {
        Tweet tweet = new Tweet(tweetRequestDto);
        tweetRepository.save(tweet);
        return ResponseDto.success(new TweetResponseDto<>(tweet));
    }


    @Transactional
    public ResponseDto<?> deleteTweet(Long tweetId) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);

        if (optionalTweet.isEmpty()) {
            return ResponseDto.fail("TWEET_ID_NOT_FOUND", "tweet does not exits");
        }
        tweetRepository.deleteById(tweetId);
        return ResponseDto.success(tweetId);
    }
}
