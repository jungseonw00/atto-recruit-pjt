//package atto.recruit.pjt.common.security;
//
//import static java.lang.String.valueOf;
//
//import atto.recruit.pjt.common.error.exception.UserTokenNotFoundException;
//import atto.recruit.pjt.host.domain.BearerToken;
//import atto.recruit.pjt.host.domain.Tokens;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import java.security.Key;
//import java.util.Date;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class JwtTokenGenerator {
//    private final Long accessTokenValidSeconds;
//    private final Long refreshTokenValidSeconds;
//    private final String jwtSecret;
//    private final Key key;
//    private final BearerTokenRepository bearerTokenRepository;
//
//    public JwtTokenGenerator(@Value("${jwt.access-token-valid-seconds}") Long accessTokenValidSeconds,
//                             @Value("${jwt.refresh-token-valid-seconds}") Long refreshTokenValidSeconds,
//                             @Value("${jwt.secret}") String jwtSecret,
//                             BearerTokenRepository bearerTokenRepository) {
//        this.accessTokenValidSeconds = accessTokenValidSeconds;
//        this.refreshTokenValidSeconds = refreshTokenValidSeconds;
//        this.jwtSecret = jwtSecret;
//        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
//        this.bearerTokenRepository = bearerTokenRepository;
//    }
//
//    public Tokens create(String userId) {
//        long nowInMilliseconds = new Date().getTime();
//        String accessToken = createAccessToken(valueOf(userId), "ROLE_USER", new Date(nowInMilliseconds + accessTokenValidSeconds * 1000));
//        String refreshToken = createRefreshToken(new Date(nowInMilliseconds + refreshTokenValidSeconds * 1000));
//        bearerTokenRepository.deleteAllByUserId(userId);
//        bearerTokenRepository.save(new BearerToken(userId, refreshToken, accessToken));
//        return new Tokens(accessToken, refreshToken);
//    }
//
//    public Tokens exchangeAccessToken(Member member, String accessToken) {
//        BearerToken token = bearerTokenRepository.findByAccessToken(accessToken)
//            .orElseThrow(() -> new UserTokenNotFoundException("accessToken not found. token : " + accessToken));
//        long nowInMilliseconds = new Date().getTime();
//        String changedAccessToken = createAccessToken(valueOf(member.getUserId()),
//                                                 "ROLE_USER",
//                                                      new Date(nowInMilliseconds + accessTokenValidSeconds * 1000));
//        token.exchangeAccessToken(changedAccessToken);
//        bearerTokenRepository.save(token);
//        return new Tokens(changedAccessToken, token.getRefreshToken());
//    }
//
//    private String createAccessToken(String userId, String role, Date expiry) {
//        Claims claims = Jwts.claims().setSubject(userId).setExpiration(expiry).setIssuedAt(new Date());
//        claims.put("role", role);
//        claims.put("userId", userId);
//        return Jwts.builder()
//            .setClaims(claims)
//            .signWith(key)
//            .compact();
//    }
//
//    private String createRefreshToken(Date expiry) {
//        return Jwts.builder()
//            .claim("claim", Jwts.claims().setExpiration(expiry).setIssuedAt(new Date()))
//            .signWith(key)
//            .compact();
//    }
//}
