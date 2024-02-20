package atto.recruit.pjt.host.domain.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import atto.recruit.pjt.common.BaseTimeEntity;
import atto.recruit.pjt.host.domain.dto.request.HostCreateRequest;
import atto.recruit.pjt.host.domain.dto.request.HostInfoRequest;
import atto.recruit.pjt.host.domain.dto.request.HostUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
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
public class Host extends BaseTimeEntity<Host, Long> {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "host_id")
	private Long id;
	private String name;
	private String ip;

	@OneToMany(mappedBy = "host")
	private List<HostStatusHistory> hostStatusHistory = new ArrayList<>();

	public static Host registerHost(HostCreateRequest request) {
		Host entity = new Host();
		entity.ip = request.getIp();
		entity.name = request.getName();
		return entity;
	}

	public static Host of(HostInfoRequest request) {
		return Host.builder()
			.id(request.getHostId())
			.ip(request.getIp())
			.name(request.getName())
			.build();
	}

	public void update(HostUpdateRequest request) {
		this.name = request.getName();
		this.ip = request.getIp();
	}
}
