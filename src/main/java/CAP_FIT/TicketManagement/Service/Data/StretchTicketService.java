package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StretchTicketService {

  private final TicketRepository ticketRepository;

  @Autowired
  public StretchTicketService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  public List<StretchTicket> searchStretchTicketList() {
    return ticketRepository.stretchTicketList();
  }

}
