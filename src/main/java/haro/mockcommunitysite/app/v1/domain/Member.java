package haro.mockcommunitysite.app.v1.domain;

import java.sql.Date;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * memberId: 생성 후 값 변경 불가
 * DisplayMemberId: 생성 후 유저가 임의로 값 변경 가능
 */
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long memberId;

    @Column(unique = true, nullable = false)
    private String DisplayMemberId;

    /**
     * '@JsonIgnore': MemberResponseDto 에서 password 필드 자체를 없앴기 때문에 불필요?
     */
    // @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private Date birthdate;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
