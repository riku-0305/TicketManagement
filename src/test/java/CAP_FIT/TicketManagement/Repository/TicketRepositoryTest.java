package CAP_FIT.TicketManagement.Repository;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Data.User;
import java.time.LocalDate;
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
  void 会員情報の全件検索が可能() {
    List<User> actual = sut.userList();
    Assertions.assertThat(actual.size()).isEqualTo(3);
  }

  @Test
  void 指名回数券の全件検索が可能() {
    List<NominationTicket> actual = sut.nominationTicketList();
    Assertions.assertThat(actual.size()).isEqualTo(2);
  }

  @Test
  void ストレッチ回数券の全件検索が可能() {
    List<StretchTicket> actual = sut.stretchTicketList();
    Assertions.assertThat(actual.size()).isEqualTo(2);
  }

  @Test
  void 名前を指定した会員の検索が可能() {
    String name = "テスト";
    List<User> actual = sut.selectUserList(name);

    Assertions.assertThat(actual.size()).isEqualTo(1);
    Assertions.assertThat(actual.getFirst().getName()).isEqualTo(name);
  }

  @Test
  void 会員の登録が可能() {
    User user = new User("070-1234-5678","テスト3","test3@gmail.com", 10000, LocalDate.of(2025,9,10));

    sut.insertUser(user);

    List<User> actual = sut.userList();

    Assertions.assertThat(actual.size()).isEqualTo(4);
  }

  @Test
  void 指名回数券の登録が可能() {
    NominationTicket nominationTicket = new NominationTicket("090-1234-1234", 10, LocalDate.of(2025,9,1), "テスト3");

    sut.insertNominationTicket(nominationTicket);

    List<NominationTicket> actual = sut.nominationTicketList();

    Assertions.assertThat(actual.size()).isEqualTo(3);
  }

  @Test
  void ストレッチ回数券の登録が可能() {
    StretchTicket stretchTicket = new StretchTicket("090-1234-1234",10, LocalDate.of(2025,9,1), "テスト3");

    sut.insertStretchTicket(stretchTicket);

    List<StretchTicket> actual = sut.stretchTicketList();

    Assertions.assertThat(actual.size()).isEqualTo(3);
  }

  @Test
  void 会員情報の更新が可能() {
    User updateUser = new User("090-1234-5678","テストさん","testsan@gmail.com", 15000, LocalDate.of(2025,9,30));

    List<User> oldUserList = sut.userList();

    sut.updateUser(updateUser);

    List<User> newUserList = sut.userList();

    Assertions.assertThat(oldUserList.size()).isEqualTo(newUserList.size());
    Assertions.assertThat(oldUserList.getFirst().getId()).isEqualTo(newUserList.getFirst().getId());
    Assertions.assertThat("テストさん").isEqualTo(newUserList.getFirst().getName());
  }
}