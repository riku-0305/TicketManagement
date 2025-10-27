package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import CAP_FIT.TicketManagement.Judgment.Judgment;
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
class TicketsServiceTest {

  @Mock
  private TicketRepository ticketRepository;

  @Mock
  private Judgment judgment;

  private TicketsService sut;

  @BeforeEach
  void before() {
    sut = new TicketsService(ticketRepository, judgment);
  }

  private Tickets createValidTickets() {
    return new Tickets(1, "090-1234-5678", 1, LocalDate.now(), "テスト", "テスト回数券");
  }

  @Test
  void TicketRepositoryからの回数券情報の全件取得が可能なメソッドticketsListを呼び出せる() {
    List<Tickets> ticketsList = new ArrayList<>();

    Mockito.when(ticketRepository.ticketsList()).thenReturn(ticketsList);

    sut.searchTicketsList();

    Mockito.verify(ticketRepository, Mockito.times(1)).ticketsList();
  }

  @Test
  void TicketRepositoryから全件のユーザー情報を呼び出しTicketJudgmentから回数券情報の登録を判定するメソッドを呼び出すその後TicketRepositoryからinsertTicketsメソッドを呼び出し新しい回数券情報を渡す() {
    String insertId = createValidTickets().getUserId();
    Mockito.when(judgment.insertJudgment(insertId)).thenReturn(
        true);

    sut.searchInsertTickets(createValidTickets());

    Mockito.verify(judgment, Mockito.times(1)).insertJudgment(insertId);
    Mockito.verify(ticketRepository, Mockito.times(1)).insertTickets(Mockito.any(Tickets.class));
  }

  @Test
  void TicketRepositoryから回数券情報を更新できるupdateTicketsメソッドを呼び出せる() {
    Tickets tickets = createValidTickets();

    Mockito.when(ticketRepository.updateTickets(tickets)).thenReturn(1);

    sut.searchUpdateTickets(tickets);

    Mockito.verify(ticketRepository, Mockito.times(1)).updateTickets(Mockito.any(Tickets.class));
  }

  @Test
  void TicketRepositoryから回数券情報を更新できるupdateTicketsメソッドを呼び出した際に取得件数が0件の場合に例外メッセージを返す() {
    Tickets tickets = createValidTickets();

    Mockito.when(ticketRepository.updateTickets(tickets)).thenReturn(0);

    UserNotFoundException actual = Assertions.assertThrows(UserNotFoundException.class, () -> {
      sut.searchUpdateTickets(tickets);
    });

    Assertions.assertEquals("会員ID " + tickets.getUserId() + " の回数券は見つかりません。"
        + "正しいチケット番号とユーザー番号を入力してください。", actual.getMessage());
  }
}
