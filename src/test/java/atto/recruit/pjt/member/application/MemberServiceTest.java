package atto.recruit.pjt.member.application;

import static org.assertj.core.api.Assertions.assertThat;

import atto.recruit.pjt.common.config.security.JwtTokenProvider;
import atto.recruit.pjt.member.application.dto.request.MemberLoginRequest;
import atto.recruit.pjt.member.application.dto.request.MemberRegisterRequest;
import atto.recruit.pjt.member.application.dto.response.MemberLoginResponse;
import atto.recruit.pjt.member.domain.entity.Member;
import atto.recruit.pjt.member.domain.entity.Tokens;
import atto.recruit.pjt.member.repository.BearerTokenRepository;
import atto.recruit.pjt.member.repository.MemberRepository;
import atto.recruit.pjt.member.repository.TokenBlacklistInfoRepository;
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

	@Autowired
	private TokenBlacklistInfoRepository tokenBlacklistInfoRepository;

	@BeforeEach
	void eachRegister() {
		MemberRegisterRequest member = MemberRegisterRequest.builder()
			.memberId("test_id")
			.password("1234")
			.build();
		memberService.registerMember(member);
	}

	@Test
	@DisplayName("회원을 등록한다.")
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
	@DisplayName("로그인 후에 생성된 토큰을 검증한다.")
	void memberLogin() {
	    // given
		MemberLoginRequest member = MemberLoginRequest.builder()
			.memberId("test_id")
			.password("1234")
			.build();

//		 when
		MemberLoginResponse response = memberService.memberLogin(member);

		assertThat(tokenProvider.validateToken("Bearer " + response.getAccessToken())).isTrue();
	}

	@Test
	@DisplayName("AccessToken을 재발급 받는다.")
	void refreshToken() {
	    // given
		MemberLoginRequest member = MemberLoginRequest.builder()
			.memberId("test_id")
			.password("1234")
			.build();

		// when
		MemberLoginResponse response = memberService.memberLogin(member);

		Tokens token = memberService.refresh(response.getRefreshToken());
		assertThat(token.getRefreshToken()).isEqualTo(response.getRefreshToken());
	}
}