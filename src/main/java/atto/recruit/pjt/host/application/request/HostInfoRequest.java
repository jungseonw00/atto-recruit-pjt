package atto.recruit.pjt.host.application.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HostInfoRequest {
	private Long hostId;
	private String ip;
	private String name;
}
