package projekt33.kamkk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import projekt33.kamkk.exception.base.BaseException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidSecretException extends BaseException {
  private static final String ERROR_CODE = "INVALID_SECRET";

  public InvalidSecretException() {
    super(ERROR_CODE, "Invalid Secret");
  }
}
