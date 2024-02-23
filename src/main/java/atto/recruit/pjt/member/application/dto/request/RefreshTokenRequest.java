package atto.recruit.pjt.member.application.dto.request;

import lombok.Getter;

@Getter
public class RefreshTokenRequest {
	private String accessToken;
	private String refreshToken;
}
