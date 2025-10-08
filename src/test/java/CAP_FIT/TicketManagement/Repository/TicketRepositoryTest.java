package CAP_FIT.TicketManagement.Repository;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
class TicketRepositoryTest {

  @Autowired
  private TicketRepository sut;

  @Test
  void  ユーザー情報の全件検索が可能() {
    List<User> actual = sut.userList();
    Assertions.assertThat(actual.size()).isEqualTo(3);
  }

  @Test
  void 回数券情報の全件検索が可能() {
    List<Tickets> actual = sut.ticketsList();
    Assertions.assertThat(actual.size()).isEqualTo(2);
  }

  @Test
  void 名前を指定したユーザー情報の検索が可能() {
    String name = "テスト";
    List<User> actual = sut.selectUserList(name);

    Assertions.assertThat(actual.size()).isEqualTo(1);
    Assertions.assertThat(actual.getFirst().getName()).isEqualTo(name);
  }

  @Test
  void ユーザー情報の登録が可能() {
    User user = new User("070-1234-5678","テスト4","test4@gmail.com", 10000, LocalDate.of(2025,9,10));

    sut.insertUser(user);

    List<User> actual = sut.userList();

    Assertions.assertThat(actual.size()).isEqualTo(4);
  }

  @Test
  void 回数券情報の登録が可能() {
    Tickets tickets = new Tickets(1,"090-1234-1234", 10, LocalDate.of(2025,9,1), "テスト3", "指名回数券");

    sut.insertTickets(tickets);

    List<Tickets> actual = sut.ticketsList();

    Assertions.assertThat(actual.size()).isEqualTo(3);
  }

  @Test
  void ユーザー情報の更新が可能() {
    User updateUser = new User("090-1234-5678","テストさん","testsan@gmail.com", 15000, LocalDate.of(2025,9,30));

    List<User> oldUserList = sut.userList();

    sut.updateUser(updateUser);

    List<User> newUserList = sut.userList();

    Assertions.assertThat(oldUserList.size()).isEqualTo(newUserList.size());
    Assertions.assertThat(oldUserList.getFirst().getId()).isEqualTo(newUserList.getFirst().getId());
    Assertions.assertThat(15000).isEqualTo(newUserList.getFirst().getMembershipFee());
  }

  @Test
  void 回数券情報の更新が可能() {
    Tickets updateTickets = new Tickets(1, "090-1234-5678", 9, LocalDate.of(2025,9,1), "テスト", "指名回数券");

    List<Tickets> oldTicktsList = sut.ticketsList();

    sut.updateTickets(updateTickets);

    List<Tickets> newTickets = sut.ticketsList();

    Assertions.assertThat(oldTicktsList.size()).isEqualTo(newTickets.size());
    Assertions.assertThat(oldTicktsList.getFirst().getTicketNumber()).isEqualTo(newTickets.getFirst().getTicketNumber());
    Assertions.assertThat(9).isEqualTo(newTickets.getFirst().getRemaining());
  }

  @Test
  void ユーザー情報とそのユーザーに紐づく回数券情報をまとめて削除が可能() {
    User deleteUser = new User("090-1234-5678","テスト","testsan@gmail.com", 15000, LocalDate.of(2025,9,30));

    sut.deleteTickets(deleteUser.getId());
    sut.deleteUser(deleteUser);

    List<Tickets> ticketsList = sut.ticketsList();
    List<User> userList = sut.userList();

    Assertions.assertThat(ticketsList.size()).isEqualTo(1);
    Assertions.assertThat(userList.size()).isEqualTo(2);
  }
}