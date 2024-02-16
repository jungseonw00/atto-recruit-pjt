package atto.recruit.pjt.host.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import atto.recruit.pjt.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = PROTECTED)
@ToString
public class Host extends BaseTimeEntity<Host, Long> {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "host_id")
	private Long id;
	private String name;
	private String ip;

	@Builder
	public Host(String name, String ip) {
		this.name = name;
		this.ip = ip;
	}
}
