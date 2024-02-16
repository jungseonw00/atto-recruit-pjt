package atto.recruit.pjt.host;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import atto.recruit.pjt.host.controller.HostService;
import atto.recruit.pjt.host.dto.HostCreateRequest;
import atto.recruit.pjt.host.entity.Host;
import atto.recruit.pjt.host.repository.HostRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
public class HostTest {

	@Autowired
	private EntityManager em;

	@Autowired
	private HostService hostService;

	@Autowired
	private HostRepository hostRepository;

	@BeforeEach
	void registerHost() {
		// given
		Host entity = Host
			.builder()
			.name("AWS")
			.ip("192.168.0.1")
			.build();
		em.persist(entity);
	}

	@Test
	void createHost() {
		// given
		Host entity = Host
			.builder()
			.name("AWS")
			.ip("192.168.0.1")
			.build();
		em.persist(entity);

		log.info("createdAt => {}", entity.getCreatedAt());
		log.info("updatedAt => {}", entity.getUpdatedAt());
		log.info("entity => {}", entity);
	}

	@Test
	@DisplayName("ip또는 name이 중복일경우 예외를 터트린다.")
	void duplicateTest() {
		// ip 중복
		HostCreateRequest dto1 = HostCreateRequest.builder().ip("192.168.0.1").build();
		assertThat(hostRepository.validateDuplicateIp(dto1)).isEqualTo(1);

		// name 중복
		HostCreateRequest dto2 = HostCreateRequest.builder().name("AWS").build();
		assertThat(hostRepository.validateDuplicateName(dto2)).isEqualTo(1);
	}

	@Test
	void duplicateTest2() {
		HostCreateRequest dto1 = HostCreateRequest.builder().ip("192.168.0.1").build();
		assertThatThrownBy(() ->
			hostService.createHost(dto1)).isInstanceOf(IllegalArgumentException.class).hasMessage("이미 등록된 IP입니다.");
	}

	@Test
	void deleteTest() {
	    // given
		HostCreateRequest request = HostCreateRequest.builder().ip("192.168.0.1").name("AWS").build();
		Long result = hostRepository.deleteHostByIpAndName(request.getName(), request.getIp());
		log.info("result => {}", result);
		// when
	}
}
