package CAP_FIT.TicketManagement.ExceptionHandler;

import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> hangleUserNotFoundException(Exception ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  @ExceptionHandler(DuplicateKeyException.class)
  public ResponseEntity<String> handleDuplicateKeyException(Exception ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }
}
