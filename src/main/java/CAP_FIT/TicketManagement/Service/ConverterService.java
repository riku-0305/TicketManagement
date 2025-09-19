package CAP_FIT.TicketManagement.Service;

import CAP_FIT.TicketManagement.Converter.TicketConverter;
import CAP_FIT.TicketManagement.Domain.UserInfo;
import CAP_FIT.TicketManagement.Service.Data.TicketsService;
import CAP_FIT.TicketManagement.Service.Data.TicketUserService;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class ConverterService {

  private TicketUserService ticketUserService;
  private TicketsService ticketsService;

  private TicketConverter ticketConverter;

  @Autowired
  public ConverterService(TicketUserService ticketUserService, TicketsService ticketsService, TicketConverter ticketConverter) {
    this.ticketUserService = ticketUserService;
    this.ticketsService = ticketsService;

    this.ticketConverter = ticketConverter;
  }

  public List<UserInfo> userInfoList() {
    return ticketConverter.convertUserInfoList(ticketUserService.searchUserList(), ticketsService.searchTicketsList());
  }

  public List<UserInfo> selectUserInfo(String name) {
    return ticketConverter.convertUserInfoList(ticketUserService.searchSelectUserList(name),
        ticketsService.searchTicketsList());
  }
}
