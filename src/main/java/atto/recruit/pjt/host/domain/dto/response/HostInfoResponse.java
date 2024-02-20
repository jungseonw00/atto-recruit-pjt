package atto.recruit.pjt.host.domain.dto.response;

import atto.recruit.pjt.host.domain.entity.AliveStatus;
import atto.recruit.pjt.host.domain.entity.HostStatusHistory;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HostInfoResponse {
	private Long hostId;
	private String name;
	private String ip;
	private AliveStatus aliveStatus;
	private LocalDateTime aliveTime;

	public static HostInfoResponse of(HostStatusHistory entity) {
		return HostInfoResponse.builder()
			.hostId(entity.getId())
			.aliveTime(entity.getAliveTime())
			.aliveStatus(entity.getAliveStatus())
			.build();
	}
}
