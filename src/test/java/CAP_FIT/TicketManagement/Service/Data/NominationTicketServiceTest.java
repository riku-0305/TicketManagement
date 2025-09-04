package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.NominationTicket;
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
class NominationTicketServiceTest {

  @Mock
  private TicketRepository ticketRepository;

  private NominationTicketService sut;

  @BeforeEach
  void before() {
    sut = new NominationTicketService(ticketRepository);
  }

  @Test
  void リポジトリから会員の指名回数券の全件取得が可能なメソッドを呼び出せる() {
    List<NominationTicket> nominationTicketList = new ArrayList<>();

    Mockito.when(ticketRepository.nominationTicketList()).thenReturn(nominationTicketList);

    sut.searchNominationTicketList();

    Mockito.verify(ticketRepository,Mockito.times(1)).nominationTicketList();
  }
}