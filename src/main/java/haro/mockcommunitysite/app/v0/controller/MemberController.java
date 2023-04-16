package haro.mockcommunitysite.app.v0.controller;

import haro.mockcommunitysite.app.v0.dto.MemberRequestDto;
import haro.mockcommunitysite.app.v0.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/newcomers")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public String checkEmailForSignup(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.isEmailValid(memberRequestDto);
    }

    @PostMapping("/{email}/verification-codes")
    public String checkEmailVerification(@RequestBody Integer code) {
        return memberService.verifyEmailVerificationCode(code);
    }

    @PostMapping("/{email}/account-infos")
    public String signup(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.registerAccount(memberRequestDto);
    }


}
