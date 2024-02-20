package atto.recruit.pjt.host.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class BearerToken {

    public static final String ACCESS_TOKEN_CACHE_KEY = "access.token";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bearer_token_id")
    private Long id;

    private Long userId;

    private Long userProfileId;

    private String refreshToken;

    private String accessToken;

    public BearerToken(Long userId, Long userProfileId, String refreshToken, String accessToken) {
        this.userId = userId;
        this.userProfileId = userProfileId;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public void exchangeAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
