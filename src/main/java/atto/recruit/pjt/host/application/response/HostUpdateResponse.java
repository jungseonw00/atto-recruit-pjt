package atto.recruit.pjt.host.application.response;

import atto.recruit.pjt.host.domain.entity.Host;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
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
