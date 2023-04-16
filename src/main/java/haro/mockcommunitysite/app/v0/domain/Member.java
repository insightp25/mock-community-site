package haro.mockcommunitysite.app.v0.domain;

import haro.mockcommunitysite.app.v0.dto.MemberRequestDto;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    private String memberId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String MemberName;

    @Column(nullable = false)
    private Date birthdate;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    // @Id
    // @GeneratedValue
    // private Long id;

    protected Member() {
    }

    public Member(MemberRequestDto memberDto) {
        this.memberId = memberDto.getMemberId();
        this.MemberName = memberDto.getMemberName();
        this.createdAt = memberDto.getCreatedAt();
    }

}
