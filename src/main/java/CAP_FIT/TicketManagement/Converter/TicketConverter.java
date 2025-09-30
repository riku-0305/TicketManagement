package CAP_FIT.TicketManagement.Converter;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Domain.UserInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {

  /**
   * ユーザー情報とそのユーザーに紐づく回数券情報のマッピングが可能
   * @param userList 全件のユーザー情報
   * @param ticketsList 全件の回数券情報
   * @return ユーザー情報とそのユーザーに紐づく回数券情報リスト
   */
  public List<UserInfo> convertUserInfoList(List<User> userList, List<Tickets> ticketsList) {

    List<UserInfo> userInfoList = new ArrayList<>();

    userList.forEach(user -> {
      UserInfo userInfo = new UserInfo();
      userInfo.setUser(user);

      List<Tickets> convertTickets = ticketsList.stream()
          .filter(tickets -> user.getId().equals(tickets.getUserId()))
          .toList();

      userInfo.setTickets(convertTickets);
      userInfoList.add(userInfo);
    });
    return userInfoList;
  }
}
