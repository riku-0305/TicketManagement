package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Judgment.TicketJudgment;
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
class StretchTicketServiceTest {

  @Mock
  private TicketRepository repository;

  @Mock
  private TicketJudgment ticketJudgment;

  private StretchTicketService sut;

  @BeforeEach
  void before() {
    sut = new StretchTicketService(repository, ticketJudgment);
  }

  @Test
  void リポジトリから会員のストレッチ回数券の全件取得が可能なメソッドを呼び出せる() {
    List<StretchTicket> stretchTicketList = new ArrayList<>();

    Mockito.when(repository.stretchTicketList()).thenReturn(stretchTicketList);

    sut.searchStretchTicketList();

    Mockito.verify(repository,Mockito.times(1)).stretchTicketList();
  }
}