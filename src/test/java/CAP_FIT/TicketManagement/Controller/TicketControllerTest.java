package CAP_FIT.TicketManagement.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.TrainingRecord;
import CAP_FIT.TicketManagement.Service.Data.RecordService;
import CAP_FIT.TicketManagement.Service.Data.TicketsService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import jakarta.validation.Validator;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Service.ConverterService;
import CAP_FIT.TicketManagement.Service.Data.TicketUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TicketController.class)
class TicketControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private ConverterService converterService;

  @MockitoBean
  private TicketUserService ticketUserService;

  @MockitoBean
  private TicketsService ticketsService;

  @MockitoBean
  private RecordService recordService;

  private static ValidatorFactory factory;
  private static Validator validator;

  @BeforeAll
  static void setUpValidator() {
    factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @AfterAll
  static void down() {
    if (factory != null) {
      factory.close();
    }
  }

  /**
   * @return バリデーションエラーにならないユーザー情報
   */
  private User createValidUser() {
    return new User("090-1234-5678", "テストセイコウ", "test@gmail.com", 1, LocalDate.now());
  }

  /**
   * @return バリデーションエラーにならない回数券情報
   */
  private Tickets createValidTickets() {
    return new Tickets(1, "090-1234-5678", 1, LocalDate.now(), "テスト", "テスト回数券");
  }

  /**
   * @return バリデーションエラーにならないカルテ情報
   */
  private TrainingRecord createValidTrainingRecord() {
    return new TrainingRecord(1, "090-1234-5678", LocalDate.now(), "テスト", "テスト");
  }

  @Test
  void ユーザー情報とそのユーザーに紐づく回数券情報を持つuserInfoListメソッドをコンバーターサービスから呼び出しリスト型のUserInfoオブジェクトをjson形式でユーザーに返す()
      throws Exception {
    mockMvc.perform(get("/userList"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    Mockito.verify(converterService, Mockito.times(1)).userInfoList();
  }

  @Test
  void 名前で検索されたユーザー情報とそのユーザーに紐づく回数券情報を持つselectUserInfoメソッドをコンバーターサービスから呼び出しリスト型のUserInfoオブジェクトをjson形式でユーザーに返す()
      throws Exception {
    mockMvc.perform(get("/userList")
            .param("name", "テスト"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    Mockito.verify(converterService, Mockito.times(1)).selectUserInfo("テスト");
  }

  @Test
  void ユーザーidで検索されたユーザーのカルテ情報を持つselectRecordListメソッドをRecordServiceから呼び出してリスト型のTrainingRecordオブジェクトをjson形式でユーザーに返す()
      throws Exception {
    mockMvc.perform(get("/selectUserTrainingRecord")
        .param("userId", "090-1234-5678"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    Mockito.verify(recordService, Mockito.times(1)).selectRecordList("090-1234-5678");
  }

  @Test
  void 会員登録ができるsearchInsertUserメソッドをTicketUserServiceから呼び出しUserオブジェクトをjson形式でリクエストを受け取りユーザーに登録完了のメッセージを返す()
      throws Exception {
    Mockito.doNothing().when(ticketUserService).searchInsertUser(createValidUser());

    mockMvc.perform(post("/newUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createValidUser())))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string("ユーザー情報の登録が完了しました"));

    Mockito.verify(ticketUserService, Mockito.times(1)).searchInsertUser(Mockito.any(User.class));
  }

  @Test
  void 回数券登録ができるsearchInsertTicketsメソッドをTicketsServiceから呼び出しTicketsオブジェクトjson形式でリクエストを受け取りユーザーに登録完了のメッセージを返す()
      throws Exception {
    Mockito.doNothing().when(ticketsService).searchInsertTickets(createValidTickets());

    mockMvc.perform(post("/newTickets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createValidTickets())))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string("回数券情報の登録が完了しました"));

    Mockito.verify(ticketsService, Mockito.times(1)).searchInsertTickets(Mockito.any(
        Tickets.class));
  }

  @Test
  void カルテの登録ができるsearchInsertRecordメソッドをRecordServiceから呼び出しTrainingRecordオブジェクトをjson形式でリクエストを受け取りユーザーに作成完了のメッセージを返す()
      throws Exception {
    Mockito.doNothing().when(recordService).searchInsertRecord(createValidTrainingRecord());

    mockMvc.perform(post("/userRecord")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createValidTrainingRecord())))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string("新しいカルテ情報が作成されました"));

    Mockito.verify(recordService, Mockito.times(1))
        .searchInsertRecord(Mockito.any(TrainingRecord.class));
  }

  @Test
  void ユーザー情報の更新が可能なメソッドsearchUpdateUserをTicketUserServiceから呼び出だしUserオブジェクトをjson形式でリクエストを受け取りユーザーに更新完了のメッセージを返す()
      throws Exception {
    Mockito.doNothing().when(ticketUserService).searchUpdateUser(createValidUser());

    mockMvc.perform(put("/updateUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createValidUser())))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string("ユーザー情報の更新が完了しました"));

    Mockito.verify(ticketUserService, Mockito.times(1)).searchUpdateUser(Mockito.any(User.class));
  }

  @Test
  void 回数券更新の更新が可能なメソッドsearchUpdateTicketsをTicketsServiceから呼び出しTicketsオブジェクトをjson形式でリクエストを受け取りユーザーに更新完了のメッセージを返す()
      throws Exception {
    Mockito.doNothing().when(ticketsService).searchUpdateTickets(createValidTickets());

    mockMvc.perform(put("/updateTickets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createValidTickets())))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string("回数券情報の更新が完了しました"));

    Mockito.verify(ticketsService, Mockito.times(1))
        .searchUpdateTickets(Mockito.any(Tickets.class));
  }

  @Test
  void 会員情報の一括削除が可能なメソッドdeleteUserInfoをTicketUserServiceから呼び出だしUserオブジェクトをjson形式でリクエストを受け取りユーザーに削除完了のメッセージを返す() throws Exception {
    Mockito.doNothing().when(ticketUserService).deleteUserInfo(createValidUser());

    mockMvc.perform(delete("/deleteUserInfo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createValidUser())))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string("ユーザー情報の削除が完了しました"));

    Mockito.verify(ticketUserService, Mockito.times(1)).deleteUserInfo(Mockito.any(User.class));
  }

  /**
   * 実際に発生したバリデーションエラーと期待するバリデーションエラーメッセージのサイズを検証
   * violationsからバリデーションエラーメッセージのみを取り出し、順番を無視して期待されるバリデーションエラーメッセージと中身を検証
   * @param violations 発生した全てのバリデーションエラー
   * @param expectedErrorMassage 期待されるバリデーションエラーメッセージリスト
   * @param <T> 任意のDTOクラスの型
   */
  private static <T> void assertViolationMessage(Set<ConstraintViolation<T>> violations,
      List<String> expectedErrorMassage) {
    assertThat(violations)
        .hasSize(expectedErrorMassage.size())
        .extracting(ConstraintViolation::getMessage)
        .containsExactlyInAnyOrderElementsOf(expectedErrorMassage);
  }

  /**
   * Userフィールドの各フィールド、バリデーションエラーが発生する値、バリデーションエラーメッセージを設定
   * @return フィールド名、バリデーションエラーが発生する値、バリデーションエラーメッセージリスト
   */
  static Stream<Arguments> invalidUserDate() {
    return Stream.of(

        // 1. id (携帯番号) のエラーパターン
        Arguments.of("id", null, List.of("IDの入力は必須です。")),
        Arguments.of("id", "09012345678", List.of("携帯番号をハイフン付きで入力してください。")),

        // 2. name (名前) のエラーパターン
        Arguments.of("name", null, List.of("名前の入力は必須です。")),
        Arguments.of("name", "てすと", List.of("カタカナのみ入力できます。")),
        Arguments.of("name", "ア".repeat(31), List.of("30字以内で入力してください")),

        // 3. emailAddress (メールアドレス)　のエラーパターン
        Arguments.of("emailAddress", null, List.of("メールアドレスの入力は必須です。")),
        Arguments.of("emailAddress", "test.com", List.of("無効なメールアドレスです。")),

        // 4. membershipFee (会費) のエラーパターン
        Arguments.of("membershipFee", null, List.of("金額の入力は必須です。")),
        Arguments.of("membershipFee", 0, List.of("会費は1円以上を入力してください。")),

        // 5. admissionDay (入会日) のエラーパターン
        Arguments.of("admissionDay", null, List.of("入会日の入力は必須です。"))
    );
  }

  /**
   * ユーザークラスの各フィールドにエラーが発生しない値(createValidUser())をincompleteUserに代入
   * 条件分岐で指定したフィールドのみバリデーションエラーが発生するようにincompleteUserに値をセット
   * これにより全てのフィールドのバリデーションエラーを1回ずつ検証が可能
   * @param targetField Userクラスのフィールド
   * @param inputValue バリデーションエラーが発生する値(バリデーションエラーの型が複数存在するためObject型を採用)
   * @param expectedErrorMassage 期待されるバリデーションエラーのメッセージリスト
   */
  @ParameterizedTest
  @MethodSource("invalidUserDate")
  void Userクラスのバリデーションのエラーを全て返す(String targetField, Object inputValue,
      List<String> expectedErrorMassage) {
    User incompleteUser = createValidUser();

    if ("id".equals(targetField)) {
      incompleteUser.setId((String) inputValue);
    } else if ("name".equals(targetField)) {
      incompleteUser.setName((String) inputValue);
    } else if ("emailAddress".equals(targetField)) {
      incompleteUser.setEmailAddress((String) inputValue);
    } else if ("membershipFee".equals(targetField)) {
      incompleteUser.setMembershipFee((Integer) inputValue);
    } else if ("admissionDay".equals(targetField)) {
      incompleteUser.setAdmissionDay((LocalDate) inputValue);
    }
    Set<ConstraintViolation<User>> violations = validator.validate(incompleteUser);
    assertViolationMessage(violations, expectedErrorMassage);
  }

  /**
   * Ticketsフィールドの各フィールド、バリデーションエラーが発生する値、バリデーションエラーメッセージを設定
   * @return フィールド名、バリデーションエラーが発生する値、バリデーションエラーメッセージリスト
   */
  static Stream<Arguments> invalidTicketsDate() {
    return Stream.of(

        // 1. ticketNumber (回数券番号) のエラーパターン
        Arguments.of("ticketNumber", 0, List.of("1以上の数字を入力してください。")),

        // 2. userId (携帯番号) のエラーパターン
        Arguments.of("userId", null, List.of("ユーザーIDの入力は必須です。")),
        Arguments.of("userId", "abc", List.of("携帯番号をハイフン付きで入力してください。")),

        // 3. remaining (残回数) のエラーパターン
        Arguments.of("remaining", null, List.of("残回数の入力は必須です。")),
        Arguments.of("remaining", -1, List.of("0以上30以下の数字を入力してください。")),
        Arguments.of("remaining", 31, List.of("0以上30以下の数字を入力してください。")),

        // 4. buyDay (購入日) のエラーパターン
        Arguments.of("buyDay", null, List.of("購入日の入力は必須です。")),

        // 5. userName (ユーザー名) のエラーパターン
        Arguments.of("userName", null, List.of("ユーザー名の入力は必須です。")),
        Arguments.of("userName", "てすと", List.of("カタカナのみ入力できます。")),

        // 6. ticketName (回数券名) のエラーパターン
        Arguments.of("ticketName", null, List.of("回数券名の入力は必須です。")),
        Arguments.of("ticketName", "ア".repeat(31), List.of("30字以内で入力してください。"))
    );
  }

  /**
   * チケットクラスの各フィールドにエラーが発生しない値(createValidTickets())をincompleteTicketsに代入
   * 条件分岐で指定したフィールドのみバリデーションエラーが発生するようにincompleteTicketsに値をセット
   * これにより全てのフィールドのバリデーションエラーを1回ずつ検証が可能
   * @param targetField Ticketsクラスのフィールド
   * @param inputValue バリデーションエラーが発生する値
   * @param expectedErrorMassage 期待されるバリデーションエラーのメッセージリスト
   */
  @ParameterizedTest
  @MethodSource("invalidTicketsDate")
  void Ticketsクラスのバリデーションのエラーを全て返す(String targetField, Object inputValue,
      List<String> expectedErrorMassage) {
    Tickets incompleteTickets = createValidTickets();

    if ("ticketNumber".equals(targetField)) {
      incompleteTickets.setTicketNumber((Integer) inputValue);
    } else if ("userId".equals(targetField)) {
      incompleteTickets.setUserId((String) inputValue);
    } else if ("remaining".equals(targetField)) {
      incompleteTickets.setRemaining((Integer) inputValue);
    } else if ("buyDay".equals(targetField)) {
      incompleteTickets.setBuyDay((LocalDate) inputValue);
    } else if ("userName".equals(targetField)) {
      incompleteTickets.setUserName((String) inputValue);
    } else if ("ticketName".equals(targetField)) {
      incompleteTickets.setTicketName((String) inputValue);
    }

    Set<ConstraintViolation<Tickets>> violations = validator.validate(incompleteTickets);
    assertViolationMessage(violations, expectedErrorMassage);
  }
}