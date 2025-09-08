package CAP_FIT.TicketManagement.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StretchTicket {

  private int ticketNumber;

  @Pattern(regexp = "^0[789]0-?\\d{4}-?\\d{4}$", message = "ハイフン付きで入力してください")
  private String userId;

  @Min(value = 0, message = "0以上10以下の数字を入力してください")
  @Max(value = 10, message = "0以上10以下の数字を入力してください")
  private Integer remaining;

  @NotNull
  private LocalDate buyDay;

  @Pattern(regexp = "^[ァ-ヶー]+$", message = "カタカナのみ入力できます")
  private String userName;

  public StretchTicket(String userId) {
    this.userId = userId;
  }
}
