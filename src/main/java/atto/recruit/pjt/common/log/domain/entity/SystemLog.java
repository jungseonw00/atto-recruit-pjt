package atto.recruit.pjt.common.log.domain.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import atto.recruit.pjt.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class SystemLog extends BaseTimeEntity<SystemLog, Long> {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "system_log_id")
	private Long id;

	private String targetId;

	private String eventType;

	public static SystemLog createLog(String targetId, String eventType) {
		return SystemLog.builder()
			.targetId(targetId)
			.eventType(eventType)
			.build();
	}
}
