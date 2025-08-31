package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NominationTicketService {

  private final TicketRepository ticketRepository;

  @Autowired
  public NominationTicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  public List<NominationTicket> searchNominationTicketList() {
    return ticketRepository.nominationTicketList();
  }

}


