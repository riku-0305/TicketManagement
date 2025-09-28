package CAP_FIT.TicketManagement.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @NotBlank(message = "IDの入力は必須です。")
  @Pattern(regexp = "^0[789]0-\\d{4}-\\d{4}$" , message = "携帯番号をハイフン付きで入力してください。")
  private String id;

  @NotBlank(message = "名前の入力は必須です。")
  @Pattern(regexp = "^[ァ-ヶー]+$", message = "カタカナのみ入力できます。")
  @Size(max = 30, message = "30字以内で入力してください")
  private String name;

  @NotBlank(message = "メールアドレスの入力は必須です。")
  @Pattern(regexp = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "無効なメールアドレスです。")
  private String emailAddress;

  @NotNull(message = "金額の入力は必須です。")
  @Min(value = 1, message = "会費は1円以上を入力してください。")
  private Integer membershipFee;

  @NotNull(message = "入会日の入力は必須です。")
  private LocalDate admissionDay;

  public User(String id) {
    this.id = id;
  }
  public User(String id, String name) {
    this.id = id;
    this.name = name;}
}
