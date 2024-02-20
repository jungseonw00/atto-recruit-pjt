package atto.recruit.pjt.host.application;

import static atto.recruit.pjt.common.Host.MEMBER_LIMIT;
import static atto.recruit.pjt.host.domain.entity.AliveStatus.alive;
import static atto.recruit.pjt.host.domain.entity.AliveStatus.notAlive;
import static java.time.LocalDateTime.now;

import atto.recruit.pjt.host.domain.dto.request.HostCreateRequest;
import atto.recruit.pjt.host.domain.dto.request.HostUpdateRequest;
import atto.recruit.pjt.host.domain.dto.response.HostCreateResponse;
import atto.recruit.pjt.host.domain.dto.response.HostInfoResponse;
import atto.recruit.pjt.host.domain.entity.AliveStatus;
import atto.recruit.pjt.host.domain.entity.Host;
import atto.recruit.pjt.host.domain.entity.HostStatusHistory;
import atto.recruit.pjt.host.repository.HostRepository;
import atto.recruit.pjt.host.repository.HostStatusHistoryRepository;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HostService {

	private final HostRepository hostRepository;
	private final HostStatusHistoryRepository hostStatusHistoryRepository;

	public HostCreateResponse registerHost(Long id, HostCreateRequest request) {
		validateRegisterHost(request);
		return hostRepository.registerHost(request);
	}

	private void validateRegisterHost(HostCreateRequest request) {

		hostRepository.findByName(request.getName()).ifPresent(m -> {
			throw new IllegalArgumentException("이미 존재하는 이름입니다.");
		});

		hostRepository.findByIp(request.getIp()).ifPresent(m -> {
			throw new IllegalArgumentException("이미 존재하는 IP입니다.");
		});

		validateCnt();
	}

	private boolean isReachable(Long id) throws IOException {
		return InetAddress.getByName(String.valueOf(id)).isReachable(1000);
	}

	private void validateCnt() {
		Long cnt = hostRepository.count();

		if (validateMemberLimit(cnt)) {
			throw new IllegalArgumentException("호스트의 수는 100개를 넘을 수 없습니다.");
		}
	}

	private static boolean validateMemberLimit(Long cnt) {
		return cnt > MEMBER_LIMIT.getNum();
	}

	public List<HostInfoResponse> findAllHostInfo() {
//		return hostRepository.findAll()
//			.stream()
//			.map(HostInfoResponse::of)
//			.toList();
		return null;
	}

	public HostInfoResponse findHostInfo(Long id) throws IOException {
		Host entity = hostRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 IP를 조회할 수 없습니다."));

		AliveStatus status = validateStatus(id);

		HostStatusHistory hostHistory = HostStatusHistory.builder()
				.host(entity)
				.aliveTime(now())
				.aliveStatus(status)
				.build();

		hostStatusHistoryRepository.save(hostHistory);

		return HostInfoResponse.of(hostHistory);
	}

	private AliveStatus validateStatus(Long id) throws IOException {
		if (InetAddress.getByName(String.valueOf(id)).isReachable(1000)) {
			return alive;
		}
		return notAlive;
	}

	public Long deleteHost(Long hostId) {
		hostRepository.deleteById(hostId);
		return hostId;
	}

	public Long updateHost(Long id, HostUpdateRequest request) {
		Host entity = hostRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("등록된 호스트를 찾을 수 없습니다."));
		entity.update(request);
		return entity.getId();
	}
}
