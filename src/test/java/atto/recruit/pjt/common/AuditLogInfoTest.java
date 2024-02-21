package atto.recruit.pjt.common;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import atto.recruit.pjt.host.repository.HostRepository;
import atto.recruit.pjt.member.application.MemberService;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class AuditLogInfoTest {

	@Autowired
	private EntityManager em;

	private MemberService memberService;


	@Test
	void registerLog() {
	    // given
		AuditLogInfo entity = AuditLogInfo.builder()
				.identification("일반인")
				.typeOfCase("계정")
				.resultOfCase("로그인")
				.build();

		em.persist(entity);

		AuditLogInfo result = em.find(AuditLogInfo.class, entity.getId());
		// then
	    assertThat(entity.getId()).isEqualTo(result.getId());
	}

	@Test
	void invalidateToken() {
	    // given
		hostRepository.

	    // when


	    // then
	    assertThat();

	}
}