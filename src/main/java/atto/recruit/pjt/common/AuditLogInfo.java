package atto.recruit.pjt.common;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

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
public class AuditLogInfo extends BaseTimeEntity<AuditLogInfo, Long> {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "audit_log_info_id")
	private Long id;

	private String identification;

	private String typeOfCase;

	private String resultOfCase;
}
