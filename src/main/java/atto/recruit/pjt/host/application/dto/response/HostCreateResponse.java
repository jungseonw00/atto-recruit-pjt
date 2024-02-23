package atto.recruit.pjt.host.application.dto.response;

import atto.recruit.pjt.host.domain.entity.Host;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class HostCreateResponse {
	private Long hostId;
	private String name;
	private String ip;
	private String userId;

	public static HostCreateResponse of(Host entity, String userId) {
		return HostCreateResponse.builder()
			.hostId(entity.getId())
			.name(entity.getName())
			.ip(entity.getIp())
			.userId(userId)
			.build();
	}
}
