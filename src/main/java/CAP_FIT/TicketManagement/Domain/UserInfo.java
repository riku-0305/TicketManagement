package CAP_FIT.TicketManagement.Domain;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Data.User;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {

  @Valid
  private User user;

  @Valid
  private NominationTicket nominationTicket;

  @Valid
  private StretchTicket stretchTicket;

}
