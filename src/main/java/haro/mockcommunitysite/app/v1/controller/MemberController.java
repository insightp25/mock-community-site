package haro.mockcommunitysite.app.v1.controller;

import haro.mockcommunitysite.app.v1.dto.request.MemberRequestDto;
import haro.mockcommunitysite.app.v1.dto.response.ResponseDto;
import haro.mockcommunitysite.app.v1.service.MemberService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * OAuth 를 활용한 회원등록, 로그인 기능은 현 시점에서 제외
 */
@RequiredArgsConstructor
@RestController("/members")
public class MemberController {

    private MemberService memberService;

    @PostMapping("/new")
    public ResponseDto<?> signup(@RequestBody @Valid MemberRequestDto requestDto) { // @Valid: to be covered later
        return memberService.createMember(requestDto);
    }


    @PostMapping("/login")
    public ResponseDto<?> login(@RequestBody MemberRequestDto requestDto, HttpServletResponse response) {
        return memberService.login(requestDto, response);
    }


    @PostMapping("/logout")
    public ResponseDto<?> logout() {
        return null;
    }
}
