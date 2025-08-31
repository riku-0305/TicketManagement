package CAP_FIT.TicketManagement.Domain;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Data.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {

  private User user;
  private NominationTicket nominationTicket;
  private StretchTicket stretchTicket;

}
