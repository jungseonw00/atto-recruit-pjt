package atto.recruit.pjt.host.application.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class HostUpdateRequest {
	private String ip;
	private String name;
}
