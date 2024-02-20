package atto.recruit.pjt.member.presentation;

import atto.recruit.pjt.member.application.MemberService;
import atto.recruit.pjt.member.application.request.MemberLoginRequest;
import atto.recruit.pjt.member.application.request.MemberRegisterRequest;
import atto.recruit.pjt.member.application.request.RefreshTokenRequest;
import atto.recruit.pjt.member.application.response.MemberRegisterResponse;
import atto.recruit.pjt.member.domain.entity.Tokens;
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
public class MemberController {

	private final MemberService memberService;

	@PostMapping
	public ResponseEntity<MemberRegisterResponse> register(@RequestBody @Valid MemberRegisterRequest request) {
		return ResponseEntity.ok(memberService.registerMember(request));
	}

	@PostMapping("/login")
	public ResponseEntity<Tokens> login(@RequestBody @Valid MemberLoginRequest request) {
		return ResponseEntity.ok(memberService.memberLogin(request));
	}

	@PostMapping("/refresh")
	public ResponseEntity<Tokens> refresh(@RequestBody RefreshTokenRequest request) {
		return ResponseEntity.ok(memberService.refresh(request.getRefreshToken()));
	}
}
