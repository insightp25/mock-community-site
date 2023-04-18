package haro.mockcommunitysite.app.v1.dto.response;

import haro.mockcommunitysite.app.v1.domain.Member;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 'password' 를 return 이 필요한가?
 */
@Builder
@Getter
public class MemberResponseDto {
    private Long memberId;
    private String displayMemberId;
    private String email;
    private String memberName;
    private Date birthdate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // private String password;

    public MemberResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.displayMemberId = member.getDisplayMemberId();
        this.email = member.getEmail();
        this.memberName = member.getMemberName();
        this.birthdate = member.getBirthdate();
        this.createdAt = member.getCreatedAt();
        this.updatedAt = member.getUpdatedAt();
    }
}
