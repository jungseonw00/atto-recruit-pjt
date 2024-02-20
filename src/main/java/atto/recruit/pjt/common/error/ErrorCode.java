package atto.recruit.pjt.common.error;

public enum ErrorCode {
  ACCESS_TOKEN_EXPIRED(400, "C_001", "토큰이 만료되었습니다."),
  HANDLE_ACCESS_DENIED(401, "C_002", "권한이 없습니다."),
  MEMBER_NOT_FOUND(402, "C_003", "사용자 정보를 찾을 수 없습니다."),
  NOT_FOUND_SCRAP(403, "C_004", "스크랩한 정보를 찾을 수 없습니다.");

  private final String code;
  private final String message;
  private final int status;

  ErrorCode(int status, String code, String message) {
    this.status = status;
    this.message = message;
    this.code = code;
  }

  public String getMessage() {
    return this.message;
  }

  public String getCode() {
    return code;
  }

  public int getStatus() {
    return status;
  }
}
