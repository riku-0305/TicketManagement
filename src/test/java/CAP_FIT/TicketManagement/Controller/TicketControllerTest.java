package CAP_FIT.TicketManagement.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import CAP_FIT.TicketManagement.Service.ConverterService;
import java.util.ArrayList;
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

  @MockitoBean
  private ConverterService converterService;

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
            .param("name","テスト"))
        .andExpect(status().isOk());

    Mockito.verify(converterService, Mockito.times(1)).selectUserInfo(name);
  }
}