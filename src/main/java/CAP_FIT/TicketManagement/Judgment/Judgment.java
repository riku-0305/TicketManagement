package CAP_FIT.TicketManagement.Judgment;

import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Judgment {

  private final TicketRepository ticketRepository;

  @Autowired
  public Judgment(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }


  /**
   * 全件のユーザーリストの中のユーザーIDに該当する回数券情報であれば回数券情報を呼び出し元に返し、無ければ例外メッセージを返す
   * @param userId １件の回数券情報
   * @return 引数で渡された回数券情報
   */
  public boolean insertJudgment(String userId) {

    boolean userExists = ticketRepository.userList().stream()
        .anyMatch(user -> user.getId().equals(userId));

    if (userExists) {
      return true;
    } else {
      throw new UserNotFoundException("会員ID " + userId + " の会員は見つかりません");
    }
  }

  public boolean nameJudgment(String name) {

    boolean userExists = ticketRepository.userList().stream()
        .anyMatch(user -> user.getName().equals(name));

    if(userExists) {
      return true;
    } else {
      throw new UserNotFoundException("ユーザーネーム" + name + "さんは見つかりませんでした");
    }
  }
}

