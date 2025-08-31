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

}
