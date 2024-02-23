package atto.recruit.pjt.host.application;

import static atto.recruit.pjt.common.config.error.ErrorCode.DUPLICATE_IP;
import static atto.recruit.pjt.common.config.error.ErrorCode.DUPLICATE_NAME;
import static atto.recruit.pjt.common.config.error.ErrorCode.HOST_NOT_FOUND;
import static atto.recruit.pjt.common.config.error.ErrorCode.HOST_REGISTER_DENIED;
import static atto.recruit.pjt.common.config.error.ErrorCode.NOT_REACHABLE_HOST;
import static atto.recruit.pjt.host.domain.entity.HostEnum.MEMBER_LIMIT;
import static atto.recruit.pjt.host.domain.entity.HostEnum.REACHABLE_TIMEOUT_LIMIT;

import atto.recruit.pjt.common.config.error.exception.CustomException;
import atto.recruit.pjt.host.application.dto.request.HostCreateRequest;
import atto.recruit.pjt.host.application.dto.request.HostUpdateRequest;
import atto.recruit.pjt.host.application.dto.response.HostCreateResponse;
import atto.recruit.pjt.host.application.dto.response.HostDeleteResponse;
import atto.recruit.pjt.host.application.dto.response.HostInfoResponse;
import atto.recruit.pjt.host.application.dto.response.HostUpdateResponse;
import atto.recruit.pjt.host.domain.entity.Host;
import atto.recruit.pjt.host.domain.entity.HostStatusHistory;
import atto.recruit.pjt.host.repository.HostRepository;
import atto.recruit.pjt.host.repository.HostStatusHistoryRepository;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class HostService {

	private final HostRepository hostRepository;
	private final HostStatusHistoryRepository hostStatusHistoryRepository;

	public HostCreateResponse registerHost(HostCreateRequest request) {
		validateRegisterHost(request);
		return hostRepository.registerHost(request);
	}

	public HostInfoResponse findHostInfo(Long id) {

		Host entity = hostRepository.findById(id)
			.orElseThrow(() -> new CustomException(HOST_NOT_FOUND));

		boolean status = validateReachable(entity);

		HostStatusHistory hostStatusHistory = HostStatusHistory.create(entity, status);
		hostStatusHistoryRepository.save(hostStatusHistory);

		return HostInfoResponse.of(hostStatusHistory, entity);
	}

	public List<HostInfoResponse> findAllHostInfo() {
		List<Host> entities = hostRepository.findAll();

		entities.forEach(entity -> {
			boolean status = validateReachable(entity);
			HostStatusHistory hostStatusHistory = HostStatusHistory.create(entity, status);
			hostStatusHistoryRepository.save(hostStatusHistory);
		});

		return hostRepository.findAllHostStatusHistory();
	}

	public HostDeleteResponse deleteHost(Long id) {
		hostRepository.findById(id)
			.orElseThrow(() -> new CustomException(HOST_NOT_FOUND));

		hostRepository.deleteById(id);

		return HostDeleteResponse.of(id);
	}

	public HostUpdateResponse updateHost(Long id, HostUpdateRequest request) {
		Host entity = hostRepository.findById(id)
			.orElseThrow(() -> new CustomException(HOST_NOT_FOUND));
		entity.update(request);
		return HostUpdateResponse.of(entity);
	}

	private void validateRegisterHost(HostCreateRequest request) {

		hostRepository.findByName(request.getName()).ifPresent(m -> {
			throw new CustomException(DUPLICATE_NAME);
		});

		hostRepository.findByIp(request.getIp()).ifPresent(m -> {
			throw new CustomException(DUPLICATE_IP);
		});

		validateCnt();
	}

	private boolean validateMemberRegistrationLimit(Long cnt) {
		return cnt > MEMBER_LIMIT.getNum();
	}

	private boolean validateReachable(Host entity) {
		try {
			return InetAddress.getByName(String.valueOf(entity.getIp())).isReachable(REACHABLE_TIMEOUT_LIMIT.getNum());
		} catch (IOException e) {
			throw new CustomException(NOT_REACHABLE_HOST);
		}
	}

	private void validateCnt() {
		Long cnt = hostRepository.count();

		if (validateMemberRegistrationLimit(cnt)) {
			throw new CustomException(HOST_REGISTER_DENIED);
		}
	}
}
