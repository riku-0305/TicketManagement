package CAP_FIT.TicketManagement.Controller;

import CAP_FIT.TicketManagement.Domain.UserInfo;
import CAP_FIT.TicketManagement.Service.ConverterService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

  private final ConverterService converterService;

  @Autowired
  public TicketController(ConverterService converterService) {
    this.converterService = converterService;
  }

  @GetMapping("/userList")
  public List<UserInfo> getUserList(
      @RequestParam(name = "name", required = false) String name) {
    if(name != null) {
      return converterService.selectUserInfo(name);
    }
    return converterService.userInfoList();
  }
}
