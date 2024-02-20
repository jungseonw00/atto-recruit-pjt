package atto.recruit.pjt.common.config.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import atto.recruit.pjt.common.CustomLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomAuthenticationEntryPoint authenticationEntryPoint;
	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.httpBasic(AbstractHttpConfigurer::disable)
			.cors(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(x -> x.sessionCreationPolicy(STATELESS))
			.exceptionHandling(x -> x.authenticationEntryPoint(authenticationEntryPoint))
			.authorizeHttpRequests(
				request ->
					 request.requestMatchers("swagger-ui/**", "v3/api-docs/**", "member", "member/login").permitAll()
					 		.anyRequest().hasRole("ADMIN"))
			.addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
			.logout(logout -> logout.logoutSuccessHandler(new CustomLogoutSuccessHandler()))
			.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}