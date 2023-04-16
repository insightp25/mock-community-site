package haro.mockcommunitysite.app.v0.service;

import haro.mockcommunitysite.app.v0.dto.MemberRequestDto;
import haro.mockcommunitysite.app.v0.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private Integer generatedEmailVerificationCode;

    public String isEmailValid(MemberRequestDto memberRequestDto) {
        if (null == memberRepository.find(memberRequestDto.getEmail())) {
            return "send message that duplicate email exist";
        }
        generatedEmailVerificationCode = generateEmailVerificationCode(memberRequestDto.getEmail());
        return "redirect to the next page";
    }

    public String verifyEmailVerificationCode(Integer code) {
        if (code != generatedEmailVerificationCode) {
            return "redirect to the next page";
        }
        return "show message that verification code does not match";
    }

    public String registerAccount(MemberRequestDto memberRequestDto) {
        return memberRepository.save(memberRequestDto);
    }

    public Integer generateEmailVerificationCode(String email) {
        // some logic generating code
        // some logic sending the code to the email address
        int generatedCode = 12345;
        generatedEmailVerificationCode = generatedCode;
        return generatedCode;
    }
}
