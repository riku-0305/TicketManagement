package CAP_FIT.TicketManagement.Judgment;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TicketJudgment {

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

