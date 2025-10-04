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

  /**
   * チケットリポジトリから全件のユーザー情報の取得が可能
   * @return 全件のユーザー情報
   */
  public List<User> searchUserList() {
    return ticketRepository.userList();
  }

  /**
   * 名前での検索によるユーザー情報の取得が可能
   * 指定されたユーザーネームがない場合は例外を返す
   * @param name 指定されたユーザーネーム
   * @return 指定された名前を持つユーザーリスト
   */
  public List<User> searchSelectUserList(String name) {
    List<User> nameSelectUserList = ticketRepository.selectUserList(name);

    if (nameSelectUserList.isEmpty()) {
      throw new UserNotFoundException(name + "さんは登録されていません");
    }
    return nameSelectUserList;
  }

  /**
   * 新規ユーザー情報の登録が可能
   * チケットリポジトリから全件のユーザーリストを呼び出してIDが競合していれば例外を返す
   * @param user 新規ユーザー情報
   */
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

  /**
   * 既存ユーザーの更新が可能
   * チケットリポジトリからユーザーリストを呼び出して引数のユーザーのIDにマッチしない場合は例外を返す
   * @param user 既存ユーザー更新情報
   */
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

  /**
   * ユーザー情報の削除が可能
   * チケットリポジトリからdeleteUserを呼び出し、0件の場合は例外を返す
   * @param user 削除したいユーザー情報
   */
  private void searchDeleteUser(User user) {
    int dropUser = ticketRepository.deleteUser(user);

    if(dropUser == 0) {
      throw new UserNotFoundException("会員番号　" + user.getId() + " は存在しません。" + "正しいユーザー番号と名前を入力してください。");
    }
  }

  /**
   * ユーザー情報とそのユーザーに紐づく回数券情報リストの一括削除が可能
   * @param user 削除したいユーザー
   */
  @Transactional
  public void deleteUserInfo(User user) {
    String id = user.getId();
    ticketRepository.deleteTickets(id);

    searchDeleteUser(user);
  }
}
