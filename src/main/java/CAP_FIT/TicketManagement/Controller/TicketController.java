package CAP_FIT.TicketManagement.Controller;

import CAP_FIT.TicketManagement.Data.NominationTicket;
import CAP_FIT.TicketManagement.Data.StretchTicket;
import CAP_FIT.TicketManagement.Data.User;
import CAP_FIT.TicketManagement.Domain.UserInfo;
import CAP_FIT.TicketManagement.Service.ConverterService;
import CAP_FIT.TicketManagement.Service.Data.NominationTicketService;
import CAP_FIT.TicketManagement.Service.Data.StretchTicketService;
import CAP_FIT.TicketManagement.Service.Data.TicketUserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
  private final NominationTicketService nominationTicketService;
  private final StretchTicketService stretchTicketService;

  @Autowired
  public TicketController(ConverterService converterService, TicketUserService ticketUserService, NominationTicketService nominationTicketService, StretchTicketService stretchTicketService) {

    this.converterService = converterService;
    this.ticketUserService = ticketUserService;
    this.nominationTicketService = nominationTicketService;
    this.stretchTicketService = stretchTicketService;
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

  @PostMapping("/newNominationTicket")
  public ResponseEntity<String> newNominationTicket(@RequestBody @Valid NominationTicket nominationTicket) {
    nominationTicketService.newInsertNominationTicket(nominationTicket);
    return ResponseEntity.ok("指名回数券の登録が完了しました");
  }

  @PostMapping("/newStretchTicket")
  public ResponseEntity<String> newStretchTicket(@RequestBody @Valid StretchTicket stretchTicket) {
    stretchTicketService.newInsertStretchTicket(stretchTicket);
    return ResponseEntity.ok("ストレッチ回数券の登録が完了しました");
  }

  @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody @Valid User user) {
     ticketUserService.searchUpdateUser(user);
     return ResponseEntity.ok("会員情報の変更が完了しました");
  }
}
