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
  void 会員リストのIDにマッチする回数券のユーザーIDがあれば回数券を返す() {
    List<User> userList = new ArrayList<>();
    userList.add(user);

    Tickets tickets = new Tickets("090-1234-5678");

    Tickets actual = sut.insertJudgmentTickets(userList, tickets);

    Assertions.assertEquals(tickets.getUserId(), actual.getUserId());
  }

  @Test
  void 会員リストのIDにマッチする回数券のユーザーIDがなければ例外を返す() {
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