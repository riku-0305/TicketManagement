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

  /**
   * convertUserInfoListからマッピングされたユーザー情報とそのユーザーに紐づく回数券情報リストの取得が可能
   * @return マッピングされた全件のユーザー情報
   */
  public List<UserInfo> userInfoList() {
    return ticketConverter.convertUserInfoList(ticketUserService.searchUserList(), ticketsService.searchTicketsList());
  }

  /**
   * convertUserInfoListから名前で指定し、マッピングされたユーザー情報とそのユーザーに紐づく回数券情報リストの取得が可能
   * @param name 指定されたユーザーの名前
   * @return マッピングされた指定のユーザー情報
   */
  public List<UserInfo> selectUserInfo(String name) {
    return ticketConverter.convertUserInfoList(ticketUserService.searchSelectUserList(name),
        ticketsService.searchTicketsList());
  }
}
