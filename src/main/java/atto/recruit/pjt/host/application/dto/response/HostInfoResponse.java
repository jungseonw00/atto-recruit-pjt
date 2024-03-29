package atto.recruit.pjt.host.application.dto.response;

import atto.recruit.pjt.host.domain.entity.AliveStatus;
import atto.recruit.pjt.host.domain.entity.Host;
import atto.recruit.pjt.host.domain.entity.HostStatusHistory;
import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HostInfoResponse {
	private Long hostId;
	private Long hostStatusHistoryId;
	private String name;
	private String ip;
	private AliveStatus aliveStatus;
	private LocalDateTime aliveTime;

	public static HostInfoResponse of(HostStatusHistory entity, Host host) {
		return HostInfoResponse.builder()
			.hostId(host.getId())
			.hostStatusHistoryId(entity.getId())
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

	@QueryProjection
	public HostInfoResponse(Long hostId, Long hostStatusHistoryId, String name, String ip,
		AliveStatus aliveStatus) {
		this.hostId = hostId;
		this.hostStatusHistoryId = hostStatusHistoryId;
		this.name = name;
		this.ip = ip;
		this.aliveStatus = aliveStatus;
	}
}
