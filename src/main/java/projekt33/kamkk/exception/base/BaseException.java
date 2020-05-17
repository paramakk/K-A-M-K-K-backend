package projekt33.kamkk.exception.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@JsonIgnoreProperties({ "suppressed", "stackTrace", "cause" })
public class BaseException extends RuntimeException {
  @Getter
  private String errorCode;

  public BaseException(String errorCode) {
    this(errorCode, null, null);
  }

  public BaseException(String errorCode, String errorMessage) {
    this(errorCode, errorMessage, null);
  }

  public BaseException(String errorCode, Throwable t) {
    this(errorCode, null, t);
  }

  public BaseException(String errorCode, String errorMessage, Throwable t) {
    super(errorMessage, t);
    this.errorCode = errorCode;
  }
}
