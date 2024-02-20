package atto.recruit.pjt.host.repository;

import atto.recruit.pjt.host.domain.dto.request.HostCreateRequest;
import atto.recruit.pjt.host.domain.dto.request.HostInfoRequest;
import atto.recruit.pjt.host.domain.dto.response.HostCreateResponse;
import atto.recruit.pjt.host.domain.dto.response.HostInfoResponse;
import atto.recruit.pjt.host.domain.entity.Host;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
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
		Host entity = Host.registerHost(request);
		em.persist(entity);
		return HostCreateResponse.of(entity);
	}

	@Override
	public HostInfoResponse findOneHost(Long id, HostInfoRequest request) {
//		Host entity = queryFactory
//				.select(host)
//				.from(host)
//				.where(host.id.eq(id)
//				.and(host.ip.eq(request.getIp())
//				.and(host.name.eq(request.getName()))))
//				.fetchOne();
//		return HostInfoResponse.of(entity);
		return null;
	}
}