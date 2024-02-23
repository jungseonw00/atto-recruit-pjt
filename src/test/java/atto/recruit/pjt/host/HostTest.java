package atto.recruit.pjt.host;

import static atto.recruit.pjt.host.domain.entity.AliveStatus.ALIVE;
import static atto.recruit.pjt.host.domain.entity.QHost.host;
import static atto.recruit.pjt.host.domain.entity.QHostStatusHistory.hostStatusHistory;
import static com.querydsl.core.types.Projections.constructor;
import static java.time.LocalDateTime.now;

import atto.recruit.pjt.host.application.HostService;
import atto.recruit.pjt.host.application.dto.request.HostCreateRequest;
import atto.recruit.pjt.host.application.dto.response.HostInfoResponse;
import atto.recruit.pjt.host.domain.entity.Host;
import atto.recruit.pjt.host.domain.entity.HostStatusHistory;
import atto.recruit.pjt.host.repository.HostRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
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

	private JPAQueryFactory queryFactory;

	@BeforeEach
	void beforeEachRegisterHost() {
		queryFactory = new JPAQueryFactory(em);
		// given
		Host entity = Host.builder()
				.name("AWS")
				.ip("192.168.0.1")
				.build();
		em.persist(entity);

		HostStatusHistory statusHistory = HostStatusHistory.builder()
					.aliveStatus(ALIVE)
					.aliveTime(now())
					.host(entity)
					.build();
		em.persist(statusHistory);
	}

//	@Test
//	void registerHost() {
//		// given
//		HostCreateRequest request = HostCreateRequest.builder()
//					.ip("142.250.66.100")
//					.name("google")
//					.build();
//		hostService.registerHost(request);
//
//		Host entity = hostRepository.findByName(request.getName()).get();
//
//		assertThat(request.getIp()).isEqualTo(entity.getIp());
//	}

	@Test
	@DisplayName("ip또는 name이 중복일경우 예외를 터트린다.")
	void duplicateTest() {
		// ip 중복
		HostCreateRequest dto1 = HostCreateRequest.builder().ip("192.168.0.1").build();
//		assertThat(hostService.valid(dto1)).isEqualTo(1);

		// name 중복
		HostCreateRequest dto2 = HostCreateRequest.builder().name("AWS").build();
//		assertThat(hostRepository.validateDuplicateName(dto2)).isEqualTo(1);
	}

	@Test
	void deleteTest() {
	    // given
		HostCreateRequest request = HostCreateRequest.builder().ip("192.168.0.1").name("AWS").build();
		Long result = hostRepository.deleteHostByIpAndName(request.getName(), request.getIp());
		log.info("result => {}", result);
		// when
	}

	@Test
	void reachableTest() throws IOException {
	    // given
		boolean reachable = InetAddress.getByName("www.google.com").isReachable(1000);
		log.info("reachable => {}", reachable);
	}

	@Test
	void findAll() {
		List<HostInfoResponse> entities = queryFactory.select(constructor(HostInfoResponse.class,
				host.id,
				hostStatusHistory.id.max(),
				host.name,
				host.ip,
				hostStatusHistory.aliveStatus,
				hostStatusHistory.aliveTime.max()))
			.from(host)
			.leftJoin(host.hostStatusHistory, hostStatusHistory)
			.on(hostStatusHistory.aliveStatus.eq(ALIVE))
			.groupBy(host.id)
			.fetch();

		for (HostInfoResponse entity : entities) {
			System.out.println("entity = " + entity);
		}
	}
}
