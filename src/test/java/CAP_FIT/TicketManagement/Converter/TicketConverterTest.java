package CAP_FIT.TicketManagement.Converter;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
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
  private NominationTicket nominationTicket;
  private StretchTicket stretchTicket;

  @BeforeEach
  void before() {
    sut = new TicketConverter();
    user = new User("090-8694-3710");
    user2 = new User("090-6882-0106");
    nominationTicket = new NominationTicket("090-8694-3710");
    stretchTicket = new StretchTicket("090-8694-3710");
  }

  @Test
  void Dataパッケージの全クラスのリストが正しくidでマッピングされているか() {
    List<User> userList = new ArrayList<>();
    userList.add(user);

    List<NominationTicket> nominationTicketList = new ArrayList<>();
    nominationTicketList.add(nominationTicket);

    List<StretchTicket> stretchTicketList = new ArrayList<>();
    stretchTicketList.add(stretchTicket);

    List<UserInfo> actual = sut.convertUserInfoList(userList,nominationTicketList,stretchTicketList);

    Assertions.assertEquals(1,actual.size());

    Assertions.assertEquals(user.getId(),actual.getFirst().getUser().getId());
    Assertions.assertEquals(nominationTicket.getId(),actual.getFirst().getNominationTicket().getId());
    Assertions.assertEquals(stretchTicket.getId(),actual.getFirst().getStretchTicket().getId());
  }

  @Test
  void 回数券を持っていない会員は会員情報のみ取得できる() {
    List<User> userList = new ArrayList<>();
    userList.add(user2);

    List<NominationTicket> nominationTicketList = new ArrayList<>();
    nominationTicketList.add(new NominationTicket());

    List<StretchTicket> stretchTicketList = new ArrayList<>();
    stretchTicketList.add(new StretchTicket());

    List<UserInfo> actual = sut.convertUserInfoList(userList,nominationTicketList,stretchTicketList);

    Assertions.assertEquals(1,actual.size());

    Assertions.assertEquals(user2.getId(),actual.getFirst().getUser().getId());
    Assertions.assertNull(actual.getFirst().getNominationTicket());
    Assertions.assertNull(actual.getFirst().getStretchTicket());
  }
}