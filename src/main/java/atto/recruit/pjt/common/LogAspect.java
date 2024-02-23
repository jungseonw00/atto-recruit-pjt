package atto.recruit.pjt.common;

import atto.recruit.pjt.common.log.domain.entity.SystemLog;
import atto.recruit.pjt.common.log.repository.SystemLogRepository;
import atto.recruit.pjt.member.application.dto.response.LogoutTokenResponse;
import atto.recruit.pjt.member.application.dto.response.MemberLoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Slf4j
@Transactional
@RequiredArgsConstructor
public class LogAspect {

	private final SystemLogRepository systemLogRepository;

	@AfterReturning(value = "execution(* atto.recruit.pjt.member.application.MemberService.memberLogin(..))", returning = "object")
	public void afterLogin(Object object) {
		MemberLoginResponse result = (MemberLoginResponse) object;
		SystemLog entity = SystemLog.createLog(result.getMemberId(), "login");
		systemLogRepository.save(entity);
		log.info("memberId => {}", result.getMemberId());
		log.info("accessToken => {}", result.getAccessToken());
	}

	@AfterReturning(value = "execution(* atto.recruit.pjt.member.application.MemberService.logout(..))", returning = "object")
	public void afterLogout(Object object) {
		LogoutTokenResponse result = (LogoutTokenResponse) object;
		SystemLog entity = SystemLog.createLog(result.getMemberId(), "logout");
		systemLogRepository.save(entity);
		log.info("memberId => {}", result.getMemberId());
		log.info("accessToken => {}", result.getAccessToken());
	}

	@AfterReturning(value = "execution(* atto.recruit.pjt.host.application.HostService.registerHost(..))", returning = "object")
	public void afterHostServiceRegister(Object object) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		SystemLog entity = SystemLog.builder()
			.targetId(userDetails.getUsername())
			.eventType("HOST CREATED")
			.build();
		systemLogRepository.save(entity);
	}

	@AfterReturning(value = "execution(* atto.recruit.pjt.host.application.HostService.findHostInfo(..))", returning = "object")
	public void afterHostServiceFind(Object object) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		SystemLog entity = SystemLog.builder()
			.targetId(userDetails.getUsername())
			.eventType("HOST FIND")
			.build();
		systemLogRepository.save(entity);
	}

	@AfterReturning(value = "execution(* atto.recruit.pjt.host.application.HostService.findAllHostInfo(..))", returning = "object")
	public void afterHostServiceFindAll(Object object) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		SystemLog entity = SystemLog.builder()
			.targetId(userDetails.getUsername())
			.eventType("HOST FINDALL")
			.build();
		systemLogRepository.save(entity);
	}

	@AfterReturning(value = "execution(* atto.recruit.pjt.host.application.HostService.deleteHost(..))", returning = "object")
	public void afterHostServiceDelete(Object object) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		SystemLog entity = SystemLog.builder()
			.targetId(userDetails.getUsername())
			.eventType("HOST DELETED")
			.build();
		systemLogRepository.save(entity);
	}

	@AfterReturning(value = "execution(* atto.recruit.pjt.host.application.HostService.updateHost(..))", returning = "object")
	public void afterHostServiceUpdate(Object object) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		SystemLog entity = SystemLog.builder()
			.targetId(userDetails.getUsername())
			.eventType("HOST UPDATED")
			.build();
		systemLogRepository.save(entity);
	}
}