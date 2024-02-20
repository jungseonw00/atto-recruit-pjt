package atto.recruit.pjt.host.repository;

import static atto.recruit.pjt.host.domain.entity.AliveStatus.ALIVE;
import static atto.recruit.pjt.host.domain.entity.QHost.host;
import static atto.recruit.pjt.host.domain.entity.QHostStatusHistory.hostStatusHistory;
import static com.querydsl.core.types.Projections.constructor;

import atto.recruit.pjt.host.application.request.HostCreateRequest;
import atto.recruit.pjt.host.application.response.HostCreateResponse;
import atto.recruit.pjt.host.application.response.HostInfoResponse;
import atto.recruit.pjt.host.domain.entity.Host;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class HostRepositoryCustomImpl implements HostRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	private final EntityManager em;

	@Override
	public HostCreateResponse registerHost(HostCreateRequest request) {
		Host entity = Host.builder()
				.name(request.getName())
				.ip(request.getName())
				.build();
		em.persist(entity);
		return HostCreateResponse.of(entity);
	}

	@Override
	public List<HostInfoResponse> findAllHostStatusHistory() {
		return queryFactory.select(constructor(HostInfoResponse.class,
				host.id,
				hostStatusHistory.id.max(),
				host.name,
				host.ip,
				hostStatusHistory.aliveStatus,
				hostStatusHistory.aliveTime.max()))
			.from(host)
			.leftJoin(host.hostStatusHistory, hostStatusHistory)
			.on(hostStatusHistory.aliveStatus.eq(ALIVE))
			.groupBy(host.id)
			.fetch();
	}
}