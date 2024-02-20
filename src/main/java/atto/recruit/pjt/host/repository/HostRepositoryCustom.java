package atto.recruit.pjt.host.repository;

import atto.recruit.pjt.host.application.request.HostCreateRequest;
import atto.recruit.pjt.host.application.response.HostCreateResponse;
import atto.recruit.pjt.host.application.response.HostInfoResponse;
import java.util.List;

public interface HostRepositoryCustom {
	HostCreateResponse registerHost(HostCreateRequest request);
	List<HostInfoResponse> findAllHostStatusHistory();
}
