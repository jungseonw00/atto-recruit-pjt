package atto.recruit.pjt.host.application.request;

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
	@NotBlank
	private String name;
	@ValidIpAddress
	private String ip;
}