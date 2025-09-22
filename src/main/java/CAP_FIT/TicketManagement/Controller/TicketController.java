package CAP_FIT.TicketManagement.Controller;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Domain.UserInfo;
import CAP_FIT.TicketManagement.Service.ConverterService;
import CAP_FIT.TicketManagement.Service.Data.TicketsService;
import CAP_FIT.TicketManagement.Service.Data.TicketUserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class TicketController {

  private final ConverterService converterService;
  private final TicketUserService ticketUserService;
  private final TicketsService ticketsService;

  @Autowired
  public TicketController(ConverterService converterService, TicketUserService ticketUserService, TicketsService ticketsService) {

    this.converterService = converterService;
    this.ticketUserService = ticketUserService;
    this.ticketsService = ticketsService;
  }

  @GetMapping("/userList")
  public List<UserInfo> getUserList(
      @RequestParam(name = "name", required = false) String name) {
    if(name != null) {
      return converterService.selectUserInfo(name);
    }
    return converterService.userInfoList();
  }

  @PostMapping("/newUser")
  public ResponseEntity<String> newUser(@RequestBody @Valid User user) {
    ticketUserService.newInsertUser(user);
    return ResponseEntity.ok("会員登録が完了しました");
  }

  @PostMapping("/newTickets")
  public ResponseEntity<String> newNominationTicket(@RequestBody @Valid Tickets tickets) {
    ticketsService.searchInsertTickets(tickets);
    return ResponseEntity.ok("回数券登録が完了しました");
  }

  @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody @Valid User user) {
     ticketUserService.searchUpdateUser(user);
     return ResponseEntity.ok("会員情報の更新が完了しました");
  }

  @PutMapping("/updateTickets")
   public ResponseEntity<String> updateTickets(@RequestBody @Valid Tickets tickets) {
    ticketsService.searchUpdateTickets(tickets);
    return ResponseEntity.ok("回数券の更新が完了しました");
  }

  @DeleteMapping("/deleteUserInfo")
  public ResponseEntity<String> deleteUserInfo(@RequestBody @Valid User user) {
   ticketUserService.deleteUserInfo(user);
   return ResponseEntity.ok("会員情報の削除が完了しました");
  }
}
