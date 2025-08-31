package CAP_FIT.TicketManagement.Service;

import CAP_FIT.TicketManagement.Converter.TicketConverter;
import CAP_FIT.TicketManagement.Domain.UserInfo;
import CAP_FIT.TicketManagement.Service.Data.NominationTicketService;
import CAP_FIT.TicketManagement.Service.Data.StretchTicketService;
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
  private NominationTicketService nominationTicketService;
  private StretchTicketService stretchTicketService;

  private TicketConverter ticketConverter;

  @Autowired
  public void TicketUser(NominationTicketService nominationTicketService, TicketUserService ticketUserService, StretchTicketService stretchTicketService, TicketConverter ticketConverter) {
    this.ticketUserService = ticketUserService;
    this.nominationTicketService = nominationTicketService;
    this.stretchTicketService = stretchTicketService;

    this.ticketConverter = ticketConverter;
  }

  public List<UserInfo> userInfoList() {
    return ticketConverter.convertUserInfoList(ticketUserService.searchUserList(), nominationTicketService.searchNominationTicketList(), stretchTicketService.searchStretchTicketList());
  }

}
