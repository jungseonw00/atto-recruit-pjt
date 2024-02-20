package atto.recruit.pjt.host.domain.dto.response;

import atto.recruit.pjt.host.domain.entity.AliveStatus;
import atto.recruit.pjt.host.domain.entity.Host;
import atto.recruit.pjt.host.domain.entity.HostStatusHistory;
import com.querydsl.core.annotations.QueryProjection;
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

	public static HostInfoResponse of(HostStatusHistory entity, Host host) {
		return HostInfoResponse.builder()
			.hostId(entity.getId())
			.name(host.getName())
			.ip(host.getIp())
			.aliveStatus(entity.getAliveStatus())
			.aliveTime(entity.getAliveTime())
			.build();
	}

	@QueryProjection
	public HostInfoResponse(Long hostId, String name, String ip, AliveStatus aliveStatus, LocalDateTime aliveTime) {
		this.hostId = hostId;
		this.name = name;
		this.ip = ip;
		this.aliveStatus = aliveStatus;
		this.aliveTime = aliveTime;
	}
}
