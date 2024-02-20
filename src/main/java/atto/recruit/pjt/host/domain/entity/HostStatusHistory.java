package atto.recruit.pjt.host.domain.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import atto.recruit.pjt.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@ToString
@Getter
@Builder
public class HostStatusHistory extends BaseTimeEntity<HostStatusHistory, Long> {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "host_status_history_id")
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "host_id")
	private Host host;

	private LocalDateTime aliveTime;

	@Enumerated(STRING)
	private AliveStatus aliveStatus;

}
