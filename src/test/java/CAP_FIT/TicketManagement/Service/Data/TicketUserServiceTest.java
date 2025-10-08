package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.Tickets;
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
import org.springframework.dao.DuplicateKeyException;

@ExtendWith(MockitoExtension.class)
class TicketUserServiceTest {

  @Mock
  private TicketRepository ticketRepository;

  private TicketUserService sut;

  @BeforeEach
  void before() {
    sut = new TicketUserService(ticketRepository);
  }

  private User createValidUser() {
    return new User("090-1234-5678", "テストセイコウ", "test@gmail.com", 1, LocalDate.now());
  }

  @Test
  void TicketRepositoryからユーザー情報の全件取得ができるuserListメソッドを呼び出せる() {
    List<User> userList = new ArrayList<>();

    Mockito.when(ticketRepository.userList()).thenReturn(userList);

    sut.searchUserList();

    Mockito.verify(ticketRepository,Mockito.times(1)).userList();
  }

  @Test
  void TicketRepositoryから名前に紐づくユーザー情報を取得できるselectUserListメソッドを呼び出せる() {
    String name = "テストセイコウ";

    List<User> userList = new ArrayList<>();
    userList.add(createValidUser());

    Mockito.when(ticketRepository.selectUserList(name)).thenReturn(userList);

    List<User> actual = sut.searchSelectUserList(name);

    Mockito.verify(ticketRepository,Mockito.times(1)).selectUserList(name);

    Assertions.assertEquals(actual,userList);
  }

  @Test
  void TicketRepositoryから名前に紐づくユーザー情報selectUserListが取得できなかった際に例外を返す() {
    String name = "テスト";

    Mockito.when(ticketRepository.selectUserList(name)).thenReturn(new ArrayList<>());

    UserNotFoundException actual = Assertions.assertThrows(UserNotFoundException.class, () -> {
      sut.searchSelectUserList(name);
    });

    Assertions.assertEquals(name + "さんは登録されていません", actual.getMessage());
  }

  @Test
  void リポジトリクラスから会員登録メソッドが呼び出せる() {
    sut.newInsertUser(createValidUser());

    Mockito.verify(ticketRepository,Mockito.times(1)).insertUser(Mockito.any(User.class));
  }

  @Test
  void ユーザー情報の登録時に既存のユーザーリストのidと重複していれば例外を返す() {
    List<User> userList = new ArrayList<>();
    userList.add(new User("090-1234-5678"));

    User updateUser = new User("090-1234-5678");

    Mockito.when(ticketRepository.userList()).thenReturn(userList);

    DuplicateKeyException actual = Assertions.assertThrows(DuplicateKeyException.class, () -> {
      sut.newInsertUser(updateUser);
    });

    Assertions.assertEquals("会員番号 " + updateUser.getId() + " はすでに存在しています", actual.getMessage());
  }

  @Test
  void TicketRepositoryからユーザー情報の更新ができるupdateUserメソッドが呼び出せる() {
    List<User> userList = new ArrayList<>();
    userList.add(createValidUser());

    User user = new User("090-1234-5678", "テスト1","test@gmail.com", 10000, LocalDate.of(2025,9,10));

    Mockito.when(ticketRepository.userList()).thenReturn(userList);

    sut.searchUpdateUser(user);

    Mockito.verify(ticketRepository, Mockito.times(1)).userList();
    Mockito.verify(ticketRepository, Mockito.times(1)).updateUser(user);
  }

  @Test
  void ユーザー情報の更新時に存在しないIDの場合は例外を返す() {
    List<User> userList = new ArrayList<>();
    userList.add(createValidUser());

    User updateUser = new User("080-1234-5678", "テストセイコウ","test@gmail.com", 10000, LocalDate.of(2025,9,10));

    Mockito.when(ticketRepository.userList()).thenReturn(userList);

    UserNotFoundException actual = Assertions.assertThrows(UserNotFoundException.class, () -> {
      sut.searchUpdateUser(updateUser);
    });

   Assertions.assertEquals("会員番号　" + updateUser.getId() + " は存在しません", actual.getMessage());
  }

  @Test
  void TicketRepositoryからユーザー情報及びそのユーザーに紐づく回数券情報の削除が可能なメソッドをdeleteUserとdeleteTicketsが呼び出せる() {
    User deleteUser = createValidUser();
    Mockito.when(ticketRepository.deleteUser(deleteUser)).thenReturn(1);
    Mockito.doNothing().when(ticketRepository).deleteTickets(deleteUser.getId());

    sut.deleteUserInfo(deleteUser);

    Mockito.verify(ticketRepository,Mockito.times(1)).deleteUser(deleteUser);
    Mockito.verify(ticketRepository,Mockito.times(1)).deleteTickets(deleteUser.getId());
  }

  @Test
  void ユーザー情報の削除時に指定されたユーザーが存在しなければ例外を返す() {
    User deleteUser = createValidUser();

    Mockito.when(ticketRepository.deleteUser(deleteUser)).thenReturn(0);

    UserNotFoundException actual = Assertions.assertThrows(UserNotFoundException.class, () -> {
      sut.deleteUserInfo(deleteUser);
    });

    Assertions.assertEquals("会員番号　" + deleteUser.getId() + " は存在しません。" + "正しいユーザー番号と名前を入力してください。", actual.getMessage());
  }
}