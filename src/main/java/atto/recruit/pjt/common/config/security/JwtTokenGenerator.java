package atto.recruit.pjt.common.config.security;

import static java.lang.String.valueOf;

import atto.recruit.pjt.member.domain.entity.BearerToken;
import atto.recruit.pjt.member.domain.entity.Tokens;
import atto.recruit.pjt.member.repository.BearerTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtTokenGenerator {
    private final Long accessTokenValidSeconds;
    private final Long refreshTokenValidSeconds;
    private final String jwtSecret;
    private final Key key;
    private final BearerTokenRepository bearerTokenRepository;

    public JwtTokenGenerator(@Value("${jwt.access-token-valid-seconds}") Long accessTokenValidSeconds,
                             @Value("${jwt.refresh-token-valid-seconds}") Long refreshTokenValidSeconds,
                             @Value("${jwt.secret}") String jwtSecret,
                             BearerTokenRepository bearerTokenRepository) {
        this.accessTokenValidSeconds = accessTokenValidSeconds;
        this.refreshTokenValidSeconds = refreshTokenValidSeconds;
        this.jwtSecret = jwtSecret;
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.bearerTokenRepository = bearerTokenRepository;
    }

    public Tokens create(String memberId) {
        long nowInMilliseconds = new Date().getTime();
        String accessToken = createAccessToken(valueOf(memberId), "ROLE_ADMIN", new Date(nowInMilliseconds + accessTokenValidSeconds * 1000));
        String refreshToken = createRefreshToken(new Date(nowInMilliseconds + refreshTokenValidSeconds * 1000));
        bearerTokenRepository.deleteAllByMemberId(memberId);
        bearerTokenRepository.save(new BearerToken(memberId, refreshToken, accessToken));
        return new Tokens(accessToken, refreshToken);
    }

    private String createAccessToken(String memberId, String role, Date expiry) {
        Claims claims = Jwts.claims().setSubject(memberId).setExpiration(expiry).setIssuedAt(new Date());
        claims.put("role", role);
        claims.put("memberId", memberId);
        return Jwts.builder()
            .setClaims(claims)
            .signWith(key)
            .compact();
    }

    private String createRefreshToken(Date expiry) {
        return Jwts.builder()
            .claim("claim", Jwts.claims().setExpiration(expiry).setIssuedAt(new Date()))
            .signWith(key)
            .compact();
    }
}
