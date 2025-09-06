package CAP_FIT.TicketManagement.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {

  @Pattern(regexp = "^0[789]0-?\\d{4}-?\\d{4}$", message = "ハイフン付きで入力してください")
  private String id;

  @Pattern(regexp = "^[ァ-ヶー]+$", message = "カタカナのみ入力できます")
  @Size(max = 30, message = "30字以内で入力してください")
  private String name;

  @Pattern(regexp = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "無効なメールアドレスです")
  private String emailAddress;

  @Min(value = 1, message = "会費は1円以上を入力してください")
  private Integer membershipFee;

  @NotNull
  private Date admissionDay;

  public User(String id) {
    this.id = id;
  }
  public User(String id, String name) {
    this.id = id;
    this.name = name;}
}
