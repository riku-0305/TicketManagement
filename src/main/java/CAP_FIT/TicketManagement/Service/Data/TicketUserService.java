package CAP_FIT.TicketManagement.Service.Data;


import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketUserService {

  private final TicketRepository ticketRepository;

  @Autowired
  public TicketUserService(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  public List<User> searchUserList() {
    return ticketRepository.userList();
  }

  public List<User> searchSelectUserList(String name) {
   return ticketRepository.selectUserList(name);
  }
}
