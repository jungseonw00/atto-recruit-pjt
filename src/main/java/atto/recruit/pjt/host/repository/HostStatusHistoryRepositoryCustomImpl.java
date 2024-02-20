package atto.recruit.pjt.host.repository;

import static atto.recruit.pjt.host.domain.entity.QHostStatusHistory.hostStatusHistory;

import atto.recruit.pjt.host.domain.dto.response.HostInfoResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class HostStatusHistoryRepositoryCustomImpl implements HostStatusHistoryRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<HostInfoResponse> findAll2() {
		queryFactory.select(hostStatusHistory).from(hostStatusHistory);
		return null;
	}
}
