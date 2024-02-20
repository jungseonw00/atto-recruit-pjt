package atto.recruit.pjt.member.application;

import static org.assertj.core.api.Assertions.assertThat;

import atto.recruit.pjt.common.config.security.JwtTokenProvider;
import atto.recruit.pjt.member.application.request.MemberLoginRequest;
import atto.recruit.pjt.member.application.request.MemberRegisterRequest;
import atto.recruit.pjt.member.domain.entity.BearerToken;
import atto.recruit.pjt.member.domain.entity.Member;
import atto.recruit.pjt.member.domain.entity.Tokens;
import atto.recruit.pjt.member.repository.BearerTokenRepository;
import atto.recruit.pjt.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Slf4j
@DisplayName("회원 API를 조회한다.")
class MemberServiceTest {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private BearerTokenRepository bearerTokenRepository;

	@BeforeEach
	void eachRegister() {
		MemberRegisterRequest member = MemberRegisterRequest.builder()
			.memberId("test_id")
			.password("1234")
			.build();
		memberService.registerMember(member);
	}

	@Test
	void registerMember() {
	    // given
		MemberRegisterRequest member = MemberRegisterRequest.builder()
						.memberId("test_id2")
						.password("12345")
						.build();
		memberService.registerMember(member);

		// when
		Member entity = memberRepository.findByMemberId(member.getMemberId()).get();

		// then
		assertThat(member.getMemberId()).isEqualTo(entity.getMemberId());
	}

	@Test
	void memberLogin() {
	    // given
		MemberLoginRequest member = MemberLoginRequest.builder()
			.memberId("test_id")
			.password("1234")
			.build();

		// when
		Tokens tokens = memberService.memberLogin(member);

		assertThat(tokenProvider.validateToken("Bearer " + tokens.getAccessToken())).isTrue();
	}

	@Test
	void refreshToken() {
	    // given
		MemberLoginRequest member = MemberLoginRequest.builder()
			.memberId("test_id")
			.password("1234")
			.build();

		// when
		Tokens tokens = memberService.memberLogin(member);

		Tokens refreshTokens = memberService.refresh(tokens.getRefreshToken());

		BearerToken bearerToken = bearerTokenRepository.findByRefreshToken(refreshTokens.getRefreshToken()).get();

		assertThat(refreshTokens.getRefreshToken()).isEqualTo(bearerToken.getRefreshToken());

	}
}