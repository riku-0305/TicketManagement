package CAP_FIT.TicketManagement.Judgment;

import CAP_FIT.TicketManagement.Data.NominationTicket;
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
  void 会員リストのIDにマッチする指名回数券のユーザーIDがあれば指名回数券を返す() {
    List<User> userList = new ArrayList<>();
    userList.add(user);

    NominationTicket nominationTicket = new NominationTicket("090-1234-5678");

    NominationTicket actual = sut.insertJudgmentNominationTicket(userList, nominationTicket);

    Assertions.assertEquals(nominationTicket.getUserId(),actual.getUserId());
  }

  @Test
  void 会員リストのIDにマッチする指名回数券のユーザーIDがなければ例外を返す() {
    List<User> userList = new ArrayList<>();
    userList.add(user);

    NominationTicket nominationTicket = new NominationTicket("090-1234-5689");

    UserNotFoundException actual = Assertions.assertThrows(UserNotFoundException.class, () -> {
      sut.insertJudgmentNominationTicket(userList,nominationTicket);
    });

    Assertions.assertEquals("会員ID " + nominationTicket.getUserId() + " の会員は見つかりません", actual.getMessage());
  }
}