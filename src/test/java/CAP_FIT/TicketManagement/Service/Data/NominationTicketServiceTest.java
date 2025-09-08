package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.NominationTicket;
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
class NominationTicketServiceTest {

  @Mock
  private TicketRepository ticketRepository;

  @Mock
  private TicketJudgment ticketJudgment;

  private NominationTicketService sut;

  @BeforeEach
  void before() {
    sut = new NominationTicketService(ticketRepository,ticketJudgment);
  }

  @Test
  void リポジトリから会員の指名回数券の全件取得が可能なメソッドを呼び出せる() {
    List<NominationTicket> nominationTicketList = new ArrayList<>();

    Mockito.when(ticketRepository.nominationTicketList()).thenReturn(nominationTicketList);

    sut.searchNominationTicketList();

    Mockito.verify(ticketRepository,Mockito.times(1)).nominationTicketList();
  }

  @Test
  void リポジトリから全件の会員情報と回数券を追加するメソッドを呼び出しチケットジャッジメントから指名回数券を追加するメソッドを呼び出せる() {
    List<User> userList = new ArrayList<>();
    userList.add(new User("090-1234-5678"));

    NominationTicket nominationTicket = new NominationTicket("090-1234-5678", 10, LocalDate.of(2025,9,10), "テスト");

    Mockito.when(ticketRepository.userList()).thenReturn(userList);
    Mockito.when(ticketJudgment.insertJudgmentNominationTicket(userList,nominationTicket)).thenReturn(nominationTicket);

    sut.newInsertNominationTicket(nominationTicket);

    Mockito.verify(ticketRepository,Mockito.times(1)).userList();
    Mockito.verify(ticketJudgment,Mockito.times(1)).insertJudgmentNominationTicket(userList,nominationTicket);
    Mockito.verify(ticketRepository,Mockito.times(1)).insertNominationTicket(Mockito.any(NominationTicket.class));
  }
}