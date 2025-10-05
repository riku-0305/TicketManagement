package CAP_FIT.TicketManagement.Data;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class TrainingRecord {

  private int recordId;

  private String userId;

  private Date trainingMemory;

  private String trainingMemo;

  private String userName;

}
