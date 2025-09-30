package CAP_FIT.TicketManagement.Judgment;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TicketJudgment {

  /**
   * 全件のユーザーリストの中のユーザーIDに該当する回数券情報であれば回数券情報を呼び出し元に返し、無ければ例外メッセージを返す
   * @param userList 全件のユーザー情報
   * @param tickets １件の回数券情報
   * @return 引数で渡された回数券情報
   */
  public Tickets insertJudgmentTickets(List<User> userList, Tickets tickets) {
    String ticketUserId = tickets.getUserId();

    boolean userExists = userList.stream()
        .anyMatch(user -> user.getId().equals(ticketUserId));

    if (userExists) {
      return tickets;
    } else {
      throw new UserNotFoundException("会員ID " + ticketUserId + " の会員は見つかりません");
    }
  }
}

