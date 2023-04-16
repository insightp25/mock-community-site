package haro.mockcommunitysite.app.v0.dto;

import java.sql.Date;
import java.sql.Timestamp;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private String memberId;
    private String password;
    private String email;
    private String memberName;
    private Date birthdate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public MemberResponseDto(String email, String memberName, Date birthdate) {
        this.email = email;
        this.memberName = memberName;
        this.birthdate = birthdate;
    }
}
