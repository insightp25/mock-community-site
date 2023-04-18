package haro.mockcommunitysite.app.v1.service;

import haro.mockcommunitysite.app.v1.domain.Member;
import haro.mockcommunitysite.app.v1.dto.request.MemberRequestDto;
import haro.mockcommunitysite.app.v1.dto.request.TokenDto;
import haro.mockcommunitysite.app.v1.dto.response.MemberResponseDto;
import haro.mockcommunitysite.app.v1.dto.response.ResponseDto;
import haro.mockcommunitysite.app.v1.jwt.TokenProvider;
import haro.mockcommunitysite.app.v1.repository.MemberRepository;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseDto<?> createMember(MemberRequestDto requestDto) {

        // displayMemberId 사용 로그인 시도 시(requestDto 의 email 필드에 값이 존재하지 않을 시)
        if (requestDto.getEmail().isEmpty()) {
            if (null != isPresentMemberByDisplayMemberId(requestDto.getDisplayMemberId())) {
                return ResponseDto.fail("DUPLICATE_USER_ID",
                        "the user id already exists");
            }
        }
        if (null != isPresentMemberByEmail(requestDto.getEmail())) {
            return ResponseDto.fail("DUPLICATED_EMAIL",
                    "the email is already taken");
        }


        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())) {
            return ResponseDto.fail("PASSWORDS_NOT_MATCHED",
                    "password and password confirm are not matched");
        }

        Member member = Member.builder()
                .nickname(requestDto.getDisplayMemberId())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        memberRepository.save(member);
        return ResponseDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .nickname(member.getDisplayMemberId())
                        .createdAt(member.getCreatedAt())
                        .modifiedAt(member.getModifiedAt())
                        .build()
        );
    }


    @Transactional
    public ResponseDto<?> login(MemberRequestDto requestDto, HttpServletResponse response) {

        Member memberByEmailOrNull = isPresentMemberByEmail(requestDto.getEmail());
        Member memberByDisplayMemberIdOrNull = isPresentMemberByDisplayMemberId(requestDto.getDisplayMemberId());

        // displayMemberId/email 입력 단계(requestDto 의 password 필드에 값이 존재하지 않을 시)
        if (requestDto.getPassword().isEmpty()) {

            // displayMemberId 사용 로그인 시도 시(requestDto 의 email 필드에 값이 존재하지 않을 시)
            if (requestDto.getEmail().isEmpty()) {

                // DB에 존재 여부 확인
                if (memberByDisplayMemberIdOrNull == null) {
                    return ResponseDto.fail("DISPLAY_MEMBER_ID_NOT_FOUND", "user id does not exist");
                }
                return ResponseDto.success(requestDto.getMemberId());
            }

            // email 사용 로그인 시도 시(requestDto 의 email 필드에 값이 존재할 시)
            if (memberByDisplayMemberIdOrNull == null) {
                return ResponseDto.fail("EMAIL_NOT_FOUND", "email does not exist");
            }
            return ResponseDto.success(requestDto.getEmail());
        }


        // displayMemberId 사용 로그인 시도 시
        if (requestDto.getEmail().isEmpty()) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(requestDto.getDisplayMemberId(), requestDto.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject()
                    .authenticate(authenticationToken);

            TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
            tokenToHeaders(tokenDto, response);

            return ResponseDto.success(new MemberResponseDto(memberByDisplayMemberIdOrNull));
        }

        // email 사용 로그인 시도 시
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        tokenToHeaders(tokenDto, response);

        return ResponseDto.success(new MemberResponseDto(memberByEmailOrNull));
    }


    public ResponseDto<?> logout(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return ResponseDto.fail("INVALID_TOKEN", "refresh token is invalid");
        }
        Member member = tokenProvider.getMemberFromAuthentication();
        if (null == member) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "member not found");
        }

        return tokenProvider.deleteRefreshToken(member);
    }



    @Transactional(readOnly = true)
    public Member isPresentMemberByDisplayMemberId(String displayMemberId) {
        Optional<Member> optionalMember = memberRepository.findByDisplayMemberId(displayMemberId);
        return optionalMember.orElse(null);
    }


    @Transactional(readOnly = true)
    public Member isPresentMemberByEmail(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        return optionalMember.orElse(null);
    }


    public void tokenToHeaders(TokenDto tokenDto, HttpServletResponse response) {
        response.addHeader("Access-Token", "Bearer " + tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", "Bearer " + tokenDto.getRefreshToken());
        response.addHeader("Access-Token-Expire-Time", tokenDto.getAccessTokenExpiresIn().toString());
    }
}
