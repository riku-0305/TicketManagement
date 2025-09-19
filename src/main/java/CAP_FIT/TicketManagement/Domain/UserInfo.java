package CAP_FIT.TicketManagement.Domain;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {

  @Valid
  private User user;

  @Valid
  private List<Tickets> tickets;

}
