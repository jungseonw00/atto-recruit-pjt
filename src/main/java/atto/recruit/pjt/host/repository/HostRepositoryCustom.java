package atto.recruit.pjt.host.repository;

import atto.recruit.pjt.host.dto.HostCreateRequest;

public interface HostRepositoryCustom {
	Long validateDuplicateIp(HostCreateRequest request);
	Long validateDuplicateName(HostCreateRequest request);
	Long validateHostCnt(HostCreateRequest request);
}
