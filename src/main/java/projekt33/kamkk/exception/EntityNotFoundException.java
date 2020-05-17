package projekt33.kamkk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import projekt33.kamkk.exception.base.BaseException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends BaseException {
  private static final String ERROR_CODE = "ENTITY_NOTFOUND";

  public EntityNotFoundException(Long id) {
    super(ERROR_CODE, "Entity not found. Id: " + id);
  }

  public EntityNotFoundException(String id) {
    super(ERROR_CODE, "Entity not found. Id: " + id);
  }
}
