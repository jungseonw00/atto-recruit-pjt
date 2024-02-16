package atto.recruit.pjt.host.controller;

import atto.recruit.pjt.host.dto.HostCreateRequest;
import atto.recruit.pjt.host.dto.HostCreateResponse;
import atto.recruit.pjt.host.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HostService {

	private final HostRepository hostRepository;

	public HostCreateResponse createHost(HostCreateRequest request) {
		// 1. name, ip 중복 체크
		validateDuplicationIp(request);
		validateDuplicationName(request);
		validateHostCnt(request);
		// 2. 호스트 등록은 100개로 제한
		return null;
	}

	private void validateDuplicationIp(HostCreateRequest request) {
		Long cnt = hostRepository.validateDuplicateIp(request);
		if (cnt > 0) {
			throw new IllegalArgumentException("이미 등록된 IP입니다.");
		}
	}

	private void validateDuplicationName(HostCreateRequest request) {
		Long cnt = hostRepository.validateDuplicateName(request);
		if (cnt > 0) {
			throw new IllegalArgumentException("이미 등록된 이름입니다.");
		}
	}

	private void validateHostCnt(HostCreateRequest request) {
		Long cnt = hostRepository.validateHostCnt(request);
		if (cnt > 100) {
			throw new IllegalArgumentException("더 이상 호스트를 등록할 수 없습니다.");
		}
	}
}
