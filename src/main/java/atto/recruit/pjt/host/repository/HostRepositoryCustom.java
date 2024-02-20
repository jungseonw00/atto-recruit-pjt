package atto.recruit.pjt.host.repository;

import atto.recruit.pjt.host.domain.dto.request.HostCreateRequest;
import atto.recruit.pjt.host.domain.dto.request.HostInfoRequest;
import atto.recruit.pjt.host.domain.dto.response.HostCreateResponse;
import atto.recruit.pjt.host.domain.dto.response.HostInfoResponse;

public interface HostRepositoryCustom {
	HostCreateResponse registerHost(HostCreateRequest request);
	HostInfoResponse findOneHost(Long id, HostInfoRequest request);
}
