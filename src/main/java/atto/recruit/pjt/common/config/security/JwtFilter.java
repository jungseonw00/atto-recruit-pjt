package atto.recruit.pjt.common.config.security;

import static atto.recruit.pjt.common.config.error.ErrorCode.ACCESS_TOKEN_EXPIRED;

import atto.recruit.pjt.common.config.error.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String path = request.getServletPath();
            log.info("path => {}", path);
            if (validatePath(path)) {
                String token = jwtTokenProvider.resolveToken(request);
                if(token != null && jwtTokenProvider.validateToken(token)) {
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    log.info("authentication => {}", authentication);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            ErrorResponse res = ErrorResponse.of(ACCESS_TOKEN_EXPIRED);
            response.setStatus(401);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(res));
            response.getWriter().flush();
        }

    }

    private static boolean validatePath(String path) {
        return !path.equals("/member") && !path.equals("/member/login");
    }
}
