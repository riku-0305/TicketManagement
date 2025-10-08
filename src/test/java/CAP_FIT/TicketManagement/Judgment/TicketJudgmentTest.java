package CAP_FIT.TicketManagement.Judgment;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketJudgmentTest {

  private TicketJudgment sut;
  private User user;

  @BeforeEach
  void before() {
    sut = new TicketJudgment();
    user = new User("090-1234-5678");
  }

  @Test
  void 引数で渡されたユーザー情報リストのidにマッチする回数券情報のuserIdがあれば引数で渡された回数券情報を呼び出し元に返す() {
    List<User> userList = new ArrayList<>();
    userList.add(user);

    Tickets tickets = new Tickets("090-1234-5678");

    Tickets actual = sut.insertJudgmentTickets(userList, tickets);

    Assertions.assertEquals(tickets.getUserId(), actual.getUserId());
  }

  @Test
  void 引数で渡されたユーザー情報リストのidにマッチする回数券情報のuserIdがなければ例外メッセージをユーザーに返す() {
    List<User> userList = new ArrayList<>();
    userList.add(user);

    Tickets tickets = new Tickets("090-1234-5689");

    UserNotFoundException actual = Assertions.assertThrows(UserNotFoundException.class, () -> {
      sut.insertJudgmentTickets(userList, tickets);
    });

    Assertions.assertEquals("会員ID " + tickets.getUserId() + " の会員は見つかりません",
        actual.getMessage());
  }
}