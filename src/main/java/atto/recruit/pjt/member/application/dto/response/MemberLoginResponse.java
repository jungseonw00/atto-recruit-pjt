package atto.recruit.pjt.member.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLoginResponse {
	private String memberId;
	private String accessToken;
	private String refreshToken;

	public static MemberLoginResponse of(String memberId, String accessToken, String refreshToken) {
		return MemberLoginResponse.builder()
			   .memberId(memberId)
			   .accessToken(accessToken)
			   .refreshToken(refreshToken)
			   .build();
	}
}

