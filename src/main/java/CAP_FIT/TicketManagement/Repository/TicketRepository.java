package CAP_FIT.TicketManagement.Repository;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketRepository {

  List<User> userList();

  List<Tickets> ticketsList();

  List<User> selectUserList(String name);

  void insertUser(User user);

  void insertTickets(Tickets tickets);

  void updateUser(User user);

  int updateTickets(Tickets tickets);

}
