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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseDto<?> createMember(MemberRequestDto requestDto) {

        // displayMemberId validation
        if (null != isPresentMemberByDisplayMemberId(requestDto.getDisplayMemberId())) {
            return ResponseDto.fail("DUPLICATE_USER_ID",
                    "the user id already exists");
        }

        // email validation
        if (null != isPresentMemberByEmail(requestDto.getEmail())) {
            return ResponseDto.fail("DUPLICATED_EMAIL",
                    "the email already exists");
        }

        // password confirm
        if (!requestDto.getPassword().equals(requestDto.getConfirmingPassword())) {
            return ResponseDto.fail("PASSWORDS_NOT_MATCHED",
                    "password and confirming password does not match");
        }

        // member persist
        Member member = Member.builder()
                .displayMemberId(requestDto.getDisplayMemberId())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        memberRepository.save(member);
        return ResponseDto.success(
                MemberResponseDto.builder()
                        .memberId(member.getMemberId())
                        .displayMemberId(member.getDisplayMemberId())
                        .email(member.getEmail())
                        .memberName(member.getMemberName())
                        .birthdate(member.getBirthdate())
                        .createdAt(member.getCreatedAt())
                        .updatedAt(member.getUpdatedAt())
                        .build());
    }


    @Transactional
    public ResponseDto<?> login(MemberRequestDto requestDto, HttpServletResponse response) {

        // id input, validation
        if (requestDto.getPassword().isEmpty()) {

            // displayMemberId as id
            if (requestDto.getEmail().isEmpty()) {
                if (null == isPresentMemberByDisplayMemberId(requestDto.getDisplayMemberId()))
                    return ResponseDto.fail("DISPLAY_MEMBER_ID_NOT_FOUND", "user id does not exist");
                return ResponseDto.success(requestDto.getDisplayMemberId());
            }

            // email as id
            if (null == isPresentMemberByEmail(requestDto.getEmail()))
                return ResponseDto.fail("EMAIL_NOT_FOUND", "email does not exist");
            return ResponseDto.success(requestDto.getEmail());
        }

        // password input, authentication, token extraction
        if (requestDto.getEmail().isEmpty()) {

            // displayMemberId as id
            extractToken(requestDto.getDisplayMemberId(), requestDto.getPassword(), response);
            return ResponseDto.success(new MemberResponseDto(isPresentMemberByDisplayMemberId(requestDto.getDisplayMemberId())));
        } else {

            // email as id
            extractToken(requestDto.getEmail(), requestDto.getPassword(), response);
            return ResponseDto.success(new MemberResponseDto(isPresentMemberByEmail(requestDto.getEmail())));
        }
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

        // displayMemberId as id
        Optional<Member> optionalMember = memberRepository.findByDisplayMemberId(displayMemberId);
        return optionalMember.orElse(null);
    }

    @Transactional(readOnly = true)
    public Member isPresentMemberByEmail(String email) {

        // email as id
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        return optionalMember.orElse(null);
    }

    private void extractToken(String loginDomain, String password, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDomain, password);

        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        tokenToHeaders(tokenDto, response);
    }

    public void tokenToHeaders(TokenDto tokenDto, HttpServletResponse response) {
        response.addHeader("Access-Token", "Bearer " + tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", "Bearer " + tokenDto.getRefreshToken());
        response.addHeader("Access-Token-Expire-Time", tokenDto.getAccessTokenExpiresIn().toString());
    }
}
