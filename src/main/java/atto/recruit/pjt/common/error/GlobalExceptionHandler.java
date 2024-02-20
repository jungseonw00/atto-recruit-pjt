package atto.recruit.pjt.common.error;

import static atto.recruit.pjt.common.error.ErrorCode.HANDLE_ACCESS_DENIED;
import static atto.recruit.pjt.common.error.ErrorCode.MEMBER_NOT_FOUND;

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

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(final Exception e) {
        log.error("handleException => {}", e.getMessage());
        final ErrorResponse response = ErrorResponse.of(HANDLE_ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(MEMBER_NOT_FOUND.getStatus()));
    }
}
