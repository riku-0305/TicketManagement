package CAP_FIT.TicketManagement.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException {

  /**
   * ユーザーが見つからなかった際の例外
   * @param message 呼び出し元で指定された例外メッセージを返す
   */
  public UserNotFoundException(String message) {
    super(message);
  }
}
