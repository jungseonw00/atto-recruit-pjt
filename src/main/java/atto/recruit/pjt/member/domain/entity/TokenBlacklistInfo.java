package atto.recruit.pjt.member.domain.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import atto.recruit.pjt.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class TokenBlacklistInfo extends BaseTimeEntity<TokenBlacklistInfo, Long> {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String memberId;
	private String accessToken;

	public static TokenBlacklistInfo registerBlacklist(String accessToken, String memberId) {
		return TokenBlacklistInfo.builder()
			.accessToken(accessToken)
			.memberId(memberId)
			.build();
	}
}
