package CAP_FIT.TicketManagement.Judgment;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TicketJudgment {

  public NominationTicket insertJudgmentNominationTicket(List<User> userList, NominationTicket nominationTicket) {
    List<String> userListId = userList.stream()
        .map(User::getId)
        .toList();

    String nominationTicketUserId = nominationTicket.getUserId();

    for (String usersId : userListId) {
      if (usersId.equals(nominationTicketUserId)) {
        return nominationTicket;
      }
    } throw new UserNotFoundException("会員ID " + nominationTicketUserId + " の会員は見つかりません");
  }
}


