package atto.recruit.pjt.host.repository;

import static atto.recruit.pjt.host.entity.QHost.host;

import atto.recruit.pjt.host.dto.HostCreateRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class HostRepositoryCustomImpl implements HostRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Long validateDuplicateIp(HostCreateRequest request) {
		return queryFactory
				.select(host.count())
				.from(host)
				.where((host.ip.eq(request.getIp())))
				.fetchOne();

	}

	@Override
	public Long validateDuplicateName(HostCreateRequest request) {
		return queryFactory
			.select(host.count())
			.from(host)
			.where(host.name.eq(request.getName()))
			.fetchOne();
	}

	@Override
	public Long validateHostCnt(HostCreateRequest request) {
		return queryFactory
			.select(host.count())
			.from(host)
			.fetchOne();
	}
}
