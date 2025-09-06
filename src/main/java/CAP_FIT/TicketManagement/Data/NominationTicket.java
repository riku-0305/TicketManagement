package CAP_FIT.TicketManagement.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NominationTicket {

  @Min(value = 1, message = "チケットidは１以上を入力してください")
  private int  ticketNumber;

  @Pattern(regexp = "^0[789]0-?\\d{4}-?\\d{4}$", message = "ハイフン付きで入力してください")
  private String userId;

  @Min(value = 0, message = "0以上10以下の数字を入力してください")
  @Max(value = 10, message = "0以上10以下の数字を入力してください")
  private Integer remaining;

  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "日付はyyyy-mm-ddの形式で入力してください")
  private LocalDate buyDay;

  @Pattern(regexp = "^[ァ-ヶー]+$", message = "カタカナのみ入力できます")
  private String userName;

  public NominationTicket(String userId) {
    this.userId = userId;
  }
}
