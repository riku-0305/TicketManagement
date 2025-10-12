package CAP_FIT.TicketManagement.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

  @Min(value = 1, message = "1以上の整数値を入力してください")
  private int recordId;

  @NotBlank(message = "ユーザーidの入力は必須です。")
  @Pattern(regexp = "^0[789]0-\\d{4}-\\d{4}$", message = "携帯番号をハイフン付きで入力してください。")
  private String userId;

  @NotNull(message = "購入日の入力は必須です。")
  private LocalDate trainingMemory;

  private String trainingMemo;

  @NotBlank(message = "ユーザー名の入力は必須です。")
  @Pattern(regexp = "^[ァ-ヶー]+$", message = "カタカナのみ入力できます。")
  private String userName;
}
