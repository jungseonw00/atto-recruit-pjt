package atto.recruit.pjt.member.application.response;

import atto.recruit.pjt.member.domain.entity.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRegisterResponse {
	private String memberId;

	public static MemberRegisterResponse of(Member entity) {
		return MemberRegisterResponse.builder()
				.memberId(entity.getMemberId())
				.build();
	}
}
