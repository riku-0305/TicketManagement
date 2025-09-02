package CAP_FIT.TicketManagement.Service;

import static org.junit.jupiter.api.Assertions.*;

import CAP_FIT.TicketManagement.Converter.TicketConverter;
import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Domain.UserInfo;
import CAP_FIT.TicketManagement.Service.Data.NominationTicketService;
import CAP_FIT.TicketManagement.Service.Data.StretchTicketService;
import CAP_FIT.TicketManagement.Service.Data.TicketUserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConverterServiceTest {

  @Mock
  private TicketUserService ticketUserService;

  @Mock
  private NominationTicketService nominationTicketService;

  @Mock
  private StretchTicketService stretchTicketService;

  @Mock
  private TicketConverter ticketConverter;

  private ConverterService sut;

  @BeforeEach
  void before() {
    sut = new ConverterService(ticketUserService,nominationTicketService,stretchTicketService,ticketConverter);
  }

  @Test
  void データパッケージの各サービスクラスとチケットコンバーターを呼び出せる() {
    List<User> userList = new ArrayList<>();
    List<NominationTicket> nominationTicketList = new ArrayList<>();
    List<StretchTicket> stretchTicketList = new ArrayList<>();
    List<UserInfo> userInfoList = new ArrayList<>();

    Mockito.when(ticketUserService.searchUserList()).thenReturn(userList);
    Mockito.when(nominationTicketService.searchNominationTicketList()).thenReturn(nominationTicketList);
    Mockito.when(stretchTicketService.searchStretchTicketList()).thenReturn(stretchTicketList);
    Mockito.when(ticketConverter.convertUserInfoList(userList,nominationTicketList,stretchTicketList)).thenReturn(userInfoList);

    sut.userInfoList();

    Mockito.verify(ticketUserService,Mockito.times(1)).searchUserList();
    Mockito.verify(nominationTicketService,Mockito.times(1)).searchNominationTicketList();
    Mockito.verify(stretchTicketService,Mockito.times(1)).searchStretchTicketList();
    Mockito.verify(ticketConverter,Mockito.times(1)).convertUserInfoList(userList,nominationTicketList,stretchTicketList);
  }
}