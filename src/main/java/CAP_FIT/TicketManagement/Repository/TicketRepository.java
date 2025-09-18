package CAP_FIT.TicketManagement.Repository;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Data.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketRepository {

  List<User> userList();

  List<NominationTicket> nominationTicketList();

  List<StretchTicket> stretchTicketList();

  List<User> selectUserList(String name);

  void insertUser(User user);

  void insertNominationTicket(NominationTicket nominationTicket);

  void insertStretchTicket(StretchTicket stretchTicket);

  void updateUser(User user);

  int updateNominationTicket(NominationTicket nominationTicket);

}
