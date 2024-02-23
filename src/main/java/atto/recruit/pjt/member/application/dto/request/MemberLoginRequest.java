package atto.recruit.pjt.member.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginRequest {
	@NotBlank
	private String memberId;
	@NotBlank
	private String password;
}
