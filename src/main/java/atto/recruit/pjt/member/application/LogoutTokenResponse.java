package atto.recruit.pjt.member.application;

import atto.recruit.pjt.member.domain.entity.TokenBlacklistInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogoutTokenResponse {
	private String memberId;
	private String accessToken;

	public static LogoutTokenResponse of(TokenBlacklistInfo entity) {
		return LogoutTokenResponse.builder()
				.memberId(entity.getMemberId())
				.accessToken(entity.getAccessToken())
				.build();
	}
}
