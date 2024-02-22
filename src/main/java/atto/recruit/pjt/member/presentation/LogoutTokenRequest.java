package atto.recruit.pjt.member.presentation;

import lombok.Getter;

@Getter
public class LogoutTokenRequest {
	private String memberId;
	private String accessToken;
}
