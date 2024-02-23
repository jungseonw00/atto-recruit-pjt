package atto.recruit.pjt.common.log.repository;

import static atto.recruit.pjt.common.log.domain.entity.QSystemLog.systemLog;
import static com.querydsl.core.types.Projections.constructor;

import atto.recruit.pjt.common.log.application.dto.response.SystemLogResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class SystemLogRepositoryCustomImpl implements SystemLogRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<SystemLogResponse> findAllSystemLog() {
		return queryFactory
			.select(constructor(SystemLogResponse.class,
				systemLog.id,
				systemLog.createdAt,
				systemLog.eventType,
				systemLog.targetId))
			.from(systemLog)
			.fetch();
	}
}
