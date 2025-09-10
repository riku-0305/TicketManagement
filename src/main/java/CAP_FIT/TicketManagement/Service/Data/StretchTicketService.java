package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Judgment.TicketJudgment;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StretchTicketService {

  private final TicketRepository ticketRepository;
  private final TicketJudgment ticketJudgment;

  @Autowired
  public StretchTicketService(TicketRepository ticketRepository, TicketJudgment ticketJudgment) {
    this.ticketRepository = ticketRepository;
    this.ticketJudgment = ticketJudgment;
  }

  public List<StretchTicket> searchStretchTicketList() {
    return ticketRepository.stretchTicketList();
  }

  @Transactional
  public void newInsertStretchTicket(StretchTicket stretchTicket) {
    List<User> userList = ticketRepository.userList();
    ticketJudgment.insertJudgmentStretchTicket(userList, stretchTicket);
    ticketRepository.insertStretchTicket(stretchTicket);
  }
}
