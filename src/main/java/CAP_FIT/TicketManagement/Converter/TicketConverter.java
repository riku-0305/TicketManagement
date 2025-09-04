package CAP_FIT.TicketManagement.Converter;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Domain.UserInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {

  public List<UserInfo> convertUserInfoList(List<User> userList,
      List<NominationTicket> nominationTicketList, List<StretchTicket> stretchTicketList) {

    List<UserInfo> userInfoList = new ArrayList<>();

    userList.forEach(user -> {
      UserInfo userInfo = new UserInfo();
      userInfo.setUser(user);

      nominationTicketList.stream()
          .filter(nominationTicket -> user.getId().equals(nominationTicket.getUserId()))
          .forEach(userInfo::setNominationTicket);

      stretchTicketList.stream().filter(stretchTicket -> user.getId().equals(stretchTicket.getUserId()))
          .forEach(userInfo::setStretchTicket);

      userInfoList.add(userInfo);
    });
    return userInfoList;
  }
}
