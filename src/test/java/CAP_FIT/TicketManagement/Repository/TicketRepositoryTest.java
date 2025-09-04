package CAP_FIT.TicketManagement.Repository;

import static org.junit.jupiter.api.Assertions.*;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Data.User;
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
    Assertions.assertThat(actual.size()).isEqualTo(2);
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
}