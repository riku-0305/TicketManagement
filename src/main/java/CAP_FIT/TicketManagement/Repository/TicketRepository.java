package CAP_FIT.TicketManagement.Repository;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketRepository {

  /**
   * 全件のユーザー情報の取得が可能
   * @return 全件のユーザー情報
   */
  List<User> userList();

  /**
   * 全件の回数券情報の取得が可能
   * @return 全件尾回数券情報
   */
  List<Tickets> ticketsList();

  /**
   * 名前で指定されたユーザー情報の取得が可能
   * @param name 指定されたユーザーの名前
   * @return 指定されたユーザー
   */
  List<User> selectUserList(String name);

  /**
   * ユーザーの新規登録が可能
   * @param user 新規ユーザー情報
   */
  void insertUser(User user);

  /**
   * 新規回数券の登録が可能
   * @param tickets 新規回数券情報
   */
  void insertTickets(Tickets tickets);

  /**
   * ユーザー情報の更新が可能
   * @param user ユーザー更新情報
   */
  void updateUser(User user);

  /**
   * 回数券情報の更新が可能
   * @param tickets 回数券更新情報
   * @return 更新した回数券の件数
   */
  int updateTickets(Tickets tickets);

  /**
   * ユーザーの削除が可能
   * @param user 削除したいユーザー情報
   * @return 削除したユーザーの件数
   */
  int deleteUser(User user);

  /**
   * 回数券の削除が可能
   * @param userId 削除したい回数券情報を持つユーザーのID
   */
  void deleteTickets(String userId);

}
