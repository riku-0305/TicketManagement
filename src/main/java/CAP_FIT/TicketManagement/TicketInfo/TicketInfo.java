package CAP_FIT.TicketManagement.TicketInfo;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.User;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class TicketInfo {

 private User user;
 private List<NominationTicket> nominationTicketCours;

 @Autowired
 public TicketInfo(User user, List<NominationTicket> nominationTicket) {
  this.user = user;
  this.nominationTicketCours = nominationTicket;

 }


}
