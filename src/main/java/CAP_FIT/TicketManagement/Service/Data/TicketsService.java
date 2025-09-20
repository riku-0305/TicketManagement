package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import CAP_FIT.TicketManagement.Judgment.TicketJudgment;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketsService {

  private final TicketRepository ticketRepository;
  private final TicketJudgment ticketJudgment;

  @Autowired
  public TicketsService(TicketRepository ticketRepository, TicketJudgment ticketJudgment) {
    this.ticketRepository = ticketRepository;
    this.ticketJudgment = ticketJudgment;
  }

  public List<Tickets> searchTicketsList() {
    return ticketRepository.ticketsList();
  }

  @Transactional
  public void searchInsertTickets(Tickets tickets) {
    List<User> userList = ticketRepository.userList();
    Tickets resultTickets = ticketJudgment.insertJudgmentTickets(userList, tickets);
    ticketRepository.insertTickets(resultTickets);
  }

  @Transactional
  public void searchUpdateTickets(Tickets tickets) {
    int nominationTicketSheet = ticketRepository.updateTickets(tickets);

    if(nominationTicketSheet == 0) {
      throw new UserNotFoundException("会員ID " + tickets.getUserId() + " の回数券は見つかりません。" + "正しいチケット番号とユーザー番号を入力してください。");
    }
  }
}