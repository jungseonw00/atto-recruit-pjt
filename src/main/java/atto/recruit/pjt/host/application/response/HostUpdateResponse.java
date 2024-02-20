package atto.recruit.pjt.host.application.response;

import atto.recruit.pjt.host.domain.entity.Host;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HostUpdateResponse {
	private Long hostId;
	private String name;
	private String ip;

	public static HostUpdateResponse of(Host entity) {
		return HostUpdateResponse.builder()
			.hostId(entity.getId())
			.name(entity.getName())
			.ip(entity.getIp())
			.build();
	}
}
