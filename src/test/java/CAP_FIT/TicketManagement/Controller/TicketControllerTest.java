package CAP_FIT.TicketManagement.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Service.Data.NominationTicketService;
import CAP_FIT.TicketManagement.Service.Data.StretchTicketService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.MediaType;

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
  private NominationTicketService nominationTicketService;

  @MockitoBean
  private StretchTicketService stretchTicketService;

  @Test
  void 会員と回数券の情報全てをコンバーターサービスから呼び出せる() throws Exception {
    mockMvc.perform(get("/userList"))
        .andExpect(status().isOk());

    Mockito.verify(converterService, Mockito.times(1)).userInfoList();
  }

  @Test
  void 名前に紐づく会員と回数券の情報がコンバーターサービスから呼び出せる() throws Exception {
    String name = "テスト";

    mockMvc.perform(get("/userList")
            .param("name", "テスト"))
        .andExpect(status().isOk());

    Mockito.verify(converterService, Mockito.times(1)).selectUserInfo(name);
  }

  @Test
  void 会員登録メソッドをチケットユーザーサービスから呼び出せる() throws Exception {
    String massage = "会員登録が完了しました";
    User user = new User("090-1234-5678", "テスト", "test@gmail.com", 1000,
        LocalDate.of(2025, 9, 10));

    Mockito.doNothing().when(ticketUserService).newInsertUser(user);

    String jsonRequest = objectMapper.writeValueAsString(user);

    mockMvc.perform(post("/newUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string(massage));

    Mockito.verify(ticketUserService, Mockito.times(1)).newInsertUser(Mockito.any(User.class));
  }

  @Test
  void 指名回数券登録メソッドをノミネーションチケットサービスから呼び出せる() throws Exception {
    String massage = "指名回数券の登録が完了しました";
    NominationTicket nominationTicket = new NominationTicket("090-1234-5678", 10, LocalDate.of(2025,9,10), "テスト");

    Mockito.doNothing().when(nominationTicketService).newInsertNominationTicket(nominationTicket);

    String jsonRequest = objectMapper.writeValueAsString(nominationTicket);

    mockMvc.perform(post("/newNominationTicket")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string(massage));

    Mockito.verify(nominationTicketService,Mockito.times(1)).newInsertNominationTicket(Mockito.any(NominationTicket.class));
  }

  @Test
  void ストレッチ回数券登録メソッドをストレッチチケットサービスから呼び出せる() throws Exception {
    String massage = "ストレッチ回数券の登録が完了しました";

    StretchTicket stretchTicket = new StretchTicket("090-1234-5678", 10, LocalDate.of(2025,9,10), "テスト");

    Mockito.doNothing().when(stretchTicketService).newInsertStretchTicket(stretchTicket);

    String jsonRequest = objectMapper.writeValueAsString(stretchTicket);

    mockMvc.perform(post("/newStretchTicket")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string(massage));

    Mockito.verify(stretchTicketService,Mockito.times(1)).newInsertStretchTicket(Mockito.any(StretchTicket.class));
  }

  @Test
  void 会員情報更新メソッドをチケットユーザーサービスから呼び出せる() throws Exception {
    String massage = "会員情報の更新が完了しました";

    User user = new User("090-1234-5678", "テスト", "test@gmail.com", 10000,
        LocalDate.of(2025, 9, 10));

    Mockito.doNothing().when(ticketUserService).searchUpdateUser(user);

    String jsonRequest = objectMapper.writeValueAsString(user);

    mockMvc.perform(put("/updateUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string(massage));

    Mockito.verify(ticketUserService, Mockito.times(1)).searchUpdateUser(Mockito.any(User.class));
  }
}