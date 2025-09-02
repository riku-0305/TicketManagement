package CAP_FIT.TicketManagement.Data;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NominationTicket {

  private String id;
  private Integer remaining;
  private Date buyDay;
  private String userName;

  public NominationTicket(String id) {
    this.id = id;
  }
}
