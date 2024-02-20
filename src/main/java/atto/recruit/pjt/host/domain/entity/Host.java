package atto.recruit.pjt.host.domain.entity;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import atto.recruit.pjt.common.BaseTimeEntity;
import atto.recruit.pjt.host.application.request.HostCreateRequest;
import atto.recruit.pjt.host.application.request.HostUpdateRequest;
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

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Host extends BaseTimeEntity<Host, Long> {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "host_id")
	private Long id;

	private String name;

	private String ip;

	@OneToMany(mappedBy = "host", cascade = ALL, fetch = LAZY)
	private List<HostStatusHistory> hostStatusHistory = new ArrayList<>();

	public static Host registerHost(HostCreateRequest request) {
		Host entity = new Host();
		entity.ip = request.getIp();
		entity.name = request.getName();
		return entity;
	}

	public void update(HostUpdateRequest request) {
		this.name = request.getName();
		this.ip = request.getIp();
	}
}
