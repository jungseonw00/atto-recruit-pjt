package atto.recruit.pjt.member.application.dto.request;

import lombok.Getter;

@Getter
public class DeleteTokenRequest {
	private String accessToken;
	private String refreshToken;
}
