package CAP_FIT.TicketManagement.Data;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  private String id;
  private String name;
  private String emailAddress;
  private Integer membershipFee;
  private Date admissionDay;

  public User(String id) {
    this.id = id;
  }
}
