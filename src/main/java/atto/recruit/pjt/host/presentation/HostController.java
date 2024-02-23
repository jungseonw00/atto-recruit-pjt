package atto.recruit.pjt.host.presentation;

import atto.recruit.pjt.host.application.dto.response.HostDeleteResponse;
import atto.recruit.pjt.host.application.HostService;
import atto.recruit.pjt.host.application.dto.request.HostCreateRequest;
import atto.recruit.pjt.host.application.dto.request.HostUpdateRequest;
import atto.recruit.pjt.host.application.dto.response.HostCreateResponse;
import atto.recruit.pjt.host.application.dto.response.HostInfoResponse;
import atto.recruit.pjt.host.application.dto.response.HostUpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/host")
@Tag(name = "host", description = "호스트 등록, 조회, 수정, 삭제 API")
public class HostController {

	private final HostService hostService;

	@Operation(summary = "HOST를 등록한다.", responses = {
		@ApiResponse(responseCode = "200", description = "HOST 등록 성공"),
		@ApiResponse(responseCode = "410", description = "중복된 호스트 이름"),
		@ApiResponse(responseCode = "407", description = "중복된 호스트 IP"),
		@ApiResponse(responseCode = "408", description = "등록된 호스트 수가 100개 초과")
	})
	@PostMapping
	public ResponseEntity<HostCreateResponse> registerHost(@RequestBody @Valid HostCreateRequest request) {
		return ResponseEntity.ok(hostService.registerHost(request));
	}

	@Operation(summary = "하나의 호스트를 조회한다.", responses = {
		@ApiResponse(responseCode = "200", description = "호스트 조회 성공"),
		@ApiResponse(responseCode = "405", description = "호스트가 존재하지 않음"),
		@ApiResponse(responseCode = "501", description = "호스트 상태 조회중 오류 발생")
	})
	@GetMapping("/{id}")
	public ResponseEntity<HostInfoResponse> findHostInfo(@PathVariable Long id) {
		return ResponseEntity.ok(hostService.findHostInfo(id));
	}

	@Operation(summary = "여러개의 호스트를 조회한다.", responses = {
		@ApiResponse(responseCode = "200", description = "호스트 조회 성공"),
		@ApiResponse(responseCode = "501", description = "호스트 상태 조회중 오류 발생")
	})
	@GetMapping
	public ResponseEntity<List<HostInfoResponse>> findAllHostInfo() {
		return ResponseEntity.ok(hostService.findAllHostInfo());
	}

	@Operation(summary = "HOST를 삭제한다.", responses = {
		@ApiResponse(responseCode = "200", description = "호스트 삭제 성공"),
		@ApiResponse(responseCode = "405", description = "등록되지 않은 호스트")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<HostDeleteResponse> deleteHost(@PathVariable Long id) {
		return ResponseEntity.ok(hostService.deleteHost(id));
	}

	@Operation(summary = "HOST를 수정한다.", responses = {
		@ApiResponse(responseCode = "200", description = "호스트 삭제 성공"),
		@ApiResponse(responseCode = "405", description = "등록되지 않은 호스트")
	})
	@PatchMapping("/{id}")
	public ResponseEntity<HostUpdateResponse> updateHost(@PathVariable Long id, @RequestBody @Valid HostUpdateRequest request) {
		return ResponseEntity.ok(hostService.updateHost(id, request));
	}
}
