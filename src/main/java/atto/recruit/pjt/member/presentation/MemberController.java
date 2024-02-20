package atto.recruit.pjt.member.presentation;

import atto.recruit.pjt.member.application.MemberService;
import atto.recruit.pjt.member.application.request.MemberLoginRequest;
import atto.recruit.pjt.member.application.request.MemberRegisterRequest;
import atto.recruit.pjt.member.application.request.RefreshTokenRequest;
import atto.recruit.pjt.member.application.response.MemberRegisterResponse;
import atto.recruit.pjt.member.domain.entity.Tokens;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Tag(name = "member", description = "회원 API")
public class MemberController {

	private final MemberService memberService;

	@Operation(summary = "회원을 등록한다.", responses = {
		@ApiResponse(responseCode = "200", description = "정상적으로 회원가입 완료"),
		@ApiResponse(responseCode = "410", description = "이미 존재하는 아이디")
	})
	@PostMapping
	public ResponseEntity<MemberRegisterResponse> register(@RequestBody @Valid MemberRegisterRequest request) {
		return ResponseEntity.ok(memberService.registerMember(request));
	}

	@Operation(summary = "로그인을 한다.", responses = {
		@ApiResponse(responseCode = "200", description = "계정 검증에 성공하면 Access, Refresh Token을 발급한다."),
		@ApiResponse(responseCode = "403", description = "사용자 정보를 찾을 수 없음")
	})
	@PostMapping("/login")
	public ResponseEntity<Tokens> login(@RequestBody @Valid MemberLoginRequest request) {
		return ResponseEntity.ok(memberService.memberLogin(request));
	}

	@Operation(summary = "토큰을 갱신한다.", responses = {
		@ApiResponse(responseCode = "200", description = "토큰을 검증한 뒤 재발급 성공"),
		@ApiResponse(responseCode = "401", description = "토큰이 만료되었음"),
		@ApiResponse(responseCode = "409", description = "갱신 토큰을 찾을 수 없음"),
		@ApiResponse(responseCode = "403", description = "토큰에 있는 사용자를 찾을 수 없음"),
	})
	@PostMapping("/refresh")
	public ResponseEntity<Tokens> refresh(@RequestBody RefreshTokenRequest request) {
		return ResponseEntity.ok(memberService.refresh(request.getRefreshToken()));
	}
}
