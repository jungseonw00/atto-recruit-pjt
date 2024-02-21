package atto.recruit.pjt.member.domain.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class BearerToken {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bearer_token_id")
    private Long id;
    private String memberId;
    private String refreshToken;
    private String accessToken;
    @Enumerated(STRING)
    private TokenStatus tokenStatus;

    public BearerToken(String memberId, String refreshToken, String accessToken) {
        this.memberId = memberId;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
