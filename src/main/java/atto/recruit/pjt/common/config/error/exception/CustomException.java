package atto.recruit.pjt.common.config.error.exception;

import atto.recruit.pjt.common.config.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
	private final ErrorCode errorCode;
}
