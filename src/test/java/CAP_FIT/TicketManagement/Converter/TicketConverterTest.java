package CAP_FIT.TicketManagement.Converter;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Domain.UserInfo;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketConverterTest {

  private TicketConverter sut;
  private User user;
  private User user2;
  private Tickets tickets;

  @BeforeEach
  void before() {
    sut = new TicketConverter();
    user = new User("090-8694-3710");
    user2 = new User("090-6882-0106");
    tickets = new Tickets("090-8694-3710");
  }

  @Test
  void Dataパッケージの全クラスのリストが正しくidでマッピングされているか() {
    List<User> userList = new ArrayList<>();
    userList.add(user);

    List<Tickets> ticketsList = new ArrayList<>();
    ticketsList.add(tickets);

    List<UserInfo> actual = sut.convertUserInfoList(userList, ticketsList);

    Assertions.assertEquals(1,actual.size());

    Assertions.assertEquals(user.getId(),actual.getFirst().getUser().getId());
    Assertions.assertEquals(tickets.getUserId(),actual.getFirst().getTickets().getFirst().getUserId());
  }

  @Test
  void 回数券を持っていない会員は会員情報のみ取得できる() {
    List<User> userList = new ArrayList<>();
    userList.add(user2);

    List<Tickets> ticketsList = new ArrayList<>();
    ticketsList.add(new Tickets());

    List<UserInfo> actual = sut.convertUserInfoList(userList, ticketsList);

    Assertions.assertEquals(1,actual.size());

    Assertions.assertEquals(user2.getId(),actual.getFirst().getUser().getId());
    Assertions.assertTrue(actual.getFirst().getTickets().isEmpty());
  }
}