package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.TrainingRecord;
import CAP_FIT.TicketManagement.Exception.UserNotFoundException;
import CAP_FIT.TicketManagement.Repository.RecordRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

  private final RecordRepository recordRepository;

  @Autowired
  public RecordService(RecordRepository recordRepository) {
    this.recordRepository = recordRepository;
  }

  /**
   * recordRepositoryからinsertRecordメソッドを呼び出す
   * 新規カルテ情報の登録が可能
   * @param trainingRecord 新規カルテ情報
   */
  public void searchInsertRecord(TrainingRecord trainingRecord) {
    recordRepository.insertRecord(trainingRecord);
  }

  /**
   * recordRepositoryからselectRecordListを呼び出す
   * ユーザーidで指定されたユーザーのカルテ情報リストの取得が可能
   * @param userId ユーザーid
   * @return ユーザーidで指定されたユーザーのカルテ情報リスト
   */
  public List<TrainingRecord> selectRecordList(String userId) {
    List<TrainingRecord> selectRecordList = recordRepository.selectRecordList(userId);

    if(selectRecordList.isEmpty()) {
      throw new UserNotFoundException("ユーザー番号　" + userId + " は存在しません");
    } return selectRecordList;
  }
}
