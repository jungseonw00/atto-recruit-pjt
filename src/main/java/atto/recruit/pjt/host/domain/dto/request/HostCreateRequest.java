package atto.recruit.pjt.host.domain.dto.request;

import atto.recruit.pjt.host.custom.ValidIpAddress;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HostCreateRequest {
	// 필수값
	@NotBlank
	private String name;
	@ValidIpAddress
	private String ip;
}
