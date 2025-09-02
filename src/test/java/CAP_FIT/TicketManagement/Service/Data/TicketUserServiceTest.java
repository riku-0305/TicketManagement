package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.ArrayList;
import java.util.List;
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
  void リポジトリから会員情報の全件取得が可能() {
    List<User> userList = new ArrayList<>();

    Mockito.when(ticketRepository.userList()).thenReturn(userList);

    sut.searchUserList();

    Mockito.verify(ticketRepository,Mockito.times(1)).userList();
  }
}