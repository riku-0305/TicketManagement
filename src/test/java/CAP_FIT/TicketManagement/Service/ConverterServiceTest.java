package CAP_FIT.TicketManagement.Service;

import CAP_FIT.TicketManagement.Converter.TicketConverter;
import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Domain.UserInfo;
import CAP_FIT.TicketManagement.Service.Data.TicketsService;
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
  private TicketsService ticketsService;

  @Mock
  private TicketConverter ticketConverter;

  private ConverterService sut;

  @BeforeEach
  void before() {
    sut = new ConverterService(ticketUserService, ticketsService,ticketConverter);
  }

  @Test
  void TicketsServiceとTicketUserServiceからユーザー情報と回数券情報を全件取得しTicketConverterのconvertUserInfoListを呼び出し引数として渡せる() {
    List<User> userList = new ArrayList<>();
    List<Tickets> ticketsList = new ArrayList<>();
    List<UserInfo> userInfoList = new ArrayList<>();

    Mockito.when(ticketUserService.searchUserList()).thenReturn(userList);
    Mockito.when(ticketsService.searchTicketsList()).thenReturn(ticketsList);
    Mockito.when(ticketConverter.convertUserInfoList(userList, ticketsList)).thenReturn(userInfoList);

    sut.userInfoList();

    Mockito.verify(ticketUserService,Mockito.times(1)).searchUserList();
    Mockito.verify(ticketsService,Mockito.times(1)).searchTicketsList();
    Mockito.verify(ticketConverter,Mockito.times(1)).convertUserInfoList(userList, ticketsList);
  }

  @Test
  void TicketUserServiceの名前に紐づくユーザーの取得ができるsearchSelectUserListメソッドを呼び出し全件取得した回数券情報searchTicketsListとTicketConverterのconvertUserInfoListに引数として渡せる() {
    String name = "テスト";
    List<User> userList = new ArrayList<>();
    List<Tickets> ticketsList = new ArrayList<>();
    List<UserInfo> userInfoList = new ArrayList<>();

    Mockito.when(ticketUserService.searchSelectUserList(name)).thenReturn(userList);
    Mockito.when(ticketsService.searchTicketsList()).thenReturn(ticketsList);
    Mockito.when(ticketConverter.convertUserInfoList(userList, ticketsList)).thenReturn(userInfoList);

    sut.selectUserInfo(name);

    Mockito.verify(ticketUserService,Mockito.times(1)).searchSelectUserList(name);
    Mockito.verify(ticketsService,Mockito.times(1)).searchTicketsList();
    Mockito.verify(ticketConverter,Mockito.times(1)).convertUserInfoList(userList, ticketsList);
   }
  }