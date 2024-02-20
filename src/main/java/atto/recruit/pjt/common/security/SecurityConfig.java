//package atto.recruit.pjt.common.security;
//
//import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//	private final CustomAuthenticationEntryPoint authenticationEntryPoint;
//	private final JwtTokenProvider jwtTokenProvider;
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		return http.httpBasic(AbstractHttpConfigurer::disable)
//			.cors(AbstractHttpConfigurer::disable)
//			.formLogin(AbstractHttpConfigurer::disable)
//			.csrf(AbstractHttpConfigurer::disable)
//			.sessionManagement(x -> x.sessionCreationPolicy(STATELESS))
//			.exceptionHandling(x -> x.authenticationEntryPoint(authenticationEntryPoint))
//			.authorizeHttpRequests(
//				request ->
//					 request.requestMatchers("szs/signup", "szs/login", "swagger-ui/**", "v3/api-docs/**").permitAll()
//					 		.anyRequest().hasRole("USER")
//			)
//			.addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
//			.build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//}