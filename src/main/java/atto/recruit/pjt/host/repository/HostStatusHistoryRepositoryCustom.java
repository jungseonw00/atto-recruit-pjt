package atto.recruit.pjt.host.repository;

import atto.recruit.pjt.host.domain.dto.response.HostInfoResponse;
import java.util.List;

public interface HostStatusHistoryRepositoryCustom {
	List<HostInfoResponse> findAll2();
}
