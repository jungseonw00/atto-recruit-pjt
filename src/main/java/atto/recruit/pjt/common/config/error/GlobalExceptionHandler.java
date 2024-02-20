package atto.recruit.pjt.common.config.error;

import static atto.recruit.pjt.common.config.error.ErrorCode.HANDLE_ACCESS_DENIED;
import static atto.recruit.pjt.common.config.error.ErrorCode.MEMBER_NOT_FOUND;
import static atto.recruit.pjt.common.config.error.ErrorCode.SERVER_ERROR;

import atto.recruit.pjt.common.config.error.exception.CustomException;
import java.nio.file.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException => {}", e.getMessage());
        final ErrorResponse response = ErrorResponse.of(HANDLE_ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(HANDLE_ACCESS_DENIED.getStatus()));
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        log.error("CustomException => {}", e.getErrorCode().getMessage());
        final ErrorResponse response = ErrorResponse.of(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("handleException => {}", e.getMessage());
        final ErrorResponse response = ErrorResponse.of(SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(MEMBER_NOT_FOUND.getStatus()));
    }
}
