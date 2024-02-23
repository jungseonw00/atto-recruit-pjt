package atto.recruit.pjt.common.log.presentation;

import atto.recruit.pjt.common.log.application.dto.response.SystemLogResponse;
import atto.recruit.pjt.common.log.repository.SystemLogRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
@Tag(name = "log", description = "시스템 로그 조회")
public class SystemLogController {

	private final SystemLogRepository systemLogRepository;

	@Operation(summary = "시스템 로그 전체를 조회한다.", responses = {
		@ApiResponse(responseCode = "200", description = "시스템 로그 전체를 조회한다."),
	})
	@GetMapping
	public ResponseEntity<List<SystemLogResponse>> findAllSystemLog() {
		return ResponseEntity.ok(systemLogRepository.findAllSystemLog());
	}
}
