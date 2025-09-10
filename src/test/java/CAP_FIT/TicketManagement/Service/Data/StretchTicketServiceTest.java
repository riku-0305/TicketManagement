package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
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
class StretchTicketServiceTest {

  @Mock
  private TicketRepository ticketRepository;

  @Mock
  private TicketJudgment ticketJudgment;

  private StretchTicketService sut;

  @BeforeEach
  void before() {
    sut = new StretchTicketService(ticketRepository, ticketJudgment);
  }

  @Test
  void リポジトリから会員のストレッチ回数券の全件取得が可能なメソッドを呼び出せる() {
    List<StretchTicket> stretchTicketList = new ArrayList<>();

    Mockito.when(ticketRepository.stretchTicketList()).thenReturn(stretchTicketList);

    sut.searchStretchTicketList();

    Mockito.verify(ticketRepository,Mockito.times(1)).stretchTicketList();
  }

  @Test
  void リポジトリから全件の会員情報とストレッチ回数券を追加するメソッドを呼び出しチケットジャッジメントからストレッチ回数券の登録を判定するメソッドを呼び出せる() {
    List<User> userList = new ArrayList<>();
    userList.add(new User("090-1234-5678"));

    StretchTicket stretchTicket = new StretchTicket("090-1234-5678", 10, LocalDate.of(2025,9,10), "テスト");

    Mockito.when(ticketRepository.userList()).thenReturn(userList);
    Mockito.when(ticketJudgment.insertJudgmentStretchTicket(userList,stretchTicket)).thenReturn(stretchTicket);

    sut.newInsertStretchTicket(stretchTicket);

    Mockito.verify(ticketRepository,Mockito.times(1)).userList();
    Mockito.verify(ticketJudgment,Mockito.times(1)).insertJudgmentStretchTicket(userList, stretchTicket);
    Mockito.verify(ticketRepository,Mockito.times(1)).insertStretchTicket(Mockito.any(
        StretchTicket.class));
  }
}