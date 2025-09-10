package CAP_FIT.TicketManagement.Judgment;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TicketJudgment {

  public NominationTicket insertJudgmentNominationTicket(List<User> userList, NominationTicket nominationTicket) {
    String nominationTicketUserId = nominationTicket.getUserId();
    insertJudgmentTicket(userList, nominationTicketUserId);
    return nominationTicket;
  }

  public StretchTicket insertJudgmentStretchTicket(List<User> userList, StretchTicket stretchTicket) {
    String stretchTicketUserId = stretchTicket.getUserId();
    insertJudgmentTicket(userList, stretchTicketUserId);
    return stretchTicket;
  }

  private void insertJudgmentTicket(List<User> userList, String insertTicketUserId) {
    List<String> userListId = userList.stream()
        .map(User::getId)
        .toList();

    for (String usersId : userListId) {
      if (usersId.equals(insertTicketUserId)) {
        return;
      }
    } throw new UserNotFoundException("会員ID " + insertTicketUserId + " の会員は見つかりません");
  }
}


