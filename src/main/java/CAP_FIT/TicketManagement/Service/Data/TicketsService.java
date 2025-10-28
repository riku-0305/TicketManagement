package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.Tickets;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import CAP_FIT.TicketManagement.Judgment.Judgment;
import CAP_FIT.TicketManagement.Repository.TicketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketsService {

  private final TicketRepository ticketRepository;
  private final Judgment judgment;

  @Autowired
  public TicketsService(TicketRepository ticketRepository, Judgment judgment) {
    this.ticketRepository = ticketRepository;
    this.judgment = judgment;
  }

  /**
   * チケットリポジトリから全件の回数券情報の取得が可能
   * @return 全件の回数券情報
   */
  public List<Tickets> searchTicketsList() {
    return ticketRepository.ticketsList();
  }

  /**
   * 新規回数券情報の登録が可能
   * チケットリポジトリからユーザーリストを呼び出し、新規回数券情報と併せてチケットジャッジメントに渡す
   * @param tickets 新規回数券情報
   */
  @Transactional
  public void searchInsertTickets(Tickets tickets) {
    String ticketsUserId = tickets.getUserId();
    boolean resultTickets = judgment.insertJudgment(ticketsUserId);
    ticketRepository.insertTickets(tickets);
  }

  /**
   * 既存の回数券の更新が可能
   * チケットリポジトリからアップデートをチケットを呼び出して0件の場合は例外を返す
   * @param tickets 回数券更新情報
   */
  @Transactional
  public void searchUpdateTickets(Tickets tickets) {
    int updateTicketSheet = ticketRepository.updateTickets(tickets);

    if(updateTicketSheet == 0) {
      throw new UserNotFoundException("会員ID " + tickets.getUserId() + " の回数券は見つかりません。" + "正しいチケット番号とユーザー番号を入力してください。");
    }
  }
}