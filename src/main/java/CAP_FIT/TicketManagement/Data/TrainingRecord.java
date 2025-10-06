package CAP_FIT.TicketManagement.Data;

import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TrainingRecord {

  private int recordId;

  private String userId;

  private LocalDate trainingMemory;

  private String trainingMemo;

  private String userName;

}
