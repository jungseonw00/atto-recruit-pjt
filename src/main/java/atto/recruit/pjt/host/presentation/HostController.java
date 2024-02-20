package atto.recruit.pjt.host.presentation;

import atto.recruit.pjt.host.application.HostService;
import atto.recruit.pjt.host.domain.dto.request.HostCreateRequest;
import atto.recruit.pjt.host.domain.dto.request.HostUpdateRequest;
import atto.recruit.pjt.host.domain.dto.response.HostCreateResponse;
import atto.recruit.pjt.host.domain.dto.response.HostInfoResponse;
import jakarta.validation.Valid;
import java.io.IOException;
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
public class HostController {

	private final HostService hostService;

	// 등록
	@PostMapping("/{id}")
	public ResponseEntity<HostCreateResponse> registerHost(@PathVariable Long id, @RequestBody @Valid HostCreateRequest request) {
		return ResponseEntity.ok(hostService.registerHost(id, request));
	}

	// 조회
	@GetMapping("/{id}")
	public ResponseEntity<HostInfoResponse> findHostInfo(@PathVariable Long id) throws IOException {
		return ResponseEntity.ok(hostService.findHostInfo(id));
	}

	// 전체 조회
	@GetMapping("/all")
	public ResponseEntity<List<HostInfoResponse>> findAllHostInfo() throws IOException {
		return ResponseEntity.ok(hostService.findAllHostInfo());
	}

	// 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> deleteHost(@PathVariable Long id) {
		return ResponseEntity.ok(hostService.deleteHost(id));
	}

	// 수정
	@PatchMapping("/{id}")
	public ResponseEntity<Long> updateHost(@PathVariable Long id, @RequestBody @Valid HostUpdateRequest request) {
		return ResponseEntity.ok(hostService.updateHost(id, request));
	}
}
