package atto.recruit.pjt.host.domain.dto.request;

import atto.recruit.pjt.host.custom.ValidIpAddress;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HostUpdateRequest {
	@ValidIpAddress
	private String ip;
	@NotBlank
	private String name;
}
