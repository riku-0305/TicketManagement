package CAP_FIT.TicketManagement.Service.Data;


import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    List<User> nameSelectUserList = ticketRepository.selectUserList(name);

    if(nameSelectUserList.isEmpty()) {
      throw new UserNotFoundException(name + "さんは登録されていません");
    }
   return nameSelectUserList;
  }

  @Transactional
  public void newInsertUser(User user) {
    ticketRepository.insertUser(user);
  }
}
