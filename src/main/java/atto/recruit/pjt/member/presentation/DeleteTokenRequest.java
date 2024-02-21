package atto.recruit.pjt.member.presentation;

import lombok.Getter;

@Getter
public class DeleteTokenRequest {
	private String accessToken;
	private String refreshToken;
}
