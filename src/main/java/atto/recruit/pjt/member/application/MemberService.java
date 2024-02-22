package atto.recruit.pjt.member.application;

import static atto.recruit.pjt.common.config.error.ErrorCode.ACCESS_TOKEN_EXPIRED;
import static atto.recruit.pjt.common.config.error.ErrorCode.DUPLICATE_MEMBER;
import static atto.recruit.pjt.common.config.error.ErrorCode.MEMBER_NOT_FOUND;
import static atto.recruit.pjt.common.config.error.ErrorCode.REFRESH_TOKEN_NOT_FOUND;

import atto.recruit.pjt.common.config.error.exception.CustomException;
import atto.recruit.pjt.common.config.security.JwtTokenGenerator;
import atto.recruit.pjt.common.config.security.JwtTokenProvider;
import atto.recruit.pjt.member.application.request.MemberLoginRequest;
import atto.recruit.pjt.member.application.request.MemberRegisterRequest;
import atto.recruit.pjt.member.application.response.MemberRegisterResponse;
import atto.recruit.pjt.member.domain.entity.BearerToken;
import atto.recruit.pjt.member.domain.entity.Member;
import atto.recruit.pjt.member.domain.entity.TokenBlacklistInfo;
import atto.recruit.pjt.member.domain.entity.Tokens;
import atto.recruit.pjt.member.presentation.LogoutTokenRequest;
import atto.recruit.pjt.member.repository.BearerTokenRepository;
import atto.recruit.pjt.member.repository.MemberRepository;
import atto.recruit.pjt.member.repository.TokenBlacklistInfoRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
	private final JwtTokenProvider tokenProvider;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtTokenGenerator tokenGenerator;
	private final BearerTokenRepository bearerTokenRepository;
	private final TokenBlacklistInfoRepository tokenBlacklistInfoRepository;


	public MemberRegisterResponse registerMember(MemberRegisterRequest request) {
		memberRepository.findByMemberId(request.getMemberId()).ifPresent(
			member -> {
				throw new CustomException(DUPLICATE_MEMBER);
			});

		Member entity = Member.builder()
				.memberId(request.getMemberId())
				.password(passwordEncoder.encode(request.getPassword()))
				.build();

		memberRepository.save(entity);

		return MemberRegisterResponse.of(entity);
	}

	public Tokens memberLogin(MemberLoginRequest request) {
		return memberRepository.findByMemberId(request.getMemberId())
			.filter(member -> passwordEncoder.matches(request.getPassword(), member.getPassword()))
			.map(member -> tokenGenerator.create(member.getMemberId()))
			.filter(token -> validateBlacklistToken(token.getAccessToken()))
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
	}

	private boolean validateBlacklistToken(String accessToken) {
		tokenBlacklistInfoRepository.validateBlacklist(accessToken).ifPresent(t -> {
			throw new CustomException(ACCESS_TOKEN_EXPIRED);
		});
		return true;
	}

	public Tokens refresh(String refreshToken) {
		try {
			tokenProvider.decode(refreshToken);
		} catch (ExpiredJwtException e) {
			throw new CustomException(ACCESS_TOKEN_EXPIRED);
		}

		BearerToken token = bearerTokenRepository.findByRefreshToken(refreshToken)
				.orElseThrow(() -> new CustomException(REFRESH_TOKEN_NOT_FOUND));

		String memberId = token.getMemberId();
		Member member = memberRepository.findByMemberId(memberId)
			.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

		return tokenGenerator.create(member.getMemberId());
	}

	public LogoutTokenResponse logout(LogoutTokenRequest request) {
		try {
			tokenProvider.decode(request.getAccessToken());
		} catch (ExpiredJwtException e) {
			throw new CustomException(ACCESS_TOKEN_EXPIRED);
		}

		TokenBlacklistInfo entity = TokenBlacklistInfo.builder()
					.accessToken(request.getAccessToken())
					.memberId(request.getMemberId())
					.build();

		tokenBlacklistInfoRepository.save(entity);

		return LogoutTokenResponse.of(entity);
	}
}