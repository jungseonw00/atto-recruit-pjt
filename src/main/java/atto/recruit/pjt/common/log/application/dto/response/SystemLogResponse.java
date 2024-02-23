package atto.recruit.pjt.common.log.application.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class SystemLogResponse {

	private Long id;
	private LocalDateTime createdAt;
	private String eventType;
	private String targetId;

	@QueryProjection
	public SystemLogResponse(Long id, LocalDateTime createdAt, String eventType, String targetId) {
		this.id = id;
		this.createdAt = createdAt;
		this.eventType = eventType;
		this.targetId = targetId;
	}
}