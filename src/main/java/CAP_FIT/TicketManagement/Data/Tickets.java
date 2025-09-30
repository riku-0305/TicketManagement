package CAP_FIT.TicketManagement.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Tickets {

  //回数券番号
  @Min(value = 1, message = "1以上の数字を入力してください。")
  private Integer ticketNumber;

  //回数券を持つユーザーID
  @NotBlank(message = "ユーザーIDの入力は必須です。")
  @Pattern(regexp = "^0[789]0-\\d{4}-\\d{4}$", message = "携帯番号をハイフン付きで入力してください。")
  private String userId;

  //回数券の残回数
  @Min(value = 0, message = "0以上30以下の数字を入力してください。")
  @Max(value = 30, message = "0以上30以下の数字を入力してください。")
  @NotNull(message = "残回数の入力は必須です。")
  private Integer remaining;

  //回数券の購入日
  @NotNull(message = "購入日の入力は必須です。")
  private LocalDate buyDay;

  //回数券を持つユーザー名
  @NotBlank(message = "ユーザー名の入力は必須です。")
  @Pattern(regexp = "^[ァ-ヶー]+$", message = "カタカナのみ入力できます。")
  private String userName;

  //回数券の名前
  @NotBlank(message = "回数券名の入力は必須です。")
  @Size(max = 30, message = "30字以内で入力してください。")
  private String ticketName;

  public Tickets(String userId) {
    this.userId = userId;
  }
  public Tickets(Integer ticketNumber,  String userId, Integer remaining, LocalDate buyDay, String userName, String ticketName) {
    this.ticketNumber = ticketNumber;
    this.userId = userId;
    this.remaining = remaining;
    this.buyDay = buyDay;
    this.userName = userName;
    this.ticketName = ticketName;
  }
}
