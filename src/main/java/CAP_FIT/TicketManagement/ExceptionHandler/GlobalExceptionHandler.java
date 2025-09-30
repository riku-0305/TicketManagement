package CAP_FIT.TicketManagement.ExceptionHandler;

import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  /**
   * ユーザーが見つからなかった際の例外をハンドリング
   * @param ex 呼び出し元の例外メッセージ
   * @return HTTPステータスと例外メッセージ
   */
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> hangleUserNotFoundException(Exception ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  /**
   * ユーザー登録時に既存のユーザーIDが存在した際の例外をハンドリング
   * @param ex 呼び出し元の例外メッセージ
   * @return HTTPステータスと例外メッセージ
   */
  @ExceptionHandler(DuplicateKeyException.class)
  public ResponseEntity<String> handleDuplicateKeyException(Exception ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }

  /**
   * DTOクラスのバリデーションエラーをハンドリング
   * @param ex バリデーションエラーがあったフィールドの情報
   * @return HTTPステータスとエラーがあったフィールド,バリデーションエラーメッセージ
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    for(FieldError error : ex.getBindingResult().getFieldErrors()) {
      String errorFieldPathName = error.getField();

      String errorFieldName = errorFieldPathName.substring(errorFieldPathName.lastIndexOf('.') + 1);

      errors.put(errorFieldName, error.getDefaultMessage());
    }
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
