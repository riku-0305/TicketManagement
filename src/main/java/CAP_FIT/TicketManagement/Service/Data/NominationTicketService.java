package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Judgment.TicketJudgment;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NominationTicketService {

  private final TicketRepository ticketRepository;
  private final TicketJudgment ticketJudgment;

  @Autowired
  public NominationTicketService(TicketRepository ticketRepository, TicketJudgment ticketJudgment) {
    this.ticketRepository = ticketRepository;
    this.ticketJudgment = ticketJudgment;
  }

  public List<NominationTicket> searchNominationTicketList() {
    return ticketRepository.nominationTicketList();
  }

  @Transactional
  public void newInsertNominationTicket(NominationTicket nominationTicket) {
    List<User> userList = ticketRepository.userList();
    NominationTicket resultNominationTicket = ticketJudgment.insertJudgmentNominationTicket(userList,nominationTicket);
    ticketRepository.insertNominationTicket(resultNominationTicket);
  }
}