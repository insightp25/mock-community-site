package haro.mockcommunitysite.app.v1.dto.response;

import haro.mockcommunitysite.app.v1.domain.Member;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Getter;

/**
 * 'password' 를 return 이 필요한가?
 */
@Getter
public class MemberResponseDto {
    private Long memberId;
    private String DisplayMemberId;
    private String email;
    private String memberName;
    private Date birthdate;
    private String password;

    public MemberResponseDto(String email, String memberName, Date birthdate) {
        this.email = email;
        this.memberName = memberName;
        this.birthdate = birthdate;
    }

    public MemberResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.DisplayMemberId = member.getDisplayMemberId();
        this.email = member.getEmail();
        this.memberName = member.getMemberName();
        this.birthdate = member.getBirthdate();
    }
}
