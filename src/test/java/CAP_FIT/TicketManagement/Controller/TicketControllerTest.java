package CAP_FIT.TicketManagement.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Service.Data.TicketsService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
  private TicketsService ticketsService;

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
  void 回数券登録メソッドをノミネーションチケットサービスから呼び出せる() throws Exception {
    String massage = "回数券の登録が完了しました";
    Tickets tickets = new Tickets(1,"090-1234-5678", 10, LocalDate.of(2025,9,10), "テスト", "指名回数券");

    Mockito.doNothing().when(ticketsService).searchInsertTickets(tickets);

    String jsonRequest = objectMapper.writeValueAsString(tickets);

    mockMvc.perform(post("/newTickets")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string(massage));

    Mockito.verify(ticketsService,Mockito.times(1)).searchInsertTickets(Mockito.any(
        Tickets.class));
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

  @Test
  void 回数券更新メソッドをチケットサービスから呼び出せる() throws Exception {
    String massage = "回数券の更新が完了しました";
    Tickets updateTickets = new Tickets(1,"090-1234-5678",10,LocalDate.of(2025,9,20), "テスト", "指名回数券");

    Mockito.doNothing().when(ticketsService).searchUpdateTickets(updateTickets);

    String jsonRequest = objectMapper.writeValueAsString(updateTickets);

    mockMvc.perform(put("/updateTickets")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
        .andExpect(content().string(massage));

    Mockito.verify(ticketsService,Mockito.times(1)).searchUpdateTickets(Mockito.any(Tickets.class));
  }
}