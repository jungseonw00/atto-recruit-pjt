package atto.recruit.pjt.host.repository;

import atto.recruit.pjt.host.application.dto.request.HostCreateRequest;
import atto.recruit.pjt.host.application.dto.response.HostCreateResponse;
import atto.recruit.pjt.host.application.dto.response.HostInfoResponse;
import java.util.List;

public interface HostRepositoryCustom {
	HostCreateResponse registerHost(HostCreateRequest request);
	List<HostInfoResponse> findAllHostStatusHistory();
}
