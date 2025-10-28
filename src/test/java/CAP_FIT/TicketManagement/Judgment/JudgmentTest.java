package CAP_FIT.TicketManagement.Judgment;

import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JudgmentTest {

  @Mock
  private TicketRepository ticketRepository;

  private Judgment sut;

  @BeforeEach
  void before() {
    sut = new Judgment(ticketRepository);
  }

  @Test
  void 引数で渡されたユーザー情報リストのidにマッチする回数券情報のuserIdがあれば引数で渡された回数券情報を呼び出し元に返す() {
    List<User> userList = new ArrayList<>();
    userList.add(new User("090-1234-5678"));

    Mockito.when(ticketRepository.userList()).thenReturn(userList);

    String insertId = "090-1234-5678";

    boolean actual = sut.insertJudgment(insertId);

    Assertions.assertTrue(actual);
  }

  @Test
  void 引数で渡されたユーザー情報リストのidにマッチする回数券情報のuserIdがなければ例外メッセージをユーザーに返す() {
    List<User> userList = new ArrayList<>();
    userList.add(new User("090-1234-5678"));

    Mockito.when(ticketRepository.userList()).thenReturn(userList);

    String insertId = "090-1234-0000";

    UserNotFoundException actual = Assertions.assertThrows(UserNotFoundException.class, () -> {
      sut.insertJudgment(insertId);
    });

    Assertions.assertEquals("会員ID " + insertId + " の会員は見つかりません",
        actual.getMessage());
  }
}