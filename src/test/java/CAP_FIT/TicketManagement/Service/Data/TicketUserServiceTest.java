package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.time.LocalDate;
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
class TicketUserServiceTest {

  @Mock
  private TicketRepository ticketRepository;

  private TicketUserService sut;

  @BeforeEach
  void before() {
    sut = new TicketUserService(ticketRepository);
  }

  @Test
  void リポジトリから会員情報の全件取得ができるメソッドを呼び出せる() {
    List<User> userList = new ArrayList<>();

    Mockito.when(ticketRepository.userList()).thenReturn(userList);

    sut.searchUserList();

    Mockito.verify(ticketRepository,Mockito.times(1)).userList();
  }

  @Test
  void リポジトリから名前に紐づく会員を取得できるメソッドを呼び出せる() {
    String name = "テスト";

    List<User> userList = new ArrayList<>();
    userList.add(new User("090-1234-5678","テスト"));

    Mockito.when(ticketRepository.selectUserList(name)).thenReturn(userList);

    List<User> actual = sut.searchSelectUserList(name);

    Mockito.verify(ticketRepository,Mockito.times(1)).selectUserList(name);

    Assertions.assertEquals(actual,userList);
  }

  @Test
  void リポジトリから名前に紐づく会員が取得できなかった際に例外を返す() {
    String name = "テスト";

    Mockito.when(ticketRepository.selectUserList(name)).thenReturn(new ArrayList<>());

    UserNotFoundException actual = Assertions.assertThrows(UserNotFoundException.class, () -> {
      sut.searchSelectUserList(name);
    });

    Assertions.assertEquals(name + "さんは登録されていません", actual.getMessage());
  }


  @Test
  void リポジトリクラスから会員登録メソッドが呼び出せる() {
    User user = new User("090-1234-5678", "テスト","test@gmail.com", 10000, LocalDate.of(2025,9,10));

    Mockito.doNothing().when(ticketRepository).insertUser(user);

    sut.newInsertUser(user);

    Mockito.verify(ticketRepository,Mockito.times(1)).insertUser(user);
  }
}