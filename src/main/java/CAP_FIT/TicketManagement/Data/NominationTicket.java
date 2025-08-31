package CAP_FIT.TicketManagement.Data;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NominationTicket {

  private String id;
  private Integer remaining;
  private Date buyDay;
  private String userName;

}
