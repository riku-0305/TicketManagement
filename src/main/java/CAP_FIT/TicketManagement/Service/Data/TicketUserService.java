package CAP_FIT.TicketManagement.Service.Data;


import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

    if (nameSelectUserList.isEmpty()) {
      throw new UserNotFoundException(name + "さんは登録されていません");
    }
    return nameSelectUserList;
  }

  @Transactional
  public void newInsertUser(User user) {
    List<User> userList = ticketRepository.userList();
    for (User users : userList) {
      if (users.getId().equals(user.getId())) {
        throw new DuplicateKeyException("会員番号 " + user.getId() + " はすでに存在しています");
      }
    }
    ticketRepository.insertUser(user);
  }

  @Transactional
  public void searchUpdateUser(User user) {
    List<User> userList = ticketRepository.userList();
    for (User users : userList) {
      if (users.getId().equals(user.getId())) {
        ticketRepository.updateUser(user);
        return;
      }
    }
    throw new UserNotFoundException("会員番号　" + user.getId() + " は存在しません");
  }

  private void searchDeleteUser(User user) {
    int dropUser = ticketRepository.deleteUser(user);

    if(dropUser == 0) {
      throw new UserNotFoundException("会員番号　" + user.getId() + " は存在しません。" + "正しいユーザー番号と名前を入力してください。");
    }
  }

  private void searchDeleteTickets(String userId) {
    ticketRepository.deleteTickets(userId);
  }

  @Transactional
  public void deleteUserInfo(User user) {
    String id = user.getId();
    searchDeleteTickets(id);

    searchDeleteUser(user);
  }
}
