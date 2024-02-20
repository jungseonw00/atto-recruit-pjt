package atto.recruit.pjt.common.config.error;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class ErrorResponse {
    private int status;
    private String code;
    private String message;
    private String timestamp;

    public ErrorResponse(int status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now().toString();
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatus(), errorCode.getMessage(), errorCode.getCode());
    }
}
