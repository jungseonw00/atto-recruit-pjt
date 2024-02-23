package atto.recruit.pjt.host.domain.entity;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import atto.recruit.pjt.common.BaseTimeEntity;
import atto.recruit.pjt.host.application.dto.request.HostUpdateRequest;
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

	@Builder.Default
	@OneToMany(mappedBy = "host", cascade = ALL)
	private List<HostStatusHistory> hostStatusHistory = new ArrayList<>();

	public void update(HostUpdateRequest request) {
		this.name = request.getName();
		this.ip = request.getIp();
	}
}
