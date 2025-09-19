package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Judgment.TicketJudgment;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TicketsServiceTest {

  @Mock
  private TicketRepository ticketRepository;

  @Mock
  private TicketJudgment ticketJudgment;

  private TicketsService sut;

  @BeforeEach
  void before() {
    sut = new TicketsService(ticketRepository,ticketJudgment);
  }

  @Test
  void リポジトリから会員の回数券の全件取得が可能なメソッドを呼び出せる() {
    List<Tickets> ticketsList = new ArrayList<>();

    Mockito.when(ticketRepository.ticketsList()).thenReturn(ticketsList);

    sut.searchTicketsList();

    Mockito.verify(ticketRepository,Mockito.times(1)).ticketsList();
  }

  @Test
  void リポジトリから全件の会員情報と回数券を追加するメソッドを呼び出しチケットジャッジメントから回数券の登録を判定するメソッドを呼び出せる() {
    List<User> userList = new ArrayList<>();
    userList.add(new User("090-1234-5678"));

    Tickets tickets = new Tickets(1,"090-1234-5678", 10, LocalDate.of(2025,9,10), "テスト", "指名回数券");

    Mockito.when(ticketRepository.userList()).thenReturn(userList);
    Mockito.when(ticketJudgment.insertJudgmentTickets(userList, tickets)).thenReturn(
        tickets);

    sut.searchInsertTickets(tickets);

    Mockito.verify(ticketRepository,Mockito.times(1)).userList();
    Mockito.verify(ticketJudgment,Mockito.times(1)).insertJudgmentTickets(userList, tickets);
    Mockito.verify(ticketRepository,Mockito.times(1)).insertTickets(Mockito.any(Tickets.class));
  }
}