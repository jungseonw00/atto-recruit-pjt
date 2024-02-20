package atto.recruit.pjt.common.error.exception;

import atto.recruit.pjt.common.error.ErrorCode;

public class DuplicationException extends RuntimeException {
  private ErrorCode errorCode;

  public DuplicationException(String message,ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

}