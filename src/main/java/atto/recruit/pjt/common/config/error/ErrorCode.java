package atto.recruit.pjt.common.config.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	ACCESS_TOKEN_EXPIRED(401, "C_001", "토큰이 만료되었습니다."),
	HANDLE_ACCESS_DENIED(402, "C_002", "권한이 없습니다."),
	MEMBER_NOT_FOUND(403, "C_003", "사용자 정보를 찾을 수 없습니다."),
	DUPLICATE_MEMBER(404, "C_004", "이미 존재하는 아이디입니다."),
	HOST_NOT_FOUND(405, "C_005", "등록되지 않은 호스트입니다."),
	DUPLICATE_NAME(406, "C_006", "이미 존재하는 이름입니다."),
	DUPLICATE_IP(407, "C_007", "이미 존재하는 IP입니다."),
	HOST_REGISTER_DENIED(408, "C_008", "호스트의 수는 100개를 넘을 수 없습니다."),
	REFRESH_TOKEN_NOT_FOUND(409, "C_009", "갱신 토큰을 찾을 수 없습니다."),
	NOT_REACHABLE_HOST(501, "C_501", "호스트에 연결하던중 오류가 발생하였습니다."),
	SERVER_ERROR(502, "C502", "서버에 에러가 발생하였습니다.");

	private final int status;
	private final String code;
	private final String message;
}
